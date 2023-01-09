package br.com.tisistema.maximatech.dadosdocliente.dao

import br.com.tisistema.maximatech.dadosdocliente.model.ResponseDadosDoClienteDto
import br.com.tisistema.maximatech.core.cross.listener.CommonsHTTPListener
import br.com.tisistema.maximatech.core.cross.listener.RestCallListener
import br.com.tisistema.maximatech.core.dao.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarDadosDoClienteRestService(private val commonsHTTPStatusListener: CommonsHTTPListener) {

    private val buscarClienteRest =
        RetrofitClient.createService(
            BuscarDadosDoClienteRest::class.java,
            commonsHTTPStatusListener
        )

    fun getCliente(
        restCallListener: RestCallListener<ResponseDadosDoClienteDto>
    ) {
        val call = buscarClienteRest.getCliente()
        call.enqueue(object : Callback<ResponseDadosDoClienteDto> {
            override fun onFailure(
                call: Call<ResponseDadosDoClienteDto>,
                throwable: Throwable
            ) {
                restCallListener.onFailure(throwable)
            }

            override fun onResponse(
                call: Call<ResponseDadosDoClienteDto>,
                responseDadosDoDesvincularDaViagem: Response<ResponseDadosDoClienteDto>
            ) {
                restCallListener.onSuccess(
                    responseDadosDoDesvincularDaViagem.body(),
                    responseDadosDoDesvincularDaViagem.code()
                )
            }

        })
    }
}