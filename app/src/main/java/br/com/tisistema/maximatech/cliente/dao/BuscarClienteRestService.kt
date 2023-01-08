package br.com.tisistema.maximatech.cliente.dao

import br.com.tisistema.maximatech.cliente.model.ResponseClienteDto
import br.com.tisistema.maximatech.core.cross.listener.CommonsHTTPListener
import br.com.tisistema.maximatech.core.cross.listener.RestCallListener
import br.com.tisistema.maximatech.core.dao.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarClienteRestService(private val commonsHTTPStatusListener: CommonsHTTPListener) {

    private val buscarClienteRest =
        RetrofitClient.createService(
            BuscarClienteRest::class.java,
            commonsHTTPStatusListener
        )

    fun getCliente(
        restCallListener: RestCallListener<ResponseClienteDto>
    ) {
        val call = buscarClienteRest.getCliente()
        call.enqueue(object : Callback<ResponseClienteDto> {
            override fun onFailure(
                call: Call<ResponseClienteDto>,
                throwable: Throwable
            ) {
                restCallListener.onFailure(throwable)
            }

            override fun onResponse(
                call: Call<ResponseClienteDto>,
                responseDesvincularDaViagem: Response<ResponseClienteDto>
            ) {
                restCallListener.onSuccess(
                    responseDesvincularDaViagem.body(),
                    responseDesvincularDaViagem.code()
                )
            }

        })
    }
}