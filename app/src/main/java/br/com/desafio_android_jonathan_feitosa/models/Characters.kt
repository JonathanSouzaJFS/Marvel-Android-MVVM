package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters (
    var available:Int,
    var collectionURI:String,
    var items:List<ItemCharacters>,
    var returned:Int
):Parcelable