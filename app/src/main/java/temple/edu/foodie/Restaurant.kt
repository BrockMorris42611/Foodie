package temple.edu.foodie

import com.google.android.gms.maps.model.LatLng

class Restaurant(var RestaurantId : Int, var latLong : LatLng, var address : String, var details : String, var restaurant_name : String)

class RestaurantList {
    var restaurants = ArrayList<Restaurant>()
    val size : Int
        get() = restaurants.size

    fun updateRestaurants (list: RestaurantList){
        restaurants = list.restaurants
    }

    fun getRestaurant(i: Int): Restaurant{
        return restaurants[i]
    }
}