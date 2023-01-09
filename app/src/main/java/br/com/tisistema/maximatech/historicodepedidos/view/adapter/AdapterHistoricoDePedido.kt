package br.com.tisistema.maximatech.historicodepedidos.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.historicodepedidos.model.HistoricoDePedido

class AdapterHistoricoDePedido(
    context: Context,
    private val historicoDePedidos: List<HistoricoDePedido>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var rowView: View

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        carregarRowView(parent)
        carregarValoresDeTextos(position)
        return rowView
    }

    @SuppressLint("SetTextI18n")
    private fun carregarValoresDeTextos(posicao: Int) {
        val numeroDoPedido =
            rowView.findViewById(R.id.row_historico_de_pedidos__text_numero_do_pedido) as TextView
        val cliente =
            rowView.findViewById(R.id.row_historico_de_pedidos__label_cliente) as TextView
        val status =
            rowView.findViewById(R.id.row_historico_de_pedidos__text_status) as TextView
        val valor =
            rowView.findViewById(R.id.row_historico_de_pedidos__text_valor) as TextView
        val horario =
            rowView.findViewById(R.id.row_historico_de_pedidos__text_horario) as TextView


        val historicoDePedido = getItem(posicao) as HistoricoDePedido
        numeroDoPedido.text = "${historicoDePedido.numeroDoPedidoRca} / ${historicoDePedido.numeroDoPedidoErp}"
        cliente.text = "${historicoDePedido.codigoDoCliente} - ${historicoDePedido.nomeDoCliente}"
        status.text = historicoDePedido.status
        valor.text = Constants.SISTEMA.VALOR_MONETARIO
        horario.text = historicoDePedido.data

    }

    private fun carregarRowView(parent: ViewGroup?) {
        rowView = inflater.inflate(R.layout.row_historico_de_pedidos, parent, false)
    }

    override fun getItem(position: Int): HistoricoDePedido {
        return historicoDePedidos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return historicoDePedidos.size
    }
}