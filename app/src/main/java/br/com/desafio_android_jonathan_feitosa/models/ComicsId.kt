package br.com.desafio_android_jonathan_feitosa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ComicsId  (
    var id:Int?,
    var digitalId:Int,
    var title:String,
    var issueNumber:Double?,
    var variantDescription:String?,
    var description:String?,
    var modified: Date,
    var isbn: String,
    var upc: String,
    var diamondCode: String,
    var issn: String,
    var format: String,
    var pageCount: String,
    var resourceURI:String?,
    var thumbnail: Thumbnail?,
    var comics: Comics?,
    var series: Series?,
    var stories: Stories?,
    var events: Events?,
    var creators: Creators?,
    var characters: Characters?,
    var prices: Prices?,
    var variants: Variants?,
    var dates: Dates?,
    var images: Images?,
    var urls:List<ItemUrls>?
): Parcelable

