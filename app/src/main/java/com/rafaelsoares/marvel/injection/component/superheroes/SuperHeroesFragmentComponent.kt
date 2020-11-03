package com.rafaelsoares.marvel.injection.component.superheroes

import com.rafaelsoares.marvel.injection.module.superheroes.SuperHeroesFragmentModule
import com.rafaelsoares.marvel.ui.superheroes.SuperHeroesFragment
import dagger.Component

@Component(modules = [SuperHeroesFragmentModule::class])
interface SuperHeroesFragmentComponent {
    fun inject(fragment: SuperHeroesFragment)
}