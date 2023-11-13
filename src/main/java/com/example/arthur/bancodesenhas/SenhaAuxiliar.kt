package com.example.arthur.bancodesenhas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SenhaAuxiliar(context: Context, resource: Int, objects: List<Senhas>) :
    ArrayAdapter<Senhas>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val convertView = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_lista_de_senha, parent, false)
        val descricao: TextView = convertView.findViewById(R.id.DescricaoSenha)
        val tamanho: TextView = convertView.findViewById(R.id.TamanhoDaSenha)
        val senha = getItem(position)

        descricao.text = senha?.nomeS
        tamanho.text = "(${senha?.tamanho})"

        return convertView
    }
}
