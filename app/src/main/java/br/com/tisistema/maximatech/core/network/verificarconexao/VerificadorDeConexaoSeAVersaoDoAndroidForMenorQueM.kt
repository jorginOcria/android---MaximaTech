package br.com.tisistema.tiembarque.core.network.verificarconexao

import android.net.ConnectivityManager
import br.com.tisistema.maximatech.core.network.callback.CallbackDaConectividadeParaVersaoDoAndroidMenorQueM

class VerificadorDeConexaoSeAVersaoDoAndroidForMenorQueM private constructor() : VerificadorDeConexao {

    companion object {
        private lateinit var instance: VerificadorDeConexaoSeAVersaoDoAndroidForMenorQueM

        fun getInstance(): VerificadorDeConexaoSeAVersaoDoAndroidForMenorQueM {
            synchronized(CallbackDaConectividadeParaVersaoDoAndroidMenorQueM::class.java) {
                if (!Companion::instance.isInitialized) {
                    instance =
                        VerificadorDeConexaoSeAVersaoDoAndroidForMenorQueM()
                }
            }
            return instance
        }
    }

    override fun existeConexao(connectivityManager: ConnectivityManager): Boolean {
        var result = false
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }
}