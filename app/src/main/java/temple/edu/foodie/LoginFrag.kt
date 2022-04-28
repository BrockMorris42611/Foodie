package temple.edu.foodie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject


class LoginFrag : Fragment() {

    lateinit var layout : View
    lateinit var login : Button
    lateinit var createAcc : TextView
    lateinit var usernameBox : TextView
    lateinit var passwordBox : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = inflater.inflate(R.layout.fragment_login, container, false)
        usernameBox = layout.findViewById(R.id.login_usernameTV)
        passwordBox = layout.findViewById(R.id.login_passwordTV)
        login = layout.findViewById(R.id.loginButton)
        createAcc = layout.findViewById(R.id.createAccTV)
        login.setOnClickListener {
            if (usernameBox.text.toString().isNotEmpty() ||
                passwordBox.text.toString().isNotEmpty()
            ) {
                val url = "http://cis-linux2.temple.edu:8080/SP22_4515_tuj42611/loginUser.jsp?" +
                            "username=" + usernameBox.text.toString() +
                            "&password=" + passwordBox.text.toString()
                val requestQueue = Volley.newRequestQueue(requireContext())
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    object : Response.Listener<JSONObject> {
                        override fun onResponse(response: JSONObject?) {
                            try {
                                Log.d("Success Login Frag>>>>", response.toString())
                                if (!response.toString().contains("User Not Found")) {
                                    User.Api.setLocalUser(requireContext(),
                                        JsonParser().parse(response.toString()).asJsonObject
                                            .get("userId").asInt
                                    )
                                    Navigation.findNavController(layout)
                                        .navigate(R.id.action_loginFrag_to_mapsFragment)
                                }
                                else Toast.makeText(requireContext(),
                                    "User Does Not Exist. Try Again.",Toast.LENGTH_LONG).show()
                            } catch (e: JSONException) {
                                println(">>>>>>>>>>>>e JSON EXCEPTION LOGIN FRAG")
                            }
                        }
                    },
                    Response.ErrorListener {
                        println(">>>>>>ERROR LISTENER LOGIN FRAG: $it")
                    })
                requestQueue.add(jsonObjectRequest)
            }
        }
        createAcc.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.action_loginFrag_to_registerFrag)
        }
        return layout
    }
}