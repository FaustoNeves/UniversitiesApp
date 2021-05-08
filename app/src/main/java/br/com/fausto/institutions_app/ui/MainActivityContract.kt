package br.com.fausto.institutions_app.ui

interface MainActivityContract {
    interface MainActivityView {

    }

    interface MainActivityPresenter {

        fun setView(view: MainActivityView)

    }
}