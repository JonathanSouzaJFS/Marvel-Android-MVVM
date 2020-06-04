package br.com.desafio_android_jonathan_feitosa.ui.hq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.base.BaseFragment
import br.com.desafio_android_jonathan_feitosa.models.ComicsResponse
import br.com.desafio_android_jonathan_feitosa.utils.hasInternet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_comics.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HqFragment : BaseFragment() {

    private var hList: MutableList<ComicsResponse.Data.Result> = arrayListOf()
    private val hqViewModel by viewModel<HqViewModel>()
    private var loading = false
    private var totalComics = ""
    private var comicId = ""

    override fun checkConnection(){
        if(hasInternet(activity)){
            hqViewModel.getComicId(comicId)
        }else{
            Toast.makeText(
                requireActivity(), "[Error]: Connection not found!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        comicId = arguments?.getString("comicId")!!

        hqViewModel.comicsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { pair ->
                hList.addAll(pair.first!!)
                totalComics = pair.second
                loading = false

                val hqMax  = hList.maxBy { p -> p.prices[0].price.toDouble() }

                cm_description_cmcs.text = hqMax!!.description
                cm_overview_cmcs.text = hqMax.title
                txtPrice_cmcs.text = hqMax.prices[0].price

                Picasso.get()
                    .load("${ hqMax.thumbnail.path}.${hqMax.thumbnail.extension}")
                    .error(R.drawable.placeholder)
                    .into(imgDescription_cmcs)


            }
        })

        hqViewModel.loading.observe(viewLifecycleOwner, Observer { load ->
            if(load)
                pb_hq_view.visibility = View.VISIBLE
            else
                pb_hq_view.visibility = View.GONE
        })

        checkConnection()
    }
}
