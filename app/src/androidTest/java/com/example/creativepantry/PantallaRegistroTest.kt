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

    @Test
    fun emailVacio_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("1234"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("El email no puede estar vacío")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun emailFormatoInvalido_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("correo_invalido"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("1234"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("1234"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("Correo electrónico inválido")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun contrasenaCorta_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("12"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("12"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("Las contraseñas es demasiado corta")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun contrasenaMuyLarga_muestraErrorTextView() {
        val contrasenaLarga = "a".repeat(101)
        onView(withId(R.id.nomUsuari)).perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("correo@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText(contrasenaLarga), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText(contrasenaLarga), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("Las contraseñas es demasiado larga")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun todosLosCamposVacios_muestraErrorNombreUsuario() {
        onView(withId(R.id.nomUsuari)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText(""), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("El nombre de usuario no puede estar vacío")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun confirmacionContrasenaVacia_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("Joan"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("joan@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("123456"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText(""), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("Las contraseñas no coinciden")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun emailConCaracteresEspeciales_muestraErrorTextView() {
        onView(withId(R.id.nomUsuari)).perform(replaceText("Sara"), closeSoftKeyboard())
        onView(withId(R.id.correu)).perform(replaceText("sara!@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.contrasenya)).perform(replaceText("secure123"), closeSoftKeyboard())
        onView(withId(R.id.repetirContrasenya)).perform(replaceText("secure123"), closeSoftKeyboard())

        onView(withId(R.id.btnregistro)).perform(click())

        onView(withId(R.id.txtError))
            .check(matches(withText("Correo electrónico inválido")))
            .check(matches(isDisplayed()))
    }
}
