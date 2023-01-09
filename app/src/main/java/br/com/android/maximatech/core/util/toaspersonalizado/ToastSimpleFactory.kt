package br.com.android.maximatech.core.util.toaspersonalizado

import android.content.Context
import android.view.View
import br.com.android.maximatech.core.cross.Constants

class ToastSimpleFactory {
    companion object {
        fun getToast(
            tipo: Int, view: View, context: Context
        ): ToastPersonalizado {
            return when (tipo) {
                Constants.TOAST.SUCESSO -> ToastSucesso(view, context)
                Constants.TOAST.ERRO -> ToastErro(view, context)
                Constants.TOAST.AVISO -> ToastAviso(view, context)
                Constants.TOAST.INFORMACAO -> ToastInformacao(view, context)
                else -> {
                    ToastErro(view, context)
                }
            }

        }
    }
}