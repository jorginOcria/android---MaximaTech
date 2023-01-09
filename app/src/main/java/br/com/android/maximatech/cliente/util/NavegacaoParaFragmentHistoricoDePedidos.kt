package br.com.android.maximatech.cliente.util

import android.view.MenuItem
import br.com.android.maximatech.cliente.view.ClienteActivity
import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.historicodepedidos.view.FragmentHistoricoDePedidos

class NavegacaoParaFragmentHistoricoDePedidos(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentHistoricoDePedidos = FragmentHistoricoDePedidos()

    override fun navegarEntreFragment() {
        clienteActivity.carregarFragmentAtual(Constants.FRAGMENT_ATUAL.HISTORICO_DE_PEDIDO)
        clienteActivity.configurarToolbarNaFragmentHistoricoDePedido()
        clienteActivity.mudarDeFragment(fragmentHistoricoDePedidos)

    }
}