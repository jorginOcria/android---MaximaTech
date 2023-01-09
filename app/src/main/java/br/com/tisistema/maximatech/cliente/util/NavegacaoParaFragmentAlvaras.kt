package br.com.tisistema.maximatech.cliente.util

import android.view.MenuItem
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.alvara.FragmentAlvara
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.tiembarque.main.util.NavegacaoEntreFragments

class NavegacaoParaFragmentAlvaras(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentAlvaras = FragmentAlvara()

    override fun navegarEntreFragment() {
        clienteActivity.carregarFragmentAtual(Constants.FRAGMENT_ATUAL.ALVARAS)
        clienteActivity.configurarToolbarNaFragmentAlvaras()
        clienteActivity.mudarDeFragment(fragmentAlvaras)

    }
}