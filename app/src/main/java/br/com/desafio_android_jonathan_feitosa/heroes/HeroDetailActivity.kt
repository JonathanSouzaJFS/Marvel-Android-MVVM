package br.com.desafio_android_jonathan_feitosa.heroes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.models.Hero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hero_detail.*


class HeroDetailActivity : AppCompatActivity() {


    companion object {
        private const val EXTRA_HERO_ID = "EXTRA_HERO_ID"

        fun getStartIntent(context: Context, hero: Hero): Intent {
            return Intent(context, HeroDetailActivity::class.java).apply {
                putExtra(EXTRA_HERO_ID, hero)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        val hero = intent.getParcelableExtra<Hero>(EXTRA_HERO_ID)

        hero!!.let{

            tv_overview_txt.text  = hero.name

            if(checkIsNull(hero.description))
                tv_description_txt.text  = "No Description"
            else
                tv_description_txt.text  = hero.description


            Picasso.get().load("${hero.thumbnail!!.path}/portrait_medium.${hero.thumbnail!!.extension}")
                .error(R.drawable.placeholder)
                .into(imgDescription)

        }

    }

    fun checkIsNull(tt : String) : Boolean{
        return (tt == "")
    }

}
