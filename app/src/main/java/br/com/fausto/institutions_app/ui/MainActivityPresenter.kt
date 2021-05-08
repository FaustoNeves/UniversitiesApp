package br.com.fausto.institutions_app.ui

import br.com.fausto.institutions_app.model.UniversityParsed
import br.com.fausto.institutions_app.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter : MainActivityContract.MainActivityPresenter {

    private lateinit var view: MainActivityContract.MainActivityView
    private val universityService = RetrofitBuilder().universityService()

    override fun setView(view: MainActivityContract.MainActivityView) {
        this.view = view
    }

    override fun loadUniversitiesList(name: String) {
        view.setProgressBar()
        universityService.getUniversities(name).enqueue(object : Callback<UniversityParsed> {
            override fun onResponse(
                call: Call<UniversityParsed>,
                response: Response<UniversityParsed>
            ) {
                if (response.isSuccessful) {
                    view.setRecyclerView(response.body()!!)
                    view.endProgressBar()
                } else {
                    view.displayMessage("You are offline")
                    view.endProgressBar()
                }
            }

            override fun onFailure(call: Call<UniversityParsed>, t: Throwable) {
                view.displayMessage("Out of service")
                view.endProgressBar()
            }
        })
    }
}