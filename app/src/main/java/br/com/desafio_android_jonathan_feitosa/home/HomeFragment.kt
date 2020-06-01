package br.com.desafio_android_jonathan_feitosa.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.base.BaseFragment
import br.com.desafio_android_jonathan_feitosa.heroes.HeroDetailActivity
import br.com.desafio_android_jonathan_feitosa.models.Hero
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private var hList: MutableList<Hero> = arrayListOf()
    private val homeViewModel by viewModel<HomeViewModel>()
    private var pastVisiblesItems = 0
    private var totalItemCount = 0
    private var loading = false
    private var pageLoad = 1
    private var totalPages = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun checkConnection() {
        homeViewModel.getUpcomingList(pageLoad)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        setupRecyclerView()

        homeViewModel.heroesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { pair ->
                hList.addAll(pair.first!!)
                totalPages = pair.second
                rv_upcoming_hero.adapter!!.notifyDataSetChanged()
                loading = false
            }
        })

        homeViewModel.hasErrorLiveData.observe(viewLifecycleOwner, Observer {error ->
            if (error) {
                Toast.makeText(
                    requireActivity(), "Error get upcomming list !",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        swiperefresh.setColorSchemeResources(R.color.colorBlack)
        swiperefresh.setOnRefreshListener{
            this.checkConnection()
        }

        homeViewModel.loading.observe(viewLifecycleOwner, Observer { load ->
            swiperefresh.isRefreshing = load
        })

        checkConnection()

    }

    private fun setupRecyclerView() {
        with(rv_upcoming_hero) {
            layoutManager = GridLayoutManager(
                activity,
                3,
                RecyclerView.VERTICAL,
                false
            )
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView, dx: Int, dy: Int
                ) {
                    if (dy > 0) {

                        totalItemCount = layoutManager!!.itemCount
                        pastVisiblesItems =
                            (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (!loading && (pageLoad <= totalPages)) {
                            if (pastVisiblesItems >= totalItemCount - 1) {

                                loading = true
                                pageLoad++
                                checkConnection()
                            }
                        }
                    }
                }
            })

            adapter = UpcomingAdapter(context, hList) { hero ->
                val intent = HeroDetailActivity.getStartIntent(requireActivity(), hero)
                requireActivity().startActivity(intent)
            }
        }
    }
}
