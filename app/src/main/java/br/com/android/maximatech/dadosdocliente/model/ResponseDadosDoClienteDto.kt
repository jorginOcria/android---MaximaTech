package br.com.android.maximatech.dadosdocliente.model

import br.com.android.maximatech.dadosdocliente.model.DadosDoCllienteDto
import com.google.gson.annotations.SerializedName

data class ResponseDadosDoClienteDto(

    @SerializedName("cliente")
    var dadosDoClliente: DadosDoCllienteDto? = DadosDoCllienteDto()

)