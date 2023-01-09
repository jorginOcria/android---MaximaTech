package br.com.tisistema.maximatech.historicodepedidos.dao

import br.com.tisistema.maximatech.historicodepedidos.model.ResponseHistoricoDePedidoDto
import retrofit2.Call
import retrofit2.http.GET

interface HistoricoDePedidoRest {

    @GET("pedido")
    fun getHistoricoDePedido(): Call<ResponseHistoricoDePedidoDto>
}