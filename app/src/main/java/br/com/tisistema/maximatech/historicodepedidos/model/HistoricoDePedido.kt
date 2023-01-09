package br.com.tisistema.maximatech.historicodepedidos.model

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "historico_de_pedido",
    indices = [Index(value = ["legacy_id"])])
class HistoricoDePedido {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("historico_de_pedido_id")
    @ColumnInfo(name = "historico_de_pedido_id")
    var idHistoricoDePedido: Long? = 0L

    @SerializedName("legacyId")
    @ColumnInfo(name = "legacy_id")
    var legacyId: Long? = 0

    @SerializedName("numeroDoPedidoRca")
    @ColumnInfo(name = "numeroDoPedidoRca")
    var numeroDoPedidoRca: Long? = 0L

    @SerializedName("numeroDoPedidoErp")
    @ColumnInfo(name = "numeroDoPedidoErp")
    var numeroDoPedidoErp: String? = ""

    @SerializedName("codigoDoCliente")
    @ColumnInfo(name = "codigoDoCliente")
    var codigoDoCliente: String? = ""

    @SerializedName("nomeDoCliente")
    @ColumnInfo(name = "nomeDoCliente")
    var nomeDoCliente: String? = ""

    @SerializedName("data")
    @ColumnInfo(name = "data")
    var data: String? = null

    @SerializedName("critica")
    @ColumnInfo(name = "critica")
    var critica: String? = ""

    @SerializedName("status")
    @ColumnInfo(name = "status")
    var status: String? = ""

    @SerializedName("tipo")
    @ColumnInfo(name = "tipo")
    var tipo: String? = ""

    @Ignore
    var legendas: List<String>? = arrayListOf()

}
