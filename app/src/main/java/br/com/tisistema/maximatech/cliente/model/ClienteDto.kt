package br.com.tisistema.maximatech.cliente.model

import br.com.tisistema.maximatech.contato.model.ContatoDto
import com.google.gson.annotations.SerializedName

data class ClienteDto(

    @SerializedName("id")
    var id: Long? = 0L,

    @SerializedName("codigo")
    var codigo: String? = "",

    @SerializedName("razao_social")
    var razaoSocial: String? = "",

    @SerializedName("nomeFantasia")
    var nomeFantasia: String? = "",

    @SerializedName("cpf")
    var cpf: String? = null,

    @SerializedName("cnpj")
    var cnpj: String? = "",

    @SerializedName("ramo_atividade")
    var ramoAtividade: String? = "",

    @SerializedName("endereco")
    var endereco: String? = "",

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("contatos")
    var contatos: List<ContatoDto>? = arrayListOf()

)