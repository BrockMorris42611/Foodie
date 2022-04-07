package temple.edu.foodie

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botNav : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val searchBotNav : BottomNavigationItemView = findViewById(R.id.action_search)
        val mapBotNav : BottomNavigationItemView = findViewById(R.id.action_map)
        val listBotNav : BottomNavigationItemView = findViewById(R.id.action_list)

        //val navController = Navigation.findNavController(MainActivity, R.id.loginFrag)
        //findViewById(R.id.main)
        /*.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.parent?.id == R.id.loginFrag || destination.parent?.id == R.id.registerFrag) {
                botNav.visibility = View.GONE
            } else {
                botNav.visibility = View.VISIBLE
            }
        }*/
        searchBotNav.setOnClickListener {
            Navigation.findNavController(findViewById(R.id.main)).navigate(R.id.action_menuFrag_to_searchFrag)
        }
        mapBotNav.setOnClickListener {
            //add stuff here
        }
        listBotNav.setOnClickListener {
            Navigation.findNavController(findViewById(R.id.main)).navigate(R.id.action_menuFrag_to_listFrag)
        }
    }
}