package br.com.tisistema.maximatech.historicodepedidos.model

import br.com.tisistema.maximatech.contato.model.ContatoDto
import com.google.gson.annotations.SerializedName

data class HistoricoDePedidoDto(

    @SerializedName("numero_ped_Rca")
    var numeroDoPedidoRca: Long? = 0L,

    @SerializedName("numero_ped_erp")
    var numeroDoPedidoErp: String? = "",

    @SerializedName("codigoCliente")
    var codigoDoCliente: String? = "",

    @SerializedName("NOMECLIENTE")
    var nomeDoCliente: String? = "",

    @SerializedName("data")
    var data: String? = null,

    @SerializedName("critica")
    var critica: String? = "",

    @SerializedName("tipo")
    var tipo: String? = "",

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("legendas")
    var legendas: List<String>? = arrayListOf()

)