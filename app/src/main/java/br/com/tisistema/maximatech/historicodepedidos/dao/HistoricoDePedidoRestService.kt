package br.com.tisistema.maximatech.historicodepedidos.dao

import br.com.tisistema.maximatech.core.cross.listener.CommonsHTTPListener
import br.com.tisistema.maximatech.core.cross.listener.RestCallListener
import br.com.tisistema.maximatech.core.dao.RetrofitClient
import br.com.tisistema.maximatech.historicodepedidos.model.ResponseHistoricoDePedidoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoricoDePedidoRestService(private val commonsHTTPStatusListener: CommonsHTTPListener) {

    private val historicoDePedidoRest =
        RetrofitClient.createService(
            HistoricoDePedidoRest::class.java,
            commonsHTTPStatusListener
        )

    fun getHistoricoDePedido(
        restCallListener: RestCallListener<ResponseHistoricoDePedidoDto>
    ) {
        val call = historicoDePedidoRest.getHistoricoDePedido()
        call.enqueue(object : Callback<ResponseHistoricoDePedidoDto> {
            override fun onFailure(
                call: Call<ResponseHistoricoDePedidoDto>,
                throwable: Throwable
            ) {
                restCallListener.onFailure(throwable)
            }

            override fun onResponse(
                call: Call<ResponseHistoricoDePedidoDto>,
                responseDesvincularDaViagem: Response<ResponseHistoricoDePedidoDto>
            ) {
                restCallListener.onSuccess(
                    responseDesvincularDaViagem.body(),
                    responseDesvincularDaViagem.code()
                )
            }

        })
    }
}