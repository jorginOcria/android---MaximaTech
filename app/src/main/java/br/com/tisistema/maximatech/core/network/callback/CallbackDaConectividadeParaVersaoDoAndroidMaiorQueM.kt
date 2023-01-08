package br.com.tisistema.tiembarque.core.network.callback

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import br.com.tisistema.tiembarque.core.network.listener.ConnectivityChangeListener

class CallbackDaConectividadeParaVersaoDoAndroidMaiorQueM : ConnectivityManager.NetworkCallback(),
    CallbackDaConectividade {

    private val listeners = mutableListOf<ConnectivityChangeListener>()

    companion object {
        private lateinit var instance: CallbackDaConectividadeParaVersaoDoAndroidMaiorQueM
        fun getInstance(): CallbackDaConectividadeParaVersaoDoAndroidMaiorQueM {
            synchronized(CallbackDaConectividadeParaVersaoDoAndroidMaiorQueM::class.java) {
                if (!Companion::instance.isInitialized) {
                    instance =
                        CallbackDaConectividadeParaVersaoDoAndroidMaiorQueM()
                }
            }
            return instance
        }
    }

    override fun registerCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest: NetworkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        notifyListeners(false)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        notifyListeners(true)
    }

    override fun addListener(listener: ConnectivityChangeListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: ConnectivityChangeListener) {
        listeners.remove(listener)
    }

    override fun notifyListeners(isConnected: Boolean) {
        listeners.forEach {
            it.onNetworkConnectionChange(isConnected)
        }
    }
}