package br.com.tisistema.maximatech.cliente.util

import android.view.MenuItem
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.tiembarque.main.util.NavegacaoEntreFragments

class NavegacaoEntreFragmentSimpleFactory {

    companion object {

        fun clickNosBotoesDoMenuPrincipal(
            menuItem: MenuItem,
            viewActivityClient: ClienteActivity
        ): NavegacaoEntreFragments {
            return when (menuItem.itemId) {
                R.id.item_dados -> NavegacaoParaFragmentDadosDoCliente(viewActivityClient, menuItem)
                R.id.item_historico_de_pedidos -> NavegacaoParaFragmentHistoricoDePedidos(viewActivityClient, menuItem)
                R.id.item_alvaras -> NavegacaoParaFragmentAlvaras(viewActivityClient, menuItem)
                else -> {
                    NavegacaoParaFragmentDadosDoCliente(viewActivityClient, menuItem)
                }
            }
            true
        }
    }

}
