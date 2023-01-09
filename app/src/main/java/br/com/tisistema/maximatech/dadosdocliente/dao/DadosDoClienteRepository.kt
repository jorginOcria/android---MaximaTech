package br.com.tisistema.maximatech.dadosdocliente.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.tisistema.maximatech.dadosdocliente.model.DadosDoCliente

@Dao
interface DadosDoClienteRepository {

    @Insert
    fun insert(dadosDoCliente: DadosDoCliente): Long

    @Update
    fun update(dadosDoCliente: DadosDoCliente)

    @Query("select * from cliente")
    fun getPrimeiroCliente(): DadosDoCliente?

    @Query("delete from cliente")
    fun deleteAll()

}