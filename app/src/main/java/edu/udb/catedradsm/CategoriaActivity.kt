package edu.udb.catedradsm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.udb.catedradsm.adapter.ProductoAdapter
import edu.udb.catedradsm.models.Producto

class CategoriaActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var categoriaSeleccionada: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private val productosList = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categoria)

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView) // Asegúrate de tener un RecyclerView en tu layout
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductoAdapter(productosList)
        recyclerView.adapter = adapter

        // Inicializar Firebase
        database = FirebaseDatabase.getInstance().reference

        // Recibir el dato del Intent
        categoriaSeleccionada = intent.getStringExtra("CATEGORIA") ?: ""

        // Cargar datos de Firebase
        cargarDatos(categoriaSeleccionada)



    }
    private fun cargarDatos(categoria: String) {
        database.child("categorias").child(categoria)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    productosList.clear() // Limpiar la lista antes de cargar nuevos datos
                    for (snapshot in dataSnapshot.children) {
                        val producto = snapshot.getValue(Producto::class.java)
                        if (producto != null) {
                            productosList.add(producto)
                        }
                    }
                    adapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Manejar errores
                }
            })
    }
}