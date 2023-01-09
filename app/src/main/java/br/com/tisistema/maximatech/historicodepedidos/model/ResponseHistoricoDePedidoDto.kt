package br.com.tisistema.maximatech.historicodepedidos.model

import com.google.gson.annotations.SerializedName

data class ResponseHistoricoDePedidoDto(

    @SerializedName("pedidos")
    var pedidos: List<HistoricoDePedidoDto>? = arrayListOf()

)