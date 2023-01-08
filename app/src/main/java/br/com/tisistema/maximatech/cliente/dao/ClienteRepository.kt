package br.com.tisistema.maximatech.cliente.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import br.com.tisistema.maximatech.cliente.model.Cliente

@Dao
interface ClienteRepository {

    @Insert
    fun insert(cliente: Cliente): Long

    @Update
    fun update(cliente: Cliente)

    @Query("select * from cliente")
    fun getPrimeiroCliente(): Cliente?

    @Query("delete from cliente")
    fun deleteAll()

}