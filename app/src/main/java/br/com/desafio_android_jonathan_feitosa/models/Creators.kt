package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Creators (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemCreators>,
    var returned:Int
):Parcelable