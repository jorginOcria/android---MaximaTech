package br.com.tisistema.maximatech.dadosdocliente.dao

import android.content.Context
import br.com.tisistema.maximatech.core.db.MaximaTechDataBase
import br.com.tisistema.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.tisistema.maximatech.core.dao.AbstractRepository

class DadosDoClienteRepositoryService(context: Context) :
    AbstractRepository(context) {

    private val clienteRepository = MaximaTechDataBase.getDatabase(context).getClienteRepository()

    fun merge(dadosDoCliente: DadosDoCliente): DadosDoCliente {
        return if (dadosDoCliente.idCliente == 0L) {
            insert(dadosDoCliente)
        } else {
            update(dadosDoCliente)
        }
    }

    fun insert(dadosDoCliente: DadosDoCliente): DadosDoCliente {
        val rowId = clienteRepository.insert(dadosDoCliente)
        dadosDoCliente.idCliente = rowId
        return dadosDoCliente
    }

    fun update(dadosDoCliente: DadosDoCliente): DadosDoCliente {
        clienteRepository.update(dadosDoCliente)
        return dadosDoCliente
    }

    fun getPrimeiroCliente(): DadosDoCliente? {
        return clienteRepository.getPrimeiroCliente()
    }

    fun deleteAll(){
        clienteRepository.deleteAll()
    }

}