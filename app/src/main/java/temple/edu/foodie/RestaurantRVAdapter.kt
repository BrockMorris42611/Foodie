package temple.edu.foodie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class RestaurantRVAdapter (private var restaurantList: RestaurantList): RecyclerView.Adapter<RestaurantRVAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val restaurantNameTV : TextView = itemView.findViewById(R.id.restaurantTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.restaurantNameTV.text = restaurantList.restaurants[position].restaurant_name
    }

    override fun getItemCount() = restaurantList.restaurants.size
}