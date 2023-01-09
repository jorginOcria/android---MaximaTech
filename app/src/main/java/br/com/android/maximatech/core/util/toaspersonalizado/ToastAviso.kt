package br.com.android.maximatech.core.util.toaspersonalizado

import android.content.Context
import android.view.View
import android.widget.ImageView
import br.com.tisistema.maximatech.R

class ToastAviso(var view: View, var contex: Context) : ToastPersonalizado {

    override fun toastAlterarBackground(imageView: ImageView) {
        view.background = contex.getDrawable(R.drawable.shape_toast_aviso)
        toastAlterarImagem(imageView)
    }

    private fun toastAlterarImagem(imageView: ImageView) {
        imageView.setImageDrawable(contex.getDrawable(R.drawable.icone_warning))
    }

}