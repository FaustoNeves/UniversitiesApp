package br.com.fausto.institutions_app.ui.presenter

import android.util.Log
import br.com.fausto.institutions_app.retrofit.UniversityAPI
import br.com.fausto.institutions_app.retrofit.UniversityService
import br.com.fausto.institutions_app.ui.base.LifecycleScope
import kotlinx.coroutines.launch

class MainActivityPresenter(private val universityService: UniversityService) :
    MainActivityContract.MainActivityPresenter, LifecycleScope() {

    companion object {
        fun create(universityService: UniversityService? = UniversityAPI().createAPI()): MainActivityPresenter {
            return MainActivityPresenter(universityService!!)
        }
    }

    private var view: MainActivityContract.MainActivityView? = null

    override fun loadUniversitiesList(universityName: String) {
        /**To make tests easier, I decided to remove the Response type on the service layer. Because of that, I cant
         * check attributes isSuccessful or statusCode because there is no more http response getting fetched.
         * To cover this, I wrapped everything inside of a try catch block
         * */
        launch {
            if (universityName.isEmpty()) {
                view!!.displayMessage("Empty institutions name")
            } else {
                view!!.setProgressBar()
                try {
                    val result = universityService.getUniversities(universityName)
                    view!!.setRecyclerView(result)
                    view!!.endProgressBar()
                } catch (exception: Exception) {
                    view!!.displayMessage("You are offline")
                    view!!.endProgressBar()
                    Log.e("exception", exception.toString())
                }
            }
        }
    }

    override fun setupView(view: MainActivityContract.MainActivityView) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }
}