package com.example.arthur.bancodesenhas
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaSenhas : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var btAddSenha: FloatingActionButton
    private var senhaLista : MutableList<Senhas> = ArrayList()
    private var indiceSenha = 0
    private lateinit var dao:SenhaDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_senhas)


        this.dao = SenhaDAO(this)
        this.listView =findViewById(R.id.listaSenhas)
        this.btAddSenha = findViewById(R.id.botaoAddSenha)

        val auxiliar = SenhaAuxiliar(this, R.layout.activity_lista_de_senha, senhaLista)
        listView.adapter = auxiliar

        this.btAddSenha =findViewById(R.id.botaoAddSenha)
        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK){
                val data = it.data

                val senhaNova: Senhas? = data?.getParcelableExtra("novaSenha")

                if (senhaNova != null) {
                    senhaLista.add(senhaNova)
                    auxiliar.notifyDataSetChanged()
                }

                val senhaEditada: Senhas? = data?.getParcelableExtra("senhaAlterada")

                if (senhaEditada != null) {
                    senhaLista[indiceSenha] = senhaEditada
                    auxiliar.notifyDataSetChanged()
                }

                val senhaApagada: Senhas? = data?.getParcelableExtra("senhaApagada")

                if (senhaApagada != null){
                    senhaLista.remove(senhaLista[indiceSenha])
                    auxiliar.notifyDataSetChanged()
                }
            }}

        btAddSenha.setOnClickListener {
            val intent = Intent(this@TelaSenhas, CadastroSenha::class.java)
            resultForm.launch(intent)
//            Log.i("APP_BANCO", this.dao.select().toString())
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("simple texto", senhaLista[position].senha1)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this@TelaSenhas, "Senha copiada", Toast.LENGTH_SHORT).show()
            true
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val senhaSelecionada = senhaLista[position]
            indiceSenha = position
            val intent = Intent(this@TelaSenhas, EditarSenhas::class.java)
            intent.putExtra("senha", senhaSelecionada)
            resultForm.launch(intent)
        }


    }
}















//        this.senhas = mutableListOf()
//        this.senhasList = findViewById(R.id.listaSenhas)
//        this.floatingActionButton = findViewById(R.id.botaoAddSenha)
//        this.floatingActionButton.setOnClickListener({ onClick(it) })
//
//
//        this.senhasList.adapter = SenhaAuxiliar(this, this.senhas)
//    }
//
//    fun add(){
//        val senhaGerada = intent.getStringExtra("senha")
//        (this.senhasList.adapter as SenhaAuxiliar).add(senhaGerada)
//    }
//    private fun onClick(view: View){
//        add()
//        val intent = Intent(this, CadastroSenha::class.java)
//        startActivity(intent)
//
//
//    }
//
//
//
//}