package br.com.tisistema.maximatech.dadosdocliente.model

import androidx.room.*
import br.com.tisistema.maximatech.contato.model.Contato
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "cliente",
    indices = [Index(value = ["legacy_id"])]
)
class DadosDoCliente {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("cliente_id")
    @ColumnInfo(name = "cliente_id")
    var idCliente: Long? = 0L

    @SerializedName("legacyId")
    @ColumnInfo(name = "legacy_id")
    var legacyId: Long? = 0

    @SerializedName("codigo")
    @ColumnInfo(name = "codigo")
    var codigo: String? = ""

    @SerializedName("razaoSocial")
    @ColumnInfo(name = "razaoSocial")
    var razaoSocial: String? = ""

    @SerializedName("nomeFantasia")
    @ColumnInfo(name = "nomeFantasia")
    var nomeFantasia: String? = ""

    @SerializedName("cpf")
    @ColumnInfo(name = "cpf")
    var cpf: String? = ""

    @SerializedName("cnpj")
    @ColumnInfo(name = "cnpj")
    var cnpj: String? = ""

    @SerializedName("ramoAtividade")
    @ColumnInfo(name = "ramoAtividade")
    var ramoAtividade: String? = ""

    @SerializedName("endereco")
    @ColumnInfo(name = "endereco")
    var endereco: String? = ""

    @SerializedName("status")
    @ColumnInfo(name = "status")
    var status: String? = ""

    @Ignore
    var contatos: ArrayList<Contato>? = arrayListOf()

}