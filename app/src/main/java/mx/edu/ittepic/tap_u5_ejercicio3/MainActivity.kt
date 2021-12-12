package mx.edu.ittepic.tap_u5_ejercicio3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val arregloDinamico = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insertar.setOnClickListener {
            arregloDinamico.add(frase.text.toString())
            frase.setText("")
            cargarDatosEnListView()
        }

    }

    fun cargarDatosEnListView(){
        listaFrases.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arregloDinamico)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ventana1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.enviarsms -> {
                val txt = frase.text.toString()
                val ventana2 = Intent(this, MainActivity2::class.java).putExtra("DATO", txt)
                startActivity(ventana2)
            }
            R.id.borraritem -> {
                val indiceBorrar = EditText(this)
                indiceBorrar.inputType = InputType.TYPE_CLASS_NUMBER
                indiceBorrar.setText("0")
                AlertDialog.Builder(this).setTitle("ATENCION").setMessage("ESCRIBA EL INDICE A BORRAR")
                    .setView(indiceBorrar).setPositiveButton("OK", {d,i ->
                        val indice = indiceBorrar.text.toString().toInt()
                        arregloDinamico.removeAt(indice)
                        Toast.makeText(this, "SE BORRO CORRECTAMENTE ${indice}", Toast.LENGTH_LONG)
                        cargarDatosEnListView()
                    }).setNegativeButton("CANCELAR", { d, i -> }).show()
            }
            R.id.acerca -> {
                AlertDialog.Builder(this).setTitle("ATENCION").setMessage("DERECHOS RESERVADOS")
                    .setPositiveButton("OK", {d,i ->}).show()
            }
            R.id.salir -> {
                finish()
            }
        }
        return true
    }
}