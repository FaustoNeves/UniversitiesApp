package br.com.fausto.institutions_app.ui.presenter

import br.com.fausto.institutions_app.model.University
import br.com.fausto.institutions_app.ui.base.BaseContract

interface MainActivityContract : BaseContract {

    interface MainActivityView : BaseContract.View {
        fun setProgressBar()
        fun endProgressBar()
        fun setRecyclerView(universitiesList: MutableList<University>)
        fun displayMessage(message: String)
    }

    interface MainActivityPresenter : BaseContract.Presenter<MainActivityView> {
        fun loadUniversitiesList(universityName: String)
    }
}