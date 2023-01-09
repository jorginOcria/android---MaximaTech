package br.com.android.maximatech.historicodepedidos.model

import br.com.android.maximatech.historicodepedidos.model.HistoricoDePedidoDto
import com.google.gson.annotations.SerializedName

data class ResponseHistoricoDePedidoDto(

    @SerializedName("pedidos")
    var pedidos: List<HistoricoDePedidoDto>? = arrayListOf()

)