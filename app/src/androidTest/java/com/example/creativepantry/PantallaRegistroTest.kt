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
    fun nombreUsuarioVacio_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("1234"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("El nombre de usuario no puede estar vacío")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun contrasenasNoCoinciden_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("4321"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("Las contraseñas no coinciden")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun registroCorrecto_ocultaErrorTextViewYToast() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("1234"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("")))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}
