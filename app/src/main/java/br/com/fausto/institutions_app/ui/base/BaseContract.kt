package br.com.fausto.institutions_app.ui.base

interface BaseContract {

    interface View

    interface Presenter<T : View> {

        fun setupView(view: T)

        fun dropView()
    }
}