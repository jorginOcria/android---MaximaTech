package br.com.tisistema.maximatech.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.tisistema.maximatech.dadosdocliente.dao.DadosDoClienteRepository
import br.com.tisistema.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.tisistema.maximatech.contato.dao.ContatoRepository
import br.com.tisistema.maximatech.contato.model.Contato
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.historicodepedidos.dao.HistoricoDePedidoRepository
import br.com.tisistema.maximatech.historicodepedidos.model.HistoricoDePedido

@Database(
    entities = [
        DadosDoCliente::class,
        Contato::class,
        HistoricoDePedido::class
    ],
    version = 1
)

@TypeConverters
abstract class MaximaTechDataBase : RoomDatabase() {

    abstract fun getClienteRepository(): DadosDoClienteRepository
    abstract fun getContatoRepository(): ContatoRepository
    abstract fun getHistoricoDePedidoRepository(): HistoricoDePedidoRepository

    companion object {
        private lateinit var INSTANCE: MaximaTechDataBase
        fun getDatabase(context: Context): MaximaTechDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(MaximaTechDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MaximaTechDataBase::class.java,
                        Constants.DB.DB_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}