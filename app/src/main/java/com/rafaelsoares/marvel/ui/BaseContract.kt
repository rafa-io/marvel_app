package com.rafaelsoares.marvel.ui

import android.content.Context

class BaseContract {

    interface Presenter<in T> {
        fun subscribe()

        fun unsubscribe()

        fun attach(view: T, context: Context)

        fun detach()
    }

    interface View {
        fun progress(isActive: Boolean)

        fun show(title: String, message: String)
    }
}