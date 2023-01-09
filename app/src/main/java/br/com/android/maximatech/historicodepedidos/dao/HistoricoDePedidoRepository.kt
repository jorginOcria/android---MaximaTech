package br.com.android.maximatech.historicodepedidos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.android.maximatech.historicodepedidos.model.HistoricoDePedido

@Dao
interface HistoricoDePedidoRepository {

    @Insert
    fun insert(historicoDePedido: HistoricoDePedido): Long

    @Update
    fun update(historicoDePedido: HistoricoDePedido)

    @Query("select * from historico_de_pedido")
    fun getAll(): List<HistoricoDePedido>?

    @Query("delete from historico_de_pedido")
    fun deleteAll()

}