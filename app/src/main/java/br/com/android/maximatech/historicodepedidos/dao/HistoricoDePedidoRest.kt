package br.com.android.maximatech.historicodepedidos.dao

import br.com.android.maximatech.historicodepedidos.model.ResponseHistoricoDePedidoDto
import retrofit2.Call
import retrofit2.http.GET

interface HistoricoDePedidoRest {

    @GET("pedido")
    fun getHistoricoDePedido(): Call<ResponseHistoricoDePedidoDto>
}