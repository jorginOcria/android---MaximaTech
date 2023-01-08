package br.com.tisistema.tiembarque.core.network.verificarconexao

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class VerificadorDeConexaoSeAVersaoDoAndroidForMaiorQueM private constructor() : VerificadorDeConexao {

    companion object {
        private lateinit var instance: VerificadorDeConexaoSeAVersaoDoAndroidForMaiorQueM
        fun getInstance(): VerificadorDeConexaoSeAVersaoDoAndroidForMaiorQueM {
            synchronized(VerificadorDeConexaoSeAVersaoDoAndroidForMaiorQueM::class.java) {
                if (!Companion::instance.isInitialized) {
                    instance =
                        VerificadorDeConexaoSeAVersaoDoAndroidForMaiorQueM()
                }
            }
            return instance
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun existeConexao(connectivityManager: ConnectivityManager): Boolean {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val netwotkCapabilities =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            netwotkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            netwotkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}