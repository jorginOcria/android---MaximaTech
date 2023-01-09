package br.com.android.maximatech.core.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.android.maximatech.core.util.ConnectivityUtil

abstract class AbstractActivity : AppCompatActivity() {

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initConnectivityCallback()
        hideActionBar()
        initControllers()
        runObservers()
    }

    private fun initConnectivityCallback() {
        ConnectivityUtil.getInstance().registerCallback(this)
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    abstract fun initControllers()
    abstract fun runObservers()

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}