package com.example.creativepantry

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PantallaRegistroTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(PantallaRegistro::class.java)

    @Test
    fun registroCorrecto_navegaPantallaLogin() {
        onView(withId(R.id.nomUsuari)).perform(typeText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(typeText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(typeText("1234"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withText("Registrado correctamente"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun nombreUsuarioVacio_muestraErrorToast() {
        onView(withId(R.id.nomUsuari)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(typeText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(typeText("1234"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withText("El nombre de usuario no puede estar vacío"))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

}
