package edu.udb.catedradsm

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.udb.catedradsm.models.Producto
import com.squareup.picasso.Picasso
class DetalleProductoActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textViewNombre: TextView
    private lateinit var textViewPrecio: TextView
    private lateinit var textViewDescripcion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_producto)

        // Inicializar vistas
        imageView = findViewById(R.id.imageViewDetalle)
        textViewNombre = findViewById(R.id.textViewNombreDetalle)
        textViewPrecio = findViewById(R.id.textViewPrecioDetalle)
        textViewDescripcion = findViewById(R.id.textViewDescripcionDetalle)


        // Obtener el producto del Intent
        val producto = intent.getParcelableExtra<Producto>("PRODUCTO")

        // Mostrar los datos del producto
        producto?.let {
            textViewNombre.text = it.nombre
            textViewPrecio.text = "\$${it.precio}"
            textViewDescripcion.text = it.descripcion

            // Cargar la imagen
            Picasso.get()
                .load(it.imgLink)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}