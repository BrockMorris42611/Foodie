package temple.edu.foodie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import org.intellij.lang.annotations.JdkConstants

class FoodieViewModel : ViewModel(){

    private val restaurants by lazy{
        RestaurantList()
    }
    private val mutableRestaurantList by lazy {
        MutableLiveData<RestaurantList>()
    }
    private val reviews by lazy{
        ReviewList()
    }
    private val mutableReviewList by lazy{
        MutableLiveData<ReviewList>()
    }
    private val mutableSelectedRestaurant by lazy {
        MutableLiveData<Restaurant>()
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
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
    ///////////////////////////////////////////////////////////////////////////////////////////////
    fun setReviews(reviewList: ReviewList){
        reviews.updateReviews(reviewList)

        mutableReviewList.value = reviews
    }
    fun getReviewsToObserve(): MutableLiveData<ReviewList>{
        return mutableReviewList
    }
    fun getReviewList() : ReviewList{
        return reviews
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    fun setSelectedRestaurant(newRestaurant: Restaurant){
        mutableSelectedRestaurant.value = newRestaurant
    }
    fun getSelectedRestaurantToObserve(): MutableLiveData<Restaurant>{
        return mutableSelectedRestaurant
    }
    fun getSelectedRestaurant() {

    }
}