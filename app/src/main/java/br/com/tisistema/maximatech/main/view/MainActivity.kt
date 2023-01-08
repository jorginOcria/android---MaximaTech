package br.com.tisistema.maximatech.main.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.core.view.AbstractActivity
import br.com.tisistema.maximatech.main.controller.ControllerMain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity() {

    private lateinit var controllerMain: ControllerMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarCliqueNosBotoes()
    }

    override fun initControllers() {
        controllerMain = ViewModelProvider(this)[ControllerMain::class.java]
    }

    override fun runObservers() {}

    private fun configurarCliqueNosBotoes() {
        activity_main__botao_clientes.setOnClickListener { navegarParaClienteActivity() }
    }

    private fun navegarParaClienteActivity() {
        startActivity(Intent(this, ClienteActivity::class.java))
        finish()
    }

}