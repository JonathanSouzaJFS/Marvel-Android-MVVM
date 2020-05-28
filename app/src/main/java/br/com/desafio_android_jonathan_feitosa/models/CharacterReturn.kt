package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterReturn (
    var code:Int,
    var status:String,
    var copyright:String,
    var attributionText:String,
    var attributionHTML:String,
    var etag:String,
    var data: DataReturn
): Parcelable