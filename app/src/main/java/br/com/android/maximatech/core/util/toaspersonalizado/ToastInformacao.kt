package br.com.android.maximatech.core.util.toaspersonalizado

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import br.com.tisistema.maximatech.R

class ToastInformacao(var view: View, var contex: Context) : ToastPersonalizado {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun toastAlterarBackground(imageView: ImageView) {
        view.background = contex.getDrawable(R.drawable.shape_toast_informacao)
        toastAlterarImagem(imageView)

    }

    private fun toastAlterarImagem(imageView: ImageView) {
        imageView.setImageDrawable(contex.getDrawable(R.drawable.icone_informacao))
    }

}