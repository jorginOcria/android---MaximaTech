package br.com.tisistema.maximatech.contato.model

import com.google.gson.annotations.SerializedName

data class ContatoDto(

    @SerializedName("nome")
    var nome: String? = null,

    @SerializedName("telefone")
    var telefone: String? = null,

    @SerializedName("celular")
    var celular: String? = null,

    @SerializedName("conjuge")
    var conjuge: String? = null,

    @SerializedName("tipo")
    var tipo: String? = null,

    @SerializedName("time")
    var time: String? = null,

    @SerializedName("e_mail")
    var email: String? = null,

    @SerializedName("data_nascimento")
    var dataDeNascimento: String? = null,

    @SerializedName("dataNascimentoConjuge")
    var dataDeNascimentoDoConjuge: String? = null,

    @SerializedName("hobbie")
    var hobbie: String? = null

)