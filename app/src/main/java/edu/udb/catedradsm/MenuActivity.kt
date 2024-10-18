package edu.udb.catedradsm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        auth = FirebaseAuth.getInstance() // Inicializar FirebaseAuth

        val categoriaRopa = findViewById<View>(R.id.categoriaRopa)
        val categoriaTecnologia = findViewById<View>(R.id.categoriaTecnologia)
        val imgOut = findViewById<ImageView>(R.id.imgOut)

        // Manejar clics
        categoriaRopa.setOnClickListener {
            val intent = Intent(this, CategoriaActivity::class.java)
            intent.putExtra("CATEGORIA", "ropa")  // Envia el nombre de la categoría
            startActivity(intent)
        }

        categoriaTecnologia.setOnClickListener {
            val intent = Intent(this, CategoriaActivity::class.java)
            intent.putExtra("CATEGORIA", "tecnologia")  // Envia el nombre de la categoría
            startActivity(intent)
        }
        // Manejar clic para cerrar sesión
        imgOut.setOnClickListener {
            cerrarSesion()
        }
    }


    private fun cerrarSesion() {
        auth.signOut() // Cierra la sesión del usuario

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Session cerrada correctamente", Toast.LENGTH_SHORT).show()

        finish() // Termina la actividad actual
    }
}
