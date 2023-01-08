package br.com.tisistema.maximatech.core.network.callback

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import br.com.tisistema.maximatech.core.util.ConnectivityUtil
import br.com.tisistema.tiembarque.core.network.callback.CallbackDaConectividade
import br.com.tisistema.tiembarque.core.network.listener.ConnectivityChangeListener


class CallbackDaConectividadeParaVersaoDoAndroidMenorQueM private constructor() : BroadcastReceiver(),
    CallbackDaConectividade {

    private val listeners = mutableListOf<ConnectivityChangeListener>()

    companion object {
        private lateinit var instance: CallbackDaConectividadeParaVersaoDoAndroidMenorQueM
        fun getInstance(): CallbackDaConectividadeParaVersaoDoAndroidMenorQueM {
            synchronized(CallbackDaConectividadeParaVersaoDoAndroidMenorQueM::class) {
                if (!Companion::instance.isInitialized) {
                    instance =
                        CallbackDaConectividadeParaVersaoDoAndroidMenorQueM()
                }
            }
            return instance
        }
    }

    override fun registerCallback(context: Context) {
        context.registerReceiver(this, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onReceive(context: Context, intent: Intent?) {
        notifyListeners(ConnectivityUtil.getInstance().isConnectionAvailable(context))
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