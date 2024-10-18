package edu.udb.catedradsm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView

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
    private val productosFiltrados = mutableListOf<Producto>() // Lista para productos filtrados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categoria)

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar Firebase
        database = FirebaseDatabase.getInstance().reference
        // Recibir el dato del Intent
        categoriaSeleccionada = intent.getStringExtra("CATEGORIA") ?: ""

        // Cargar datos de Firebase
        cargarDatos(categoriaSeleccionada)

        // Inicializar el adapter con el callback
        adapter = ProductoAdapter(productosFiltrados) { producto ->
            // Aquí manejas el clic en el producto
            val intent = Intent(this, DetalleProductoActivity::class.java).apply {
                putExtra("PRODUCTO", producto) // Enviar el producto a la actividad de detalle
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Configurar el SearchView
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarProductos(newText)
                return true
            }
        })

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
                    // Inicializar productosFiltrados con todos los productos
                    productosFiltrados.clear()
                    productosFiltrados.addAll(productosList)
                    adapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Manejar errores
                }
            })
    }

    private fun filtrarProductos(query: String?) {
        val textoConsulta = query?.toLowerCase() ?: ""
        productosFiltrados.clear() // Limpiar la lista filtrada

        if (textoConsulta.isEmpty()) {
            productosFiltrados.addAll(productosList) // Si no hay consulta, mostrar todos los productos
        } else {
            for (producto in productosList) {
                if (producto.nombre?.toLowerCase()?.contains(textoConsulta) == true) {
                    productosFiltrados.add(producto) // Agregar productos que coincidan con la consulta
                }
            }
        }
        adapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }
}