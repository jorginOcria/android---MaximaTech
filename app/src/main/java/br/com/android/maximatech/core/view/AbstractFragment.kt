package br.com.android.maximatech.core.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import br.com.tisistema.maximatech.R
import br.com.android.maximatech.core.util.toaspersonalizado.ToastSimpleFactory

abstract class AbstractFragment : Fragment() {

    private var toast: Toast? = null
    private lateinit var fragmentView: View
    private var dialogLoading = DialogLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onPause() {
        super.onPause()
        hideToast()
    }

    private fun init() {
        initControllers()
        runObservers()
    }

    abstract fun initControllers()
    abstract fun runObservers()

    fun showToast(message: String, duration: Int) {
        this.toast = Toast.makeText(context, message, duration)
        this.toast!!.show()
    }

    private fun hideToast() {
        this.toast.let {
            it?.cancel()
        }
    }

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

    fun carregarFragmentView(fragmentView: View) {
        this.fragmentView = fragmentView
    }

    fun showToast(tipo: Int, mensagem: String) {
        val viewGroup = fragmentView.findViewById<ViewGroup>(R.id.container_toast)
        val viewToastPersonalizado: View =
            layoutInflater.inflate(R.layout.toast_personalizado, viewGroup)
        val imagemView: ImageView =
            viewToastPersonalizado.findViewById<ImageView>(R.id.toast_personalizado__imagem)
        ToastSimpleFactory.getToast(tipo, viewToastPersonalizado, requireActivity())
            .toastAlterarBackground(imagemView)
        viewToastPersonalizado.findViewById<TextView>(R.id.toast_personalizado__mensagem).text =
            mensagem
        this.toast = Toast(requireActivity())
        this.toast!!.view = viewToastPersonalizado
        this.toast!!.duration = Toast.LENGTH_LONG
        this.toast!!.show()
    }

    fun executarDialogLoading() {
        criarDialogLoading()
        mostrarDialogCarregando()
    }

    private fun criarDialogLoading() {
        dialogLoading = DialogLoading()
    }

    private fun mostrarDialogCarregando() {
        val fragManager: FragmentManager = activity!!.supportFragmentManager
        dialogLoading.show(fragManager, "tag")
    }

    fun esconderDialogLoading() {
        if (dialogLoading.isVisible) {
            dialogLoading.dismiss()
        }
    }

}
