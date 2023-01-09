package br.com.tisistema.maximatech.dadosdocliente.dao

import br.com.tisistema.maximatech.dadosdocliente.model.ResponseDadosDoClienteDto
import retrofit2.Call
import retrofit2.http.GET

interface BuscarDadosDoClienteRest {

    @GET("cliente")
    fun getCliente(): Call<ResponseDadosDoClienteDto>
}