package br.com.tisistema.maximatech.contato.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "contato",
    indices = [Index(value = ["legacy_id"])])
class Contato {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("contato_id")
    @ColumnInfo(name = "contato_id")
    var idContato: Long? = 0L

    @SerializedName("legacyId")
    @ColumnInfo(name = "legacy_id")
    var legacyId: Long? = 0

    @SerializedName("idCliente")
    @ColumnInfo(name = "id_cliente")
    var idCliente: Long? = 0L

    @SerializedName("nome")
    @ColumnInfo(name = "nome")
    var nome: String? = ""

    @SerializedName("telefone")
    @ColumnInfo(name = "telefone")
    var telefone: String? = ""

    @SerializedName("celular")
    @ColumnInfo(name = "celular")
    var celular: String? = ""

    @SerializedName("conjuge")
    @ColumnInfo(name = "conjuge")
    var conjuge: String? = ""

    @SerializedName("tipo")
    @ColumnInfo(name = "tipo")
    var tipo: String? = ""

    @SerializedName("time")
    @ColumnInfo(name = "time")
    var time: String? = ""

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String? = ""

    @SerializedName("dataDeNascimento")
    @ColumnInfo(name = "dataDeNascimento")
    var dataDeNascimento: String? = ""

    @SerializedName("dataDeNascimentoDoConjuge")
    @ColumnInfo(name = "dataDeNascimentoDoConjuge")
    var dataDeNascimentoDoConjuge: String? = ""

    @SerializedName("hobbie")
    @ColumnInfo(name = "hobbie")
    var hobbie: String? = ""

}