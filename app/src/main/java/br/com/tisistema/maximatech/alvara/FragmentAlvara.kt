package br.com.tisistema.maximatech.alvara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.controller.ControllerCliente
import br.com.tisistema.maximatech.core.view.AbstractFragment

class FragmentAlvara : AbstractFragment() {

    private lateinit var controllerAlvara: ControllerAlvara

    override fun initControllers() {
        controllerAlvara = ViewModelProvider(this)[ControllerAlvara::class.java]
    }

    override fun runObservers() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_alvara, container, false)
    }

}