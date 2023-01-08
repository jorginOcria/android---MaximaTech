package br.com.tisistema.tiembarque.core.network.verificarconexao

import android.net.ConnectivityManager

interface VerificadorDeConexao {
    fun existeConexao(connectivityManager: ConnectivityManager): Boolean
}