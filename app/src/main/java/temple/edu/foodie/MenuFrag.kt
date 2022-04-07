package temple.edu.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MenuFrag : Fragment() {

    lateinit var layout : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_menu, container, false)

        val searchBotNav : BottomNavigationItemView = layout.findViewById(R.id.action_search)
        val mapBotNav : BottomNavigationItemView = layout.findViewById(R.id.action_map)
        val listBotNav : BottomNavigationItemView = layout.findViewById(R.id.action_list)

        searchBotNav.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.action_menuFrag_to_searchFrag)
        }
        mapBotNav.setOnClickListener {
            //add stuff here
        }
        listBotNav.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.action_menuFrag_to_listFrag)
        }


        return layout
    }
}