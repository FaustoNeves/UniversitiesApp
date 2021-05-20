package br.com.fausto.institutions_app.ui.activity

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fausto.institutions_app.R
import br.com.fausto.institutions_app.model.University
import br.com.fausto.institutions_app.ui.adapter.UniversityAdapter
import br.com.fausto.institutions_app.ui.presenter.MainActivityContract
import br.com.fausto.institutions_app.ui.presenter.MainActivityPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.university_view.view.*

class MainActivity : AppCompatActivity(), UniversityAdapter.OnUniversityListener,
    MainActivityContract.MainActivityView {
    private lateinit var progressBar: ProgressBar
    private lateinit var txtName: EditText
    private lateinit var context: Context
    private lateinit var presenter: MainActivityContract.MainActivityPresenter

    private var listOfUniversities: MutableList<University>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = progressBarMainActivity
        txtName = textSearch
        context = this
        presenter = MainActivityPresenter.create()
        lifecycle.addObserver(presenter as MainActivityPresenter)
    }

    override fun onStart() {
        super.onStart()
        presenter.setupView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    fun btnSearch(view: View) {
        presenter.loadUniversitiesList(txtName.text.toString())
    }

    override fun setProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun endProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setRecyclerView(universitiesList: MutableList<University>) {
        listOfUniversities = universitiesList
        recyclerView.adapter = UniversityAdapter(universitiesList, context, this@MainActivity)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun displayMessage(message: String) {
        Snackbar.make(findViewById(R.id.mainLayout), message, 2000).show()
    }

    override fun onUniversityClick(position: Int) {
        try {
            val universityItem = listOfUniversities!![position]
            val uri = Uri.parse(universityItem.web_pages!![0])
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            displayMessage("Error")
        }
    }
}