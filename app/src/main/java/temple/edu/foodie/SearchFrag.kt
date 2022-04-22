package temple.edu.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SearchFrag : Fragment() {


    lateinit var searchButton : Button
    lateinit var searchTypeSpinner: Spinner
    lateinit var recyclerView : RecyclerView
    lateinit var searchTextBox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false)

        searchButton = layout.findViewById(R.id.searchButton)
        searchTypeSpinner = layout.findViewById(R.id.searchTypeSpinner)

        //searchTypeSpinner.adapter = ArrayAdapter<String>(requireContext(), )

        searchTextBox = layout.findViewById(R.id.searchBox)
        recyclerView = layout.findViewById(R.id.recyclerView)


        return layout
    }
}