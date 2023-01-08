package br.com.tisistema.maximatech.cliente.util

import android.view.MenuItem
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.cliente.view.FragmentDadosDoCliente
import br.com.tisistema.tiembarque.main.util.NavegacaoEntreFragments

class NavegacaoParaFragmentDados(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentDadosDoCliente = FragmentDadosDoCliente()

    override fun navegarEntreFragment() {
        clienteActivity.mudarDeFragment(fragmentDadosDoCliente)
    }

}