package temple.edu.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationViewMenu)

        //val navController = findNavController(R.id.main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFrag, R.id.registerFrag, R.id.searchFrag, R.id.listFrag)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, _, _ ->
            if (navController.currentDestination?.id == R.id.loginFrag || navController.currentDestination?.id == R.id.registerFrag) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }
        navView.findViewById<View>(R.id.action_search).setOnClickListener {
            navController.navigate(R.id.searchFrag)
        }
        navView.findViewById<View>(R.id.action_list).setOnClickListener {
            navController.navigate(R.id.listFrag)
        }
        navView.findViewById<View>(R.id.action_map).setOnClickListener {
            navController.navigate(R.id.mapsFrag)
        }

        val url =
            "http://cis-linux2.temple.edu:8080/SP22_4515_tuj42611/getRestaurantId.jsp?restaurantId=20"
        val requestQueue = Volley.newRequestQueue(this)
        val obj = JSONObject()

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            obj,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {
                    try {
                        Log.d(">>>>>>>>>>>>>>>>>>>>", response.toString())
                        Log.d("********************", obj.toString()/*response.toString()*/)
                    } catch (e: JSONException) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                    }
                }
            },
            Response.ErrorListener {
                println(">>>>>>>>>>>>>>>>>>>>DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD$it")
            })
        requestQueue.add(jsonObjectRequest)

        val newObj = JSONObject()
        newObj.put("x", 11)
        

        println(">>>>>>>>>>>>>>>>>>>>DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD$newObj")
    }
}

class test (x : Int)