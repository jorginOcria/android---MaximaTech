package br.com.tisistema.maximatech.cliente.dao

import br.com.tisistema.maximatech.cliente.model.ResponseClienteDto
import retrofit2.Call
import retrofit2.http.GET

interface BuscarClienteRest {

    @GET("cliente")
    fun getCliente(): Call<ResponseClienteDto>
}