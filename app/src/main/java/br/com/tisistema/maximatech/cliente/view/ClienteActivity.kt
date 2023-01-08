package br.com.tisistema.maximatech.cliente.view

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.controller.ControllerCliente
import br.com.tisistema.maximatech.cliente.util.NavegacaoEntreFragmentSimpleFactory
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.core.view.AbstractActivity
import br.com.tisistema.maximatech.main.view.MainActivity

class ClienteActivity : AbstractActivity() {

    private lateinit var controllerCliente: ControllerCliente
    private val fragmentDadosDoCliente = FragmentDadosDoCliente()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)
        configurarNomeToolbar()
        configurarCorDosTextosDoMenuPrincipal()
        mudarDeFragment(fragmentDadosDoCliente)
        cliqueNosBotoesDoMenuParaNavegarParaOutraFragment()
    }

    private fun configurarNomeToolbar(){
        var toolbar: androidx.appcompat.widget.Toolbar =
            findViewById(R.id.activity_cliente__toolbar)
        toolbar.title = getString(R.string.activity_cliente__nome_na_toolbar_dados_do_cliente)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun initControllers() {
        controllerCliente = ViewModelProvider(this)[ControllerCliente::class.java]
    }

    override fun runObservers() {
    }

    override fun onBackPressed() {
        navegarParaMainActivity()
    }

    private fun configurarCorDosTextosDoMenuPrincipal() {
        var botaoDeNavegacaoDoMenu: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.botao_de_navegacao_do_menu_principal)
        botaoDeNavegacaoDoMenu.itemIconTintList = getColorStateList()
        botaoDeNavegacaoDoMenu.itemTextColor = getColorStateList()
    }

    private fun getColorStateList(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf(-android.R.attr.state_selected)
            ),
            intArrayOf(
                Color.parseColor(Constants.CORES_DOS_ITENS_DO_MENU_PRINCIPAL.ITEM_ATIVO),
                Color.parseColor(Constants.CORES_DOS_ITENS_DO_MENU_PRINCIPAL.ITEM_INATIVO)
            )
        )
    }

    private fun cliqueNosBotoesDoMenuParaNavegarParaOutraFragment() {
        var botaoDeNavegacaoDoMenu: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.botao_de_navegacao_do_menu_principal)
        botaoDeNavegacaoDoMenu.setOnItemSelectedListener {
            NavegacaoEntreFragmentSimpleFactory.clickNosBotoesDoMenuPrincipal(
                it,
                this
            ).navegarEntreFragment()
            true
        }
    }

    fun mudarDeFragment(fragmet: Fragment) {
        val transacaoDeFragment = supportFragmentManager.beginTransaction()
        transacaoDeFragment.replace(R.id.fragment_container, fragmet)
        transacaoDeFragment.commit()
    }

    private fun navegarParaMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}