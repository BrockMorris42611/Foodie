package temple.edu.foodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantRVAdapter (private var restaurantList: RestaurantList, foodieViewModel: FoodieViewModel): RecyclerView.Adapter<RestaurantRVAdapter.ViewHolder>() {

    val fVM = foodieViewModel

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val restaurantNameTV : TextView = itemView.findViewById(R.id.restaurantTV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.restaurantNameTV.text = restaurantList.restaurants[position].restaurant_name
        holder.itemView.setOnClickListener {
            fVM.setSelectedRestaurant(restaurantList.restaurants[position])
        }
    }

    override fun getItemCount() = restaurantList.restaurants.size
}
