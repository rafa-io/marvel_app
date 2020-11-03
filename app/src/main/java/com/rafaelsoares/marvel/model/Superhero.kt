package com.rafaelsoares.marvel.model


import com.google.gson.annotations.SerializedName

data class Superhero(
    @SerializedName("comics")
    var comics: Comics = Comics(),
    @SerializedName("description")
    var description: String = "",
    @SerializedName("events")
    var events: Events = Events(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("modified")
    var modified: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("resourceURI")
    var resourceURI: String = "",
    @SerializedName("series")
    var series: Series = Series(),
    @SerializedName("stories")
    var stories: Stories = Stories(),
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("urls")
    var urls: List<Url> = listOf()
) {
    data class Comics(
        @SerializedName("available")
        var available: Int = 0,
        @SerializedName("collectionURI")
        var collectionURI: String = "",
        @SerializedName("items")
        var items: List<Item> = listOf(),
        @SerializedName("returned")
        var returned: Int = 0
    ) {
        data class Item(
            @SerializedName("name")
            var name: String = "",
            @SerializedName("resourceURI")
            var resourceURI: String = ""
        )
    }

    data class Events(
        @SerializedName("available")
        var available: Int = 0,
        @SerializedName("collectionURI")
        var collectionURI: String = "",
        @SerializedName("items")
        var items: List<Item> = listOf(),
        @SerializedName("returned")
        var returned: Int = 0
    ) {
        data class Item(
            @SerializedName("name")
            var name: String = "",
            @SerializedName("resourceURI")
            var resourceURI: String = ""
        )
    }

    data class Series(
        @SerializedName("available")
        var available: Int = 0,
        @SerializedName("collectionURI")
        var collectionURI: String = "",
        @SerializedName("items")
        var items: List<Item> = listOf(),
        @SerializedName("returned")
        var returned: Int = 0
    ) {
        data class Item(
            @SerializedName("name")
            var name: String = "",
            @SerializedName("resourceURI")
            var resourceURI: String = ""
        )
    }

    data class Stories(
        @SerializedName("available")
        var available: Int = 0,
        @SerializedName("collectionURI")
        var collectionURI: String = "",
        @SerializedName("items")
        var items: List<Item> = listOf(),
        @SerializedName("returned")
        var returned: Int = 0
    ) {
        data class Item(
            @SerializedName("name")
            var name: String = "",
            @SerializedName("resourceURI")
            var resourceURI: String = "",
            @SerializedName("type")
            var type: String = ""
        )
    }

    data class Thumbnail(
        @SerializedName("extension")
        var extension: String = "",
        @SerializedName("path")
        var path: String = ""
    )

    data class Url(
        @SerializedName("type")
        var type: String = "",
        @SerializedName("url")
        var url: String = ""
    )
}