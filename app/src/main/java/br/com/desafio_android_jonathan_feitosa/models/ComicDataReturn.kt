package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicDataReturn (
    var offset:Int,
    var limit:Int,
    var total:Int,
    var count:Int,
    var results:List<ComicsId>
): Parcelable