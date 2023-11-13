package com.example.arthur.bancodesenhas


import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.util.Calendar

class SenhaDAO {
    private val banco: BancoHelper

    constructor(context: Context){
        this.banco = BancoHelper(context)
    }

    fun insert(senha: Senha){
        val dataHora = Calendar.getInstance().timeInMillis
        val cv = ContentValues()
        cv.put("descricao", senha.descricao)
        cv.put("senha_gerada", senha.senha_gerada)
        cv.put("data_create", dataHora)
        cv.put("data_update", dataHora)
        this.banco.writableDatabase.insert("senhas", null, cv)
        Log.d("SenhaDAO", "Inserção no banco de dados realizada:")
        Log.d("SenhaDAO", "Descrição: ${senha.descricao}")
        Log.d("SenhaDAO", "Senha Gerada: ${senha.senha_gerada}")
        Log.d("SenhaDAO", "Data de Criação: ${dataHora}")
    }

    fun select(): List<Senha> {
        val lista = ArrayList<Senha>()
        val colunas = arrayOf("id", "descricao", "senha_gerada", "data_create", "data_update")

        banco.readableDatabase.query("senhas", colunas, null, null, null, null, null).use { c ->
            while (c.moveToNext()) {
                val id = c.getInt(0)
                val descricao = c.getString(1)
                val senha_gerada = c.getString(2)
                val data_create = c.getLong(3)
                val data_update = c.getLong(4)
                val senha = Senha(id, descricao, senha_gerada, data_create, data_update)
                lista.add(senha)
            }
        }

        return lista
    }


    fun find(id: Int): Senha?{
        val colunas = arrayOf("id", "descricao", "senha_gerada", "data_create", "data_update")
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        val c = this.banco.readableDatabase.query("senhas", colunas, where, pWhere, null,null, null)

        c.moveToFirst()
        if (c.count == 1){
            val id = c.getInt(0)
            val descricao = c.getString(1)
            val senha_gerada = c.getString(2)
            val data_create = c.getLong(3)
            val data_update = c.getLong(4)
            return Senha(id, descricao, senha_gerada, data_create, data_update)
        }
        return null
    }

    fun delete(id: Int){
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        this.banco.writableDatabase.delete("senhas", where, pWhere)
    }

    fun update(senha: Senha){
        val where = "id = ?"
        val pWhere = arrayOf(senha.id.toString())
        val cv = ContentValues()
        cv.put("descricao", senha.descricao)
        cv.put("senha_gerada", senha.senha_gerada)
        cv.put("data_update", Calendar.getInstance().timeInMillis)
        this.banco.writableDatabase.update("senhas", cv, where, pWhere)
    }
}