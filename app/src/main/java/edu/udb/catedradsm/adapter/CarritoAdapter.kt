package edu.udb.catedradsm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.udb.catedradsm.R
import edu.udb.catedradsm.models.Producto
import edu.udb.catedradsm.models.Carrito

class CarritoAdapter(private val productos: MutableList<Producto>, private val onDelete: (Producto) -> Unit) :
    RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProducto: ImageView = itemView.findViewById(R.id.imageViewProductoCarrito)
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombreProductoCarrito)
        val textViewPrecio: TextView = itemView.findViewById(R.id.textViewPrecioProductoCarrito)
        val textViewCantidad: TextView = itemView.findViewById(R.id.textViewCantidadProductoCarrito)
        val buttonEliminar: Button = itemView.findViewById(R.id.buttonEliminarProductoCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = productos[position]

        // Setear datos en las vistas
        holder.textViewNombre.text = producto.nombre
        holder.textViewPrecio.text = "$${producto.precio}"

        // Cargar la imagen del producto usando Picasso
        Picasso.get()
            .load(producto.imgLink)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageViewProducto)

        // Configurar el botón para eliminar un producto del carrito
        holder.buttonEliminar.setOnClickListener {
            onDelete(producto) // Llamar a la función onDelete cuando se hace click
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    fun removeItem(producto: Producto) {
        val position = productos.indexOf(producto)
        if (position != -1) {
            productos.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
