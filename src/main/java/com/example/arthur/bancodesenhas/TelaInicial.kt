package com.example.arthur.bancodesenhas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TelaInicial : AppCompatActivity() {

    private lateinit var acessarSenhas :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        this.acessarSenhas = findViewById(R.id.botaoAcessaSenhas)

        this.acessarSenhas.setOnClickListener {
            val intent = Intent(this, TelaSenhas::class.java)
            startActivity(intent)
            finish()
        }

    }

}