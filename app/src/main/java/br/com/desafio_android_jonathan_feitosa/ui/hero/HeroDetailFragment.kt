package br.com.desafio_android_jonathan_feitosa.ui.hero

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.models.Hero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hero_detail.*


class HeroDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        return inflater.inflate(R.layout.fragment_hero_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hero : Hero? = arguments?.getParcelable("hero")

        hero?.let {

            tv_overview_txt.text = hero.name

            if (checkIsNull(hero.description))
                tv_description_txt.text = getString(R.string.no_description)

            else
                tv_description_txt.text = hero.description

            Picasso.get()
                .load("${hero.thumbnail!!.path}.${hero.thumbnail!!.extension}")
                .error(R.drawable.placeholder)
                .into(imgDescription)

            button.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("comicId", hero.id?.toString()!!)
                findNavController().navigate(R.id.action_heroDetailFragment_to_hqFragment4, bundle)

            }
        }

    }

    fun checkIsNull(tt : String) : Boolean{
        return (tt == "")
    }

}
