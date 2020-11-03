package com.rafaelsoares.marvel.ui.superheroes

import com.rafaelsoares.marvel.model.data.SuperheroesData
import com.rafaelsoares.marvel.ui.BaseContract

class SuperHeroesContract {
    interface View: BaseContract.View {
        fun requestSuperheroesData(limit: Int, offset: Int)
        fun confirmApiResponse(superheroesData: SuperheroesData?)
    }

    interface Presenter<T>: BaseContract.Presenter<View> {
        fun getSuperheroesData(limit: Int, offset: Int)
    }
}