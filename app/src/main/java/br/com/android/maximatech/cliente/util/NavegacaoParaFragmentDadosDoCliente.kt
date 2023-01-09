package br.com.android.maximatech.cliente.util

import android.view.MenuItem
import br.com.android.maximatech.cliente.view.ClienteActivity
import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.dadosdocliente.view.FragmentDadosDoCliente

class NavegacaoParaFragmentDadosDoCliente(var clienteActivity: ClienteActivity, var menuItem: MenuItem) :
    NavegacaoEntreFragments {

    private val fragmentDadosDoCliente = FragmentDadosDoCliente()

    override fun navegarEntreFragment() {
        clienteActivity.carregarFragmentAtual(Constants.FRAGMENT_ATUAL.DADOS_DO_CLIENTE)
        clienteActivity.configurarToolbarNaFragmentDadosDoCliente()
        clienteActivity.mudarDeFragment(fragmentDadosDoCliente)
    }

}