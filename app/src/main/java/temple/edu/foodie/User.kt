package temple.edu.foodie

import android.content.Context
import android.content.SharedPreferences

class User(var userId : Int, var email: String, var username: String, var password: String){

    object Api{
        private val SAVED_USER_FILE = "saved_user_file"
        private val USER_ID = "userId"

        fun setLocalUser(context:Context, userId : Int){
            context.getSharedPreferences(SAVED_USER_FILE, Context.MODE_PRIVATE)
                .edit().putInt(USER_ID, userId).apply() //used for API calls ;)
        }
        fun getLocalUser(context:Context) : Int{
            return context.getSharedPreferences(SAVED_USER_FILE, Context.MODE_PRIVATE)
                .getInt(USER_ID, 0)
        }
    }
}