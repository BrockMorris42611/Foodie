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
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegisterFrag : Fragment() {

    lateinit var email : TextView
    lateinit var username : TextView
    lateinit var password : TextView
    lateinit var password2 : TextView
    lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_register, container, false)

        email = layout.findViewById(R.id.emailTV)
        username = layout.findViewById(R.id.login_usernameTV)
        password = layout.findViewById(R.id.login_passwordTV)
        password2 = layout.findViewById(R.id.password2TV)
        registerButton = layout.findViewById(R.id.registerButton)

        email.error

        registerButton.setOnClickListener {
            if      (email.text.isNullOrEmpty() || !email.text.contains("@"))
                email.error = "Please enter a valid email"
            else if (username.text.isNullOrEmpty())
                username.error = "Please enter a username"
            else if (password.text.isNullOrEmpty())
                password.error = "Please enter a password"
            else if (password2.text.isNullOrEmpty())
                password2.error = "Please re-type your password"
            else if (!password.text.toString().equals(password2.text.toString(), false))
                Toast.makeText(this.requireContext(), "Please make sure the two passwords match", Toast.LENGTH_LONG).show()
            else{
                val args = "email="    + email.text.toString()    + "&" + //I KNOW THIS IS UGLY BUT
                           "username=" + username.text.toString() + "&" + //I COULDNT USE JSON FOR SOME REASON! IT WASNT WORKING
                           "password=" + password.text.toString()         //WITH GSON???????
                println(args)
                findNavController().navigate(R.id.mapsFrag)
                val requestQueue = Volley.newRequestQueue(this.requireContext())
                val obj = JSONObject()
                val url = "http://cis-linux2.temple.edu:8080/SP22_4515_tuj42611/registerUser.jsp?$args"
                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, obj,
                    object : Response.Listener<JSONObject> {
                        override fun onResponse(response: JSONObject?) {
                            try {
                                if(!response.toString().contains("already taken")){ //if this is a clean insert!
                                    Navigation.findNavController(layout).navigate(R.id.loginFrag)
                                }else{ // if we get some sort of error back
                                    var errorMsg = ""
                                    if(!response.toString().contains("email is already taken")){
                                        errorMsg = "That email is already taken"
                                    }else if(!response.toString().contains("username is already taken")){
                                        errorMsg = "That username is already taken"
                                    }
                                    Toast.makeText(requireContext(),
                                        errorMsg,Toast.LENGTH_LONG).show()
                                }
                                Log.d("SUCCESSFUL RESPONSE IN REGISTER", response.toString())
                            } catch (e: JSONException) {println("FROM CATCH IN RESPONSE.LISTENER>> " + response.toString()) }
                        }
                    },
                    Response.ErrorListener {
                        println("FROM REPSONS.ERRORLISTENER>> $it")
                    })
                requestQueue.add(jsonObjectRequest)
            }
        }

        return layout
    }
}