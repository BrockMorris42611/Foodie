package temple.edu.foodie

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.function.Consumer

class MapsFrag : Fragment() {

    /*private lateinit var map: GoogleMap
    private lateinit var foodieViewModel: FoodieViewModel
    var myMarker: Marker? = null
    var isInit = false
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        map = googleMap
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(this::foodieViewModel.isInitialized) {
            foodieViewModel = ViewModelProvider(requireActivity()).get(foodieViewModel::class.java)
            isInit = true
            Log.d("FROM ON CREATE MAP FRAG>> ",isInit.toString())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        if(isInit)
            foodieViewModel.getRestaurantsToObserve().observe(requireActivity()){
                it.restaurants.forEach {
                    Log.d("HELLO>> "," IN MAP")
                }
            }
    }*/
    private lateinit var map: GoogleMap
    lateinit var foodieViewModel: FoodieViewModel
    var myMarker: Marker? = null

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        foodieViewModel.getRestaurantsToObserve().observe(requireActivity()) {
            it.restaurants.forEach { restaurant ->
                Log.d("IN MAPS >>>>>>>>>>>>>>> ", restaurant.latLong.toString())
                val marker = MarkerOptions().position(restaurant.latLong)
                //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_icon))
                map.addMarker(marker)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodieViewModel = ViewModelProvider(requireActivity()).get(FoodieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }
}