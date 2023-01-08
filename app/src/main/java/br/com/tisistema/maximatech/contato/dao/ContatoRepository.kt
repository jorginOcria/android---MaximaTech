package br.com.tisistema.maximatech.contato.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import br.com.tisistema.maximatech.cliente.model.Cliente
import br.com.tisistema.maximatech.contato.model.Contato

@Dao
interface ContatoRepository {

    @Insert
    fun insert(contato: Contato): Long

    @Update
    fun update(contato: Contato)

    @Query("select * from contato")
    fun getAll(): List<Cliente>

    @Query("delete from contato")
    fun deleteAll()

    @Query("select * from contato where id_cliente = :idCliente")
    fun getById(idCliente: Int): List<Contato>?

}