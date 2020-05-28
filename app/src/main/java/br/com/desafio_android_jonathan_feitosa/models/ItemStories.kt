package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemStories (
    var resourceURI:String,
    var name:String,
    var type:String
):Parcelable