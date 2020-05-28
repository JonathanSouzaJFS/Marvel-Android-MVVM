package br.com.desafio_android_jonathan_feitosa.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.base.BaseFragment
import br.com.desafio_android_jonathan_feitosa.models.Hero
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {

    private var hList:MutableList<Hero> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        var hero = Hero("Os Vilões", "https://a.wattpad.com/useravatar/ProjetoHero.256.566969.jpg")
        hList.add(hero)

        hero = Hero("Poderoso Chefão", "https://a.wattpad.com/useravatar/ProjetoHero.256.566969.jpg")
        hList.add(hero)

        hero = Hero("De Volta Para o Futuro", "https://a.wattpad.com/useravatar/ProjetoHero.256.566969.jpg")
        hList.add(hero)

        hero = Hero("Forrest Gump", "https://a.wattpad.com/useravatar/ProjetoHero.256.566969.jpg")
        hList.add(hero)


        setupRecyclerView()

    }
    private fun setupRecyclerView() {

        with(rv_upcoming_hero) {
            layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)

            adapter = UpcomingAdapter(context, hList) { hero ->
                Toast.makeText(
                    requireActivity(), hero.name,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
