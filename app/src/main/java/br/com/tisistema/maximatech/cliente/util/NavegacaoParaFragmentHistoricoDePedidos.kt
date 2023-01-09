package br.com.tisistema.maximatech.cliente.util

import android.view.MenuItem
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.historicodepedidos.view.FragmentHistoricoDePedidos
import br.com.tisistema.tiembarque.main.util.NavegacaoEntreFragments

class NavegacaoParaFragmentHistoricoDePedidos(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentHistoricoDePedidos = FragmentHistoricoDePedidos()

    override fun navegarEntreFragment() {
        clienteActivity.carregarFragmentAtual(Constants.FRAGMENT_ATUAL.HISTORICO_DE_PEDIDO)
        clienteActivity.configurarToolbarNaFragmentHistoricoDePedido()
        clienteActivity.mudarDeFragment(fragmentHistoricoDePedidos)

    }
}