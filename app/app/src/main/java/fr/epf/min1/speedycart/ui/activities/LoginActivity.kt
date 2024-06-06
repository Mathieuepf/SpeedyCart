package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.LoginDTO
import fr.epf.min1.speedycart.data.TypeUser
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.UserDTO
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // check entry and save user
        val loginButton = findViewById<Button>(R.id.login_screen_login_button)
        val emailText = findViewById<EditText>(R.id.login_screen_mail_edittext)
        val passwordText = findViewById<EditText>(R.id.login_screen_password_edittext)

        loginButton.setOnClickListener {
            val loginDto = LoginDTO(
                emailText.text.toString(),
                passwordText.text.toString()
            )

            lifecycleScope.launch {
                // fetch api login
                val user = searchIfUserReel(loginDto)

                // save user info in local data base
                if (user != null) putUserInfoInDB(user)
            }
        }

        // go to sigh up page on click
        goToSignUpPage()
    }

    private fun goToSignUpPage() {
        // go to signup page
        val signuplink = findViewById<TextView>(R.id.login_screen_signuplink_textview)

        signuplink.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private suspend fun searchIfUserReel(loginDto: LoginDTO): User? {
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        try {
            val response = clientService.login(loginDto)
            if (response.isSuccessful) {
                val user = response.body()!!
                Log.d(TAG, "$user")
                shortToast("User found")
                return user
            } else {
                Log.d(TAG, "Bad request")
                shortToast("User not found")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
        return null
    }

    private fun shortToast(message: String) {
        Toast.makeText(
            this@LoginActivity,
            message, Toast.LENGTH_SHORT
        ).show()
    }

    private fun putUserInfoInDB(user: User) {
        val typeUser = findUserType(user)

        val repository = AppRepository(application)
        lifecycleScope.launch {

            val userDTO = UserDTO(
                user.id!!,
                user.mail,
                user.password,
                typeUser,
                findUserTypeId(user, typeUser)
            )

            withContext(Dispatchers.IO) {
                repository.setUser(userDTO)
            }
        }
    }

    private fun findUserType(user: User): TypeUser {
        return when {
            user.client != null -> TypeUser.CLIENT
            user.deliveryPerson != null -> TypeUser.DELIVERYPERSONNE
            user.admin != null -> TypeUser.ADMIN
            else -> TypeUser.SHOP
        }
    }

    private fun findUserTypeId(user: User, typeUser: TypeUser): Long {
        return when (typeUser) {
            TypeUser.CLIENT -> user.client!!.id!!
            TypeUser.SHOP -> user.shop!!.id!!
            TypeUser.ADMIN -> user.admin!!.id!!
            TypeUser.DELIVERYPERSONNE -> user.deliveryPerson!!.id!!
        }
    }
}