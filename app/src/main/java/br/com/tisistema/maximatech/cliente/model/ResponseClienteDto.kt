package br.com.tisistema.maximatech.cliente.model

import com.google.gson.annotations.SerializedName

data class ResponseClienteDto(

    @SerializedName("cliente")
    var cliente: ClienteDto? = ClienteDto()

)