package mx.edu.ittepic.tap_u5_ejercicio3

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Preguntar si existe el permiso ya fue aceptado
        val permiso = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)

        //Pedir el permiso
        if (permiso == PackageManager.PERMISSION_DENIED){
            //el 0 significa que no va a verificar que se otorgo el permiso
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 0)
        }

        val intent = intent
        val txt = intent.getStringExtra("DATO")
        datos.setText(txt)

        button.setOnClickListener {
            val numeroCelular = telefono.text.toString()
            SmsManager.getDefault().sendTextMessage(numeroCelular, null, txt, null, null)

        }

        regresar.setOnClickListener {
            finish()
        }


    }
}