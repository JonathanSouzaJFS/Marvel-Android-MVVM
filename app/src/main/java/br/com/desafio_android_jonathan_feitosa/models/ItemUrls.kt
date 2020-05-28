package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemUrls (
    var type:String,
    var url:String
): Parcelable