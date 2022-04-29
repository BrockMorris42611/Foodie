package temple.edu.foodie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject


class SearchFrag : Fragment() {


    lateinit var searchButton : Button
    lateinit var searchTypeSpinner: Spinner
    lateinit var recyclerView : RecyclerView
    lateinit var searchEdit: EditText
    lateinit var foodieViewModel: FoodieViewModel
    var searchType = "Tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodieViewModel = ViewModelProvider(requireActivity()).get(FoodieViewModel::class.java)
    }

    //SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false)

        searchTypeSpinner = layout.findViewById(R.id.searchTypeSpinner)
        searchTypeSpinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, arrayOf("Tag", "Name", "Address"))
        searchTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                searchType = p0!!.getItemAtPosition(p2).toString()
            }override fun onNothingSelected(p0:AdapterView<*>?){}}
        searchEdit = layout.findViewById(R.id.searchBox)
        recyclerView = layout.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RestaurantRVAdapter(foodieViewModel.getRestaurantList(), foodieViewModel)
        foodieViewModel.getRestaurantsToObserve().observe(requireActivity()){
            recyclerView.adapter = RestaurantRVAdapter(foodieViewModel.getRestaurantList(), foodieViewModel)
        }
        foodieViewModel.getSelectedRestaurantToObserve().observe(requireActivity()){
            Log.d("IN THE OBSERVER RESTAURANT>>>", it.restaurant_name)
            val restaurantId = foodieViewModel.getSelectedRestaurantToObserve().value?.RestaurantId
            val requestQueue = Volley.newRequestQueue(requireContext())
            val url = "http://cis-linux2.temple.edu:8080/SP22_4515_tuj42611/getReviews.jsp?restaurantId=$restaurantId"
            val listRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                object : Response.Listener<JSONObject> {
                    override fun onResponse(response: JSONObject?) {
                        try {
                            if (response != null) {
                                Log.d(">>>>>>>>>>>>>>>>>>>>", response.get("reviewList").toString())
                                Log.d(">>>>>>>>>>>>>>>>>>>>", url)
                                val arr = JsonParser().parse(response.get("reviewList").toString()).asJsonArray
                                val newReviewList = ReviewList()
                                arr.forEach {
                                    val temp = it.asJsonObject
                                    val newReview = Review(temp.get("rating").asString, temp.get("textBody").asString, temp.get("datePosted").asString)
                                    //Log.d("TEST IS SEARCH$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ", (newRestaurant.latLong.latitude + newRestaurant.latLong.longitude).toString())
                                    newReviewList.reviews.add(newReview)
                                }
                                foodieViewModel.setReviews(newReviewList)
                                //Log.d("FOOOOOOOOOOOOOOOOOOOOODIE >> ", foodieViewModel.getReviewList().getReview(0).datePosted)
                            }
                        } catch (e: JSONException) {
                            println(">>>>>>>>>>>>>>>>>>>>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                        }
                    }
                },
                Response.ErrorListener {
                    println(">>>>>>>>>>>>>>>>>>>>DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD$it")
                })
            requestQueue.add(listRequest)
        }

        searchButton = layout.findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            if(searchEdit.text.toString().isEmpty()){
                //default?
            }else{
                val requestQueue = Volley.newRequestQueue(requireContext())
                val url = "http://cis-linux2.temple.edu:8080/SP22_4515_tuj42611/getRestaurants.jsp?search=${searchEdit.text}&type=$searchType"
                val listRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    object : Response.Listener<JSONObject> {
                        override fun onResponse(response: JSONObject?) {
                            try {
                                if (response != null) {
                                    Log.d(">>>>>>>>>>>>>>>>>>>>", response.get("restaurantList").toString())
                                    Log.d(">>>>>>>>>>>>>>>>>>>>", url)
                                    val arr = JsonParser().parse(response.get("restaurantList").toString()).asJsonArray
                                    val newRestaurantList = RestaurantList()
                                    arr.forEach {
                                        val temp = it.asJsonObject
                                        val tempLatLng = temp.get("latLong").asString.split(",")
                                        val newRestaurant = Restaurant(
                                            temp.get("restaurantId").asInt,
                                            LatLng(tempLatLng[0].toDouble(),tempLatLng[1].toDouble()),
                                            temp.get("address").asString,
                                            temp.get("details").asString,
                                            temp.get("restaurantName").asString,
                                            temp.get("tags").asString
                                        )
                                        Log.d("TEST IS SEARCH$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ", (newRestaurant.latLong.latitude + newRestaurant.latLong.longitude).toString())
                                        newRestaurantList.restaurants.add(newRestaurant)
                                    }
                                    foodieViewModel.setRestaurants(newRestaurantList)
                                    Log.d("FOOOOOOOOOOOOOOOOOOOOODIE >> ", foodieViewModel.getRestaurantList().getRestaurant(0).restaurant_name)
                                }
                            } catch (e: JSONException) {
                                println(">>>>>>>>>>>>>>>>>>>>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                            }
                        }
                    },
                    Response.ErrorListener {
                        println(">>>>>>>>>>>>>>>>>>>>DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD$it")
                    })
                requestQueue.add(listRequest)
            }
        }

        return layout
    }
}