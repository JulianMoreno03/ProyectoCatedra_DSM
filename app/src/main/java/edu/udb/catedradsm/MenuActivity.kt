package edu.udb.catedradsm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val categoriaRopa = findViewById<View>(R.id.categoriaRopa)
        val categoriaTecnologia = findViewById<View>(R.id.categoriaTecnologia)

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
    }
}
