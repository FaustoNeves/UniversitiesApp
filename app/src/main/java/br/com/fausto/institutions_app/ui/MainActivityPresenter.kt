package br.com.fausto.institutions_app.ui

import br.com.fausto.institutions_app.retrofit.UniversityAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityPresenter : MainActivityContract.MainActivityPresenter {

    private lateinit var view: MainActivityContract.MainActivityView
    private val universityService = UniversityAPI().api

    override fun setView(view: MainActivityContract.MainActivityView) {
        this.view = view
    }

    override fun loadUniversitiesList(universityName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            if (universityName.isEmpty()) {
                view.displayMessage("Empty institutions name")
            } else {
                view.setProgressBar()
                val result = universityService.getUniversities(universityName)
                if (result.isSuccessful) {
                    view.setRecyclerView(result.body()!!)
                    view.endProgressBar()
                } else {
                    view.displayMessage("You are offline")
                    view.endProgressBar()
                }
            }
        }
    }
}