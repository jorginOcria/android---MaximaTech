package br.com.android.maximatech.contato.dao

import android.content.Context
import br.com.android.maximatech.core.db.MaximaTechDataBase
import br.com.android.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.android.maximatech.contato.model.Contato
import br.com.android.maximatech.core.dao.AbstractRepository

class ContatoRepositoryService(context: Context) :
    AbstractRepository(context) {

    private val contatoRepository = MaximaTechDataBase.getDatabase(context).getContatoRepository()

    fun merge(contato: Contato): Contato {
        return if (contato.idContato == 0L) {
            insert(contato)
        } else {
            update(contato)
        }
    }

    fun insert(contato: Contato): Contato {
        val rowId = contatoRepository.insert(contato)
        contato.idContato = rowId
        return contato
    }

    fun update(contato: Contato): Contato {
        contatoRepository.update(contato)
        return contato
    }

    fun getAll(): List<DadosDoCliente> {
        return contatoRepository.getAll()
    }

    fun getById(idCliente: Int): List<Contato>? {
        return contatoRepository.getById(idCliente)
    }

    fun deleteAll() {
        contatoRepository.deleteAll()
    }

}