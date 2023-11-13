package com.example.arthur.bancodesenhas

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random

class Senhas() : Parcelable{
    var tamanho = 0
    var nomeS = ""
    var numero : Boolean= false
    var especial : Boolean= false
    var maiusculo : Boolean= false
    lateinit var senha1 : String


    constructor(parcel: Parcel) : this() {
        tamanho = parcel.readInt()
        nomeS = parcel.readString() ?: ""
        numero = parcel.readByte() != 0.toByte()
        especial = parcel.readByte() != 0.toByte()
        maiusculo = parcel.readByte() != 0.toByte()
        senha1 = parcel.readString() ?: ""

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(tamanho)
        parcel.writeString(nomeS)
        parcel.writeByte(if (numero) 1 else 0)
        parcel.writeByte(if (especial) 1 else 0)
        parcel.writeByte(if (maiusculo) 1 else 0)
        parcel.writeString(senha1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Senhas> {
        override fun createFromParcel(parcel: Parcel): Senhas {
            return Senhas(parcel)
        }

        override fun newArray(size: Int): Array<Senhas?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Tamanho: $tamanho, Descrição: $nomeS, Número: $numero, Especial: $especial, Maiúsculo: $maiusculo, Senha: $senha1"
    }

    fun gerarSenhaAleatoria(tamanho: Int): String {

        val senha2 = StringBuilder()
        val caracteresEspeciais = "!@#$%^&*()_+{}:<>?|[]\\;'`,./"
        val letrasMinusculas = "abcdefghijklmnopqrstuvwxyz"
        val letrasMaiusculas = letrasMinusculas.uppercase()
        val numeros = "0123456789"

        val caracteresDisponiveis = StringBuilder()
        caracteresDisponiveis.append(letrasMinusculas)

        if (maiusculo)
            caracteresDisponiveis.append(letrasMaiusculas)
        if (numero)
            caracteresDisponiveis.append(numeros)
        if(especial)
            caracteresDisponiveis.append(caracteresEspeciais)

        repeat(tamanho) {
            val randomIndex = (caracteresDisponiveis.indices).random()
            val randomCaracteres = caracteresDisponiveis[randomIndex]
            senha2.append(randomCaracteres)
        }
        senha1 = senha2.toString()
        return senha1

    }
}
