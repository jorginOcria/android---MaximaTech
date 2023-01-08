package br.com.tisistema.maximatech.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.tisistema.maximatech.cliente.dao.ClienteRepository
import br.com.tisistema.maximatech.cliente.model.Cliente
import br.com.tisistema.maximatech.contato.dao.ContatoRepository
import br.com.tisistema.maximatech.contato.model.Contato
import br.com.tisistema.maximatech.core.cross.Constants

@Database(
    entities = [
        Cliente::class,
        Contato::class
    ],
    version = 1
)

@TypeConverters
abstract class MaximaTechDataBase : RoomDatabase() {

    abstract fun getClienteRepository(): ClienteRepository
    abstract fun getContatoRepository(): ContatoRepository

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