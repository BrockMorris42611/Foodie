package temple.edu.foodie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.intellij.lang.annotations.JdkConstants

class FoodieViewModel : ViewModel(){

    private val restaurants by lazy{
        RestaurantList()
    }
    private val mutableRestaurantList by lazy {
        MutableLiveData<RestaurantList>()
    }

    fun setRestaurants(restaurantList: RestaurantList){
        restaurants.updateRestaurants(restaurantList)

        mutableRestaurantList.value = restaurants
    }

    fun getRestaurantsToObserve(): MutableLiveData<RestaurantList> {
        return mutableRestaurantList
    }

    fun getRestaurantList() : RestaurantList{
        return restaurants
    }
}