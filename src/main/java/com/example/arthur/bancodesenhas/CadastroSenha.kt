package com.example.arthur.bancodesenhas
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class CadastroSenha : AppCompatActivity() {
    private lateinit var nomeSenha: EditText
    private lateinit var tamanho: SeekBar
    private lateinit var caracterEspecial: CheckBox
    private lateinit var numero: CheckBox
    private lateinit var letraMaiuscula: CheckBox
    private lateinit var btnGerar: Button
    private lateinit var btnCancelar: Button
    private lateinit var tamanhoSenha: TextView
    private lateinit var dao: SenhaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_senhas)
        this.dao = SenhaDAO(this)
        this.dao.insert(Senha("descricao", "senha_gerada"))
        Log.i("APP_BANCO", this.dao.select().toString())


        this.tamanhoSenha = findViewById(R.id.tamanhoSenha)
        this.nomeSenha = findViewById(R.id.nomeSenha)
        this.btnGerar = findViewById(R.id.botaoGerar)
        this.btnCancelar = findViewById(R.id.botaoCancelar)
        this.tamanho = findViewById(R.id.seekBar)
        this.caracterEspecial = findViewById(R.id.caixaCaracterEspecial)
        this.numero = findViewById(R.id.caixaNumero)
        this.letraMaiuscula = findViewById(R.id.caixaLetraMaiuscula)

        tamanho.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tamanhoSenha.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val novaSenha = Senhas()

        this.btnGerar.setOnClickListener {
            val inputText = nomeSenha.text.toString()

            if (inputText.isNotEmpty()){
                novaSenha.nomeS = inputText
                novaSenha.maiusculo = letraMaiuscula.isChecked
                novaSenha.especial = caracterEspecial.isChecked
                novaSenha.numero = numero.isChecked
                novaSenha.tamanho = tamanho.progress

                novaSenha.gerarSenhaAleatoria(novaSenha.tamanho)

                val intent = Intent()
                intent.putExtra("novaSenha" ,novaSenha)
                setResult(Activity.RESULT_OK, intent)
                finish()
                this.dao.insert(Senha(this.nomeSenha.toString(), novaSenha.toString()))
                Log.i("APP_BANCO", this.dao.select().toString())

            }
        }

        this.btnCancelar.setOnClickListener {
            val intent = Intent(this, TelaSenhas::class.java)
            startActivity(intent)
            finish()
        }
    }






//    val senhaGerada = novaSenha(tamanho, caracterEspecial, numero, letraMaiuscula)
//
//            val intentMain = Intent(this,TelaSenhas::class.java);
//            startActivity(intentMain)
//            intentMain.apply {
//                putExtra("senha", senhaGerada)
//                putExtra("tamanho", tamanho)
//                putExtra("descricao", descricao)
//            }


//        fun gerarSenha(tamanho: Int, caracterEspecial: Boolean, numero: Boolean, letraMaiusculas: Boolean): String {
//            val caracteresEspeciais = "!@#$%^&*()_+{}:<>?|[]\\;'`,./"
//            val letrasMinusculas = "abcdefghijklmnopqrstuvwxyz"
//            val letrasMaiusculas = letrasMinusculas.uppercase()
//            val numeros = "0123456789"
//
//            val caracteresDisponiveis = StringBuilder()
//            caracteresDisponiveis.append(letrasMinusculas)
//
//            if (caracterEspecial) {
//                caracteresDisponiveis.append(caracteresEspeciais)
//            }
//            if (numero) {
//                caracteresDisponiveis.append(numeros)
//            }
//            if (letraMaiusculas) {
//                caracteresDisponiveis.append(letrasMaiusculas)
//            }
//
//            val senhaGerada = StringBuilder()
//            repeat(tamanho) {
//                val randomIndex = Random.nextInt(caracteresDisponiveis.length)
//                senhaGerada.append(caracteresDisponiveis[randomIndex])
//            }
//
//            return senhaGerada.toString()
//        }



}