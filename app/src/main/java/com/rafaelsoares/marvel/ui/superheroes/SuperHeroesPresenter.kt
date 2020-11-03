package com.rafaelsoares.marvel.ui.superheroes

import android.content.Context
import android.util.Log
import com.rafaelsoares.marvel.config.APIConfig
import com.rafaelsoares.marvel.model.data.SuperheroesData
import com.rafaelsoares.marvel.server.api.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SuperHeroesPresenter: SuperHeroesContract.Presenter<SuperheroesData> {

    private lateinit var subscriptions: CompositeDisposable
    private var api: APIService = APIService.create()
    private var view: SuperHeroesContract.View? = null

    override fun subscribe() {
        subscriptions = CompositeDisposable()
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: SuperHeroesContract.View, context: Context) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getSuperheroesData(limit: Int, offset: Int) {
        view?.progress(true)
        val subscribe = api
            .getSuperheroesList(
                limit,
                offset,
                APIConfig.TS,
                APIConfig.API_KEY,
                APIConfig.HASH
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: SuperheroesData ->
                view?.progress(false)
                view?.confirmApiResponse(data)
            }, { error ->
                view?.show("Erro",error.message.toString())
                Log.e("Erro", error.stackTraceToString())
            })
        subscriptions.add(subscribe)
    }
}