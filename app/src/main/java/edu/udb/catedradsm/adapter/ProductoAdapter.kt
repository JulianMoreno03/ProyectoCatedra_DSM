package edu.udb.catedradsm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.udb.catedradsm.R
import edu.udb.catedradsm.models.Producto

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onItemClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProducto)
        val nombre: TextView = view.findViewById(R.id.textViewNombre)
        val precio: TextView = view.findViewById(R.id.textViewPrecio)
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

        // Cargar la imagen
        Picasso.get()
            .load(producto.imgLink)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)

        // Manejar el clic en el elemento
        holder.itemView.setOnClickListener {
            onItemClick(producto) // Llama al callback con el producto correspondiente
        }
    }

    override fun getItemCount() = productos.size
}
