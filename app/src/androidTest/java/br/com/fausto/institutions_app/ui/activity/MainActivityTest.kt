package br.com.fausto.institutions_app.ui.activity

import android.app.Instrumentation
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.fausto.institutions_app.R
import br.com.fausto.institutions_app.util.IdleResourceCounter
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityToBeTested: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance()
            .register(IdleResourceCounter.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance()
            .unregister(IdleResourceCounter.countingIdlingResource)
    }

    @Test
    fun showErrorNotification() {
        onView(withId(R.id.button)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Empty institutions name")))
    }

    @Test
    fun searchAndAccessingWebsite() {
        /**
         * For this test, Iam acessing this endpoint
         * http://universities.hipolabs.com/search?name=saint
         * and checking if my intended matcher corresponds to the incoming intent
         **/
        Intents.init()

        onView(withId(R.id.textName)).perform(typeText("saint"))
        closeSoftKeyboard()
        onView(withId(R.id.button)).perform(click())

        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW))
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        intended(expectedIntent)
        Intents.release()
    }
}