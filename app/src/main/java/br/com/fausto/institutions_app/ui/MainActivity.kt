package br.com.fausto.institutions_app.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fausto.institutions_app.R
import br.com.fausto.institutions_app.model.UniversityParsed
import br.com.fausto.institutions_app.model.UniversityParsedItem
import br.com.fausto.institutions_app.retrofit.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), UniversityAdapter.OnUniversityListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var txtName: EditText
    lateinit var context: Context

    //    lateinit var repository: UniversityRepository
    private var listOfUniversities: MutableList<UniversityParsedItem>? = null
    val universityService = RetrofitBuilder().universityService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = progressBarMainActivity
        txtName = edit_text_search
        context = this
//        repository = UniversityRepository(AppDatabase.getInstance(this).universityDao)
    }

    fun btnSearch(view: View) {
//        progressBar.visibility = View.VISIBLE
        val universitiesList = loadUniversitiesList(txtName.text.toString())
//        recyclerView.adapter = universitiesList?.let {
//            UniversityAdapter(
//                it, context, this@MainActivity
//            )
//        }
//        recyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun loadUniversitiesList(name: String): MutableList<UniversityParsedItem>? {
        var universitiesList: MutableList<UniversityParsedItem>? = null
        universityService.getUniversities(name).enqueue(object : Callback<UniversityParsed> {
            override fun onResponse(
                call: Call<UniversityParsed>,
                response: Response<UniversityParsed>
            ) {
                if (response.isSuccessful) {
                    universitiesList = response.body()
                    recyclerView.adapter = UniversityAdapter(universitiesList!!, context, this@MainActivity)
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                } else {
                    Toast.makeText(
                        context,
                        "Connect to search for universities",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UniversityParsed>, t: Throwable) {
                Toast.makeText(context, "Impossible to handle", Toast.LENGTH_SHORT).show()
            }

        })
        return universitiesList
    }

    override fun onUniversityClick(position: Int) {
        val universityItem = listOfUniversities!![position]
        val uri = Uri.parse(universityItem.web_pages!![0])
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}