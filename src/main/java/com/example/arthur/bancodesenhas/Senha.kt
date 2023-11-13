package com.example.arthur.bancodesenhas;

import java.text.SimpleDateFormat
class Senha {
    var id: Int
    var descricao: String
    var senha_gerada: String
    var data_create: Long
    var data_update: Long

    constructor(descricao: String, senha_gerada: String){
        this.id = -1
        this.descricao = descricao
        this.senha_gerada = senha_gerada
        this.data_create = -1
        this.data_update = -1
    }
    constructor(id: Int, descricao: String, senha_gerada: String, data_create: Long, data_update: Long) {
        this.id = id
        this.descricao = descricao
        this.senha_gerada = senha_gerada
        this.data_create = data_create
        this.data_update = data_update
    }

     override fun toString(): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            return "${id} ${descricao} ${senha_gerada} ${sdf.format(data_create)} ${sdf.format(data_update)}"
        }
    }

