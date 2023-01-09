package br.com.tisistema.maximatech.contato.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.contato.model.Contato

class AdapterContato(
    context: Context,
    private val contatos: List<Contato>
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

    private fun carregarValoresDeTextos(posicao: Int) {
        val nome =
            rowView.findViewById(R.id.row_contatos__text_nome) as TextView
        val telefone =
            rowView.findViewById(R.id.row_contatos__text_telefone) as TextView
        val celular =
            rowView.findViewById(R.id.row_contatos__text_celular) as TextView
        val conjugue =
            rowView.findViewById(R.id.row_contatos__text_conjugue) as TextView
        val tipo =
            rowView.findViewById(R.id.row_contatos__text_tipo) as TextView
        val hobbie =
            rowView.findViewById(R.id.row_contatos__text_hobbie) as TextView
        val email =
            rowView.findViewById(R.id.row_contatos__text_email) as TextView
        val dataDeNascimento =
            rowView.findViewById(R.id.row_contatos__text_data_de_nascimento) as TextView
        val dataDeNascimentoDoConjugue =
            rowView.findViewById(R.id.row_contatos__text_data_de_nascimento_do_conjugue) as TextView
        val time =
            rowView.findViewById(R.id.row_contatos__text_time) as TextView

        val contato = getItem(posicao) as Contato
        nome.text = contato.nome
        telefone.text = contato.telefone
        celular.text = contato.celular
        conjugue.text = contato.conjuge
        tipo.text = contato.tipo
        hobbie.text = contato.hobbie
        email.text = contato.email
        dataDeNascimento.text = contato.dataDeNascimento
        dataDeNascimentoDoConjugue.text = contato.dataDeNascimentoDoConjuge
        time.text = contato.time

    }

    private fun carregarRowView(parent: ViewGroup?) {
        rowView = inflater.inflate(R.layout.row_contatos, parent, false)
    }

    override fun getItem(position: Int): Contato {
        return contatos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return contatos.size
    }
}