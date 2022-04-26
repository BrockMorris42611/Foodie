package temple.edu.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


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
        recyclerView.adapter = RestaurantRVAdapter(foodieViewModel.getRestaurantList())
        foodieViewModel.getRestaurantsToObserve().observe(requireActivity()){
            recyclerView.adapter = RestaurantRVAdapter(foodieViewModel.getRestaurantList())
        }

        searchButton = layout.findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            if(searchEdit.text.toString().isEmpty()){
                TODO("GIVE DEFAULT SEARCH FUNC TO IT")
            }else{

            }
        }

        return layout
    }
}