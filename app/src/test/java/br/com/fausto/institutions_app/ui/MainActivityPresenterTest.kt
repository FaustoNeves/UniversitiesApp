package br.com.fausto.institutions_app.ui

import br.com.fausto.institutions_app.model.UniversityResponse
import br.com.fausto.institutions_app.model.University
import br.com.fausto.institutions_app.retrofit.UniversityService
import br.com.fausto.institutions_app.ui.presenter.MainActivityContract
import br.com.fausto.institutions_app.ui.presenter.MainActivityPresenter
import br.com.fausto.institutions_app.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var mockMainActivity: MainActivityContract.MainActivityView = mock()

    private var universityService: UniversityService = mock()

    private var mainPresenter = MainActivityPresenter.create(universityService)

    @Before
    fun initializeSetup() {
        MockitoAnnotations.openMocks(this)
        mainPresenter.setupView(mockMainActivity)
    }

    @After
    fun dropSetup() {
        mainPresenter.dropView()
    }

    @Test
    fun universitiesInvalidSearch() {
        mainPresenter.loadUniversitiesList("")
        verify(mockMainActivity).displayMessage("Empty institutions name")
    }

    @Test
    fun universitiesValidSearch() {
        runBlocking {
            val universityParsed = UniversityResponse()
            universityParsed.addAll(generateFakeList())
            whenever(universityService.getUniversities("mit")).thenReturn(universityParsed)
            mainPresenter.loadUniversitiesList("mit")
            verify(mockMainActivity).setProgressBar()
            verify(mockMainActivity).setRecyclerView(universityParsed)
        }
    }

    private fun generateFakeList(): MutableList<University> {
        return mutableListOf(
            University(
                "alpha_two_code",
                "United States",
                "US College",
                listOf("www.uscollege.com")
            ),
            University(
                "alpha_two_code",
                "United States",
                "US College",
                listOf("www.uscollege.com")
            ),
            University(
                "alpha_two_code",
                "United States",
                "US College",
                listOf("www.uscollege.com")
            )
        )
    }
}