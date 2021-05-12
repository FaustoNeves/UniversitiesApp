package br.com.fausto.institutions_app.ui

import br.com.fausto.institutions_app.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockMainActivity: MainActivityContract.MainActivityView

    private lateinit var mainPresenter: MainActivityPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainPresenter = MainActivityPresenter()
        mainPresenter.setView(mockMainActivity)
    }

    @Test
    fun searchForUniversities() {
        mainPresenter.loadUniversitiesList("")
        verify(mockMainActivity).displayMessage("Empty institutions name")
    }
}