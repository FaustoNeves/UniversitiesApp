package br.com.fausto.institutions_app.ui

import br.com.fausto.institutions_app.model.UniversityParsedItem

interface MainActivityContract {

    interface MainActivityView {
        fun setProgressBar()
        fun endProgressBar()
        fun setRecyclerView(universitiesList: MutableList<UniversityParsedItem>)
        fun displayMessage(message: String)
    }

    interface MainActivityPresenter {
        fun setView(view: MainActivityView)
        fun loadUniversitiesList(name: String)
    }
}