package com.rafaelsoares.marvel.injection.module.superheroes

import androidx.fragment.app.Fragment
import com.rafaelsoares.marvel.model.data.SuperheroesData
import com.rafaelsoares.marvel.ui.superheroes.SuperHeroesContract
import com.rafaelsoares.marvel.ui.superheroes.SuperHeroesPresenter
import dagger.Module
import dagger.Provides

@Module
class SuperHeroesFragmentModule(private var fragment: Fragment) {

    @Provides
    fun provideActivity():Fragment {
        return fragment
    }

    @Provides
    fun provideMainPresenter(): SuperHeroesContract.Presenter<SuperheroesData> {
        return SuperHeroesPresenter()
    }
}