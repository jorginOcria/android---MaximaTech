package br.com.tisistema.maximatech.cliente.dao

import android.content.Context
import br.com.tisistema.maximatech.core.db.MaximaTechDataBase
import br.com.tisistema.maximatech.cliente.model.Cliente
import br.com.tisistema.maximatech.core.dao.AbstractRepository

class ClienteRepositoryService(context: Context) :
    AbstractRepository(context) {

    private val clienteRepository = MaximaTechDataBase.getDatabase(context).getClienteRepository()

    fun merge(cliente: Cliente): Cliente {
        return if (cliente.idCliente == 0L) {
            insert(cliente)
        } else {
            update(cliente)
        }
    }

    fun insert(cliente: Cliente): Cliente {
        val rowId = clienteRepository.insert(cliente)
        cliente.idCliente = rowId
        return cliente
    }

    fun update(cliente: Cliente): Cliente {
        clienteRepository.update(cliente)
        return cliente
    }

    fun getPrimeiroCliente(): Cliente? {
        return clienteRepository.getPrimeiroCliente()
    }

    fun deleteAll(){
        clienteRepository.deleteAll()
    }

}