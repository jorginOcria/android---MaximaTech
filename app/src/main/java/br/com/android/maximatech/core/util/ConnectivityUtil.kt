package br.com.android.maximatech.core.util

import android.content.Context
import android.net.ConnectivityManager
import br.com.android.maximatech.core.network.ConectividadeFactory

class ConnectivityUtil private constructor() {

    private val connectivityChecker = ConectividadeFactory.CHECKER.getVerificadorDeConectividade()
    val connectivityCallback = ConectividadeFactory.CALLBACK.getConectividade()

    companion object {
        private lateinit var instance: ConnectivityUtil

        fun getInstance(): ConnectivityUtil {
            synchronized(ConnectivityUtil::class.java) {
                if (!Companion::instance.isInitialized) {
                    instance = ConnectivityUtil()
                }
            }
            return instance
        }
    }

    fun registerCallback(context: Context) {
        connectivityCallback.registerCallback(context)
    }

    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityChecker.existeConexao(connectivityManager)
    }
}