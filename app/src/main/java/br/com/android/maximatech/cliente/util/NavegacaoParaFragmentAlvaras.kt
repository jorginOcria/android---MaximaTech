package br.com.android.maximatech.cliente.util

import android.view.MenuItem
import br.com.android.maximatech.cliente.view.ClienteActivity
import br.com.android.maximatech.alvara.FragmentAlvara
import br.com.android.maximatech.core.cross.Constants

class NavegacaoParaFragmentAlvaras(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentAlvaras = FragmentAlvara()

    override fun navegarEntreFragment() {
        clienteActivity.carregarFragmentAtual(Constants.FRAGMENT_ATUAL.ALVARAS)
        clienteActivity.configurarToolbarNaFragmentAlvaras()
        clienteActivity.mudarDeFragment(fragmentAlvaras)

    }
}