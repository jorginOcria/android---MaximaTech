package br.com.android.maximatech.dadosdocliente.dao

import br.com.android.maximatech.dadosdocliente.model.ResponseDadosDoClienteDto
import retrofit2.Call
import retrofit2.http.GET

interface BuscarDadosDoClienteRest {

    @GET("cliente")
    fun getCliente(): Call<ResponseDadosDoClienteDto>
}