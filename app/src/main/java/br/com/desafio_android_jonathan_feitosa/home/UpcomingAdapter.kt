package br.com.desafio_android_jonathan_feitosa.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import br.com.desafio_android_jonathan_feitosa.databinding.ItemHeroBinding
import br.com.desafio_android_jonathan_feitosa.models.Hero

class UpcomingAdapter(context: Context,
                      list: List<Hero>,
                      private val onItemClickListener:((hero:Hero) -> Unit) ) : RecyclerView.Adapter<UpcomingAdapter.MyViewHolder?>() {

    private var mContext =context
    private var mList = list
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val binding = ItemHeroBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindHero(mList[position])
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount() = mList.count()

    inner class MyViewHolder(private val binding: ItemHeroBinding,
                             private val onItemClickListener: ((hero: Hero) -> Unit)) :RecyclerView.ViewHolder(binding.root){

        fun bindHero(hero: Hero) {
            binding.hero = hero
            binding.root.setOnClickListener{
                onItemClickListener.invoke(hero)
            }
        }
    }
}