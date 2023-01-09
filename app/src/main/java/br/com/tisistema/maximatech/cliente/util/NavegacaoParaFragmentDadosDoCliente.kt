package br.com.tisistema.maximatech.cliente.util

import android.view.MenuItem
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.dadosdocliente.view.FragmentDadosDoCliente
import br.com.tisistema.tiembarque.main.util.NavegacaoEntreFragments

class NavegacaoParaFragmentDadosDoCliente(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentDadosDoCliente = FragmentDadosDoCliente()

    override fun navegarEntreFragment() {
        clienteActivity.carregarFragmentAtual(Constants.FRAGMENT_ATUAL.DADOS_DO_CLIENTE)
        clienteActivity.configurarToolbarNaFragmentDadosDoCliente()
        clienteActivity.mudarDeFragment(fragmentDadosDoCliente)
    }

}