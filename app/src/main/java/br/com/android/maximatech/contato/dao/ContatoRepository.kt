package br.com.android.maximatech.contato.dao

import androidx.room.*
import br.com.android.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.android.maximatech.contato.model.Contato

@Dao
interface ContatoRepository {

    @Insert
    fun insert(contato: Contato): Long

    @Update
    fun update(contato: Contato)

    @Query("select * from contato")
    fun getAll(): List<DadosDoCliente>

    @Query("delete from contato")
    fun deleteAll()

    @Query("select * from contato where id_cliente = :idCliente")
    fun getById(idCliente: Int): List<Contato>?

}