package br.com.tisistema.maximatech.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.core.view.AbstractActivity
import br.com.tisistema.maximatech.main.view.MainActivity
import br.com.tisistema.maximatech.splash.controller.ControllerSplash

class SplashActivity : AbstractActivity() {

    private lateinit var controllerSplash: ControllerSplash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navegarParaMainActivity()
    }

    override fun initControllers() {
        controllerSplash = ViewModelProvider(this)[ControllerSplash::class.java]
    }

    override fun runObservers() {
    }

    private fun navegarParaMainActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, Constants.SISTEMA.TEMPO_DE_SPLASH)
    }

}