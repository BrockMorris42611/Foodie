package temple.edu.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation


class LoginFrag : Fragment() {

    lateinit var layout : View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = inflater.inflate(R.layout.fragment_login, container, false)
        val login : Button = layout.findViewById(R.id.loginButton)
        val creatAcc : TextView = layout.findViewById(R.id.createAccTV)
        login.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.action_loginFrag_to_menuFrag)
        }
        creatAcc.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.action_loginFrag_to_registerFrag)
        }
        return layout
    }

}