package br.com.tisistema.maximatech.historicodepedidos.dao

import android.content.Context
import br.com.tisistema.maximatech.core.db.MaximaTechDataBase
import br.com.tisistema.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.tisistema.maximatech.core.dao.AbstractRepository
import br.com.tisistema.maximatech.historicodepedidos.model.HistoricoDePedido

class HistoricoDePedidoRepositoryService(context: Context) :
    AbstractRepository(context) {

    private val historicoDePedidoRepository =
        MaximaTechDataBase.getDatabase(context).getHistoricoDePedidoRepository()

    fun merge(historicoDePedido: HistoricoDePedido): HistoricoDePedido {
        return if (historicoDePedido.idHistoricoDePedido == 0L) {
            insert(historicoDePedido)
        } else {
            update(historicoDePedido)
        }
    }

    fun insert(historicoDePedido: HistoricoDePedido): HistoricoDePedido {
        val rowId = historicoDePedidoRepository.insert(historicoDePedido)
        historicoDePedido.idHistoricoDePedido = rowId
        return historicoDePedido
    }

    fun update(historicoDePedido: HistoricoDePedido): HistoricoDePedido {
        historicoDePedidoRepository.update(historicoDePedido)
        return historicoDePedido
    }

    fun getAll(): List<HistoricoDePedido>? {
        return historicoDePedidoRepository.getAll()
    }

    fun deleteAll() {
        historicoDePedidoRepository.deleteAll()
    }

}