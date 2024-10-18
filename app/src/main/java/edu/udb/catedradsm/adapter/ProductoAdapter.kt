package edu.udb.catedradsm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import edu.udb.catedradsm.R
import edu.udb.catedradsm.models.Producto

class ProductoAdapter(private val productos: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProducto)
        val nombre: TextView = view.findViewById(R.id.textViewNombre)
        val precio: TextView = view.findViewById(R.id.textViewPrecio)
        val descripcion: TextView = view.findViewById(R.id.textViewDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = "Precio: \$${producto.precio}"
        holder.descripcion.text = producto.descripcion


        // Usar directamente la URL pública de la imagen
        Picasso.get()
            .load(producto.imgLink)  // Aquí el campo imgLink ya tiene la URL completa como la que mencionaste
            .placeholder(R.drawable.ic_launcher_foreground)  // Imagen temporal mientras carga
            .error(R.drawable.ic_launcher_background)  // Imagen en caso de error
            .into(holder.imageView)
    }

    override fun getItemCount() = productos.size
}
