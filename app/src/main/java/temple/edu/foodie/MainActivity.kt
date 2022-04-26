package temple.edu.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.lang.Character.getType

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

        val requestQueue = Volley.newRequestQueue(this)
        val myurl = "http://cis-linux2.temple.edu:8080/SP22_4515_tuj42611/listUsers.jsp"
        val listRequest = JsonObjectRequest(
            Request.Method.GET, myurl, null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {
                    try {
                        if (response != null) {
                            Log.d(">>>>>>>>>>>>>>>>>>>>", response.get("userList").toString())
                            val arr = JsonParser().parse(response.get("userList").toString()).asJsonArray
                            val arrType = object : TypeToken<ArrayList<User>>(){}.type
                            val yes = Gson().fromJson<ArrayList<User>>(arr,arrType)
                            println("}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}} " + yes[1].userId)
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
