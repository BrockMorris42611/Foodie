package temple.edu.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReviewFrag : Fragment() {

    lateinit var foodieViewModel: FoodieViewModel
    lateinit var restaurantNameTV: TextView
    lateinit var restaurantDetailsTV: TextView
    lateinit var restaurantTagsTV: TextView
    lateinit var restaurantAddressTV: TextView
    lateinit var reviewsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodieViewModel = ViewModelProvider(requireActivity()).get(FoodieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_review, container, false)

        restaurantNameTV = layout.findViewById(R.id.currRestaurantName)
        restaurantDetailsTV = layout.findViewById(R.id.currRestaurantDetails)
        restaurantTagsTV = layout.findViewById(R.id.currRestaurantTags)
        restaurantAddressTV = layout.findViewById(R.id.currRestaurantAdress)
        reviewsRecyclerView = layout.findViewById(R.id.reviewsRV)
        reviewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        reviewsRecyclerView.adapter = ReviewRVAdapter(foodieViewModel.getReviewList(), foodieViewModel)
        foodieViewModel.getSelectedRestaurantToObserve().observe(requireActivity()){
            restaurantNameTV.text = "Restaurant:\n${it.restaurant_name}"
            restaurantDetailsTV.text = it.details
            restaurantTagsTV.text = "Tags: ${it.tags}"
            restaurantAddressTV.text = it.address
        }

        return layout
    }
}