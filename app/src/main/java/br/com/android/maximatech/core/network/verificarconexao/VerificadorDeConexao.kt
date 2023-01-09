package br.com.android.maximatech.core.network.verificarconexao

import android.net.ConnectivityManager

interface VerificadorDeConexao {
    fun existeConexao(connectivityManager: ConnectivityManager): Boolean
}