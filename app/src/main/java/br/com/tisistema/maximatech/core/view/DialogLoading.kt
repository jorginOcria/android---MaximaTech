package br.com.tisistema.maximatech.core.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.tisistema.maximatech.R

class DialogLoading : DialogFragment() {

    private lateinit var dialogLoadingView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogLoadingView = inflater.inflate(R.layout.dialog_loading, container)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialogLoadingView
    }

    fun getStatusDialog(): Boolean {
        return !this.isVisible && !this.isAdded
    }
}