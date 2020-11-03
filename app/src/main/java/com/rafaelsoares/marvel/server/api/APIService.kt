package com.rafaelsoares.marvel.server.api

import com.rafaelsoares.marvel.config.APIConfig
import com.rafaelsoares.marvel.model.Superhero
import com.rafaelsoares.marvel.model.data.SuperheroesData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("characters")
    fun getSuperheroesList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Observable<SuperheroesData>

    @GET("characters/{character_id}")
    fun getSuperhero(
        @Path("character_id") characterId: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Observable<Superhero>

    companion object Factory {
        fun create(): APIService {

            val okHttpClient = OkHttpClient.Builder()
                .build()

            val retrofit = retrofit2.Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConfig.BASE_URL)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}