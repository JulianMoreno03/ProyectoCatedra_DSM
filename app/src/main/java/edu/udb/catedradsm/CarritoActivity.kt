package edu.udb.catedradsm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.udb.catedradsm.adapters.CarritoAdapter
import edu.udb.catedradsm.models.Carrito
import edu.udb.catedradsm.models.Producto

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerViewCarrito: RecyclerView
    private lateinit var carritoAdapter: CarritoAdapter
    private lateinit var btnComprar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Configurar el RecyclerView
        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito)
        recyclerViewCarrito.layoutManager = LinearLayoutManager(this)

        // Crear el adapter
        carritoAdapter = CarritoAdapter(Carrito.productos.toMutableList()) { producto ->
            eliminarProductoDelCarrito(producto)
        }

        recyclerViewCarrito.adapter = carritoAdapter


        // Inicializar y configurar el botón de compra
        btnComprar = findViewById(R.id.btnAdd)
        btnComprar.setOnClickListener {
            finalizarCompra()
        }
    }

    private fun finalizarCompra() {

        // Borrar todos los productos del carrito
        Carrito.productos.clear()
        carritoAdapter.notifyDataSetChanged()

        Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()

        // Regresar al MenuActivity
        val intent = Intent(this, MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


    private fun eliminarProductoDelCarrito(producto: Producto) {
        Carrito.productos.remove(producto)  // Eliminar el producto del carrito global
        carritoAdapter.removeItem(producto)  // Eliminar el producto del adapter (interfaz)



    }


}
