package com.rafaelsoares.marvel.model.data


import com.google.gson.annotations.SerializedName
import com.rafaelsoares.marvel.model.Superhero

data class SuperheroesData(
    @SerializedName("attributionHTML")
    var attributionHTML: String = "",
    @SerializedName("attributionText")
    var attributionText: String = "",
    @SerializedName("code")
    var code: String = "",
    @SerializedName("copyright")
    var copyright: String = "",
    @SerializedName("data")
    var dataResult: DataResult = DataResult(),
    @SerializedName("etag")
    var etag: String = "",
    @SerializedName("status")
    var status: String = ""
) {
    data class DataResult(
        @SerializedName("count")
        var count: String = "",
        @SerializedName("limit")
        var limit: String = "",
        @SerializedName("offset")
        var offset: String = "",
        @SerializedName("results")
        var results: MutableList<Superhero> = ArrayList(),
        @SerializedName("total")
        var total: String = ""
    )
}