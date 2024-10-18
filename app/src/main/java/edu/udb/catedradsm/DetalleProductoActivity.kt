package edu.udb.catedradsm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.udb.catedradsm.models.Producto
import com.squareup.picasso.Picasso
import edu.udb.catedradsm.models.Carrito

class DetalleProductoActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textViewNombre: TextView
    private lateinit var textViewNombre01:TextView
    private lateinit var textViewPrecio: TextView
    private lateinit var textViewDescripcion: TextView
    private lateinit var buttonAgregarCarrito : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_producto)

        // Inicializar vistas
        imageView = findViewById(R.id.imageViewDetalle)
        textViewNombre = findViewById(R.id.textViewNombreDetalle)
        textViewPrecio = findViewById(R.id.textViewPrecioDetalle)
        textViewDescripcion = findViewById(R.id.textViewDescripcionDetalle)
        textViewNombre01 = findViewById(R.id.textViewNombreDetalle01)
        buttonAgregarCarrito  = findViewById(R.id.btnAdd)

        // Obtener el producto del Intent
        val producto = intent.getParcelableExtra<Producto>("PRODUCTO")
        // Mostrar los datos del producto
        producto?.let { prod ->
            textViewNombre.text = prod.nombre
            textViewNombre01.text = prod.nombre
            textViewPrecio.text = "\$${prod.precio}"
            textViewDescripcion.text = prod.descripcion

            // Cargar la imagen
            Picasso.get()
                .load(prod.imgLink)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)


            // Configurar el botón para agregar el producto al carrito
            buttonAgregarCarrito.setOnClickListener {
                // Agregar el producto al carrito
                Carrito.productos.add(prod)

                // Redirigir a la vista del carrito
                val intent = Intent(this, CarritoActivity::class.java)
                startActivity(intent)
            }
        }
    }

}