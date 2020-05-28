package br.com.desafio_android_jonathan_feitosa.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.desafio_android_jonathan_feitosa.R
import com.squareup.picasso.Picasso

class ImageUtils {

    companion object{

        @JvmStatic
        @BindingAdapter("bind:picassoLoad")
        fun loadImageView2(image: ImageView, imageUrl: String?) {
            if(imageUrl.isNullOrEmpty().not()){

                val url =/* BuildConfig.IMAGE_URL + */"" +  imageUrl

                Picasso.get().load(url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(image)
            }
        }
    }
}

