package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemEvents>,
    var returned:Int
):Parcelable