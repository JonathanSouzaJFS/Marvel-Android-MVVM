package br.com.desafio_android_jonathan_feitosa.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.models.Hero
import br.com.desafio_android_jonathan_feitosa.ui.hq.HqFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hero_detail.*


class HeroDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hero_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val hero = intent.getParcelableExtra<Hero>(EXTRA_HERO_ID)
        val hero : Hero? = arguments?.getParcelable("hero")

        hero?.let {

            tv_overview_txt.text = hero.name

            if (checkIsNull(hero.description))
                tv_description_txt.text = "No Description"
            else
                tv_description_txt.text = hero.description

            Picasso.get()
                .load("${hero.thumbnail!!.path}/portrait_medium.${hero.thumbnail!!.extension}")
                .error(R.drawable.placeholder)
                .into(imgDescription)

            button.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("comicId", hero.id!!)
                findNavController().navigate(R.id.action_heroDetailFragment_to_hqFragment4, bundle)

            }
        }

    }
        fun checkIsNull(tt : String) : Boolean{
        return (tt == "")
    }

}
