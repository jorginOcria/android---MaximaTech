package br.com.tisistema.maximatech.dadosdocliente.model

import com.google.gson.annotations.SerializedName

data class ResponseDadosDoClienteDto(

    @SerializedName("cliente")
    var dadosDoClliente: DadosDoCllienteDto? = DadosDoCllienteDto()

)