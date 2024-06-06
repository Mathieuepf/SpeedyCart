package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.TypeUser
import fr.epf.min1.speedycart.data.UserDTO
import fr.epf.min1.speedycart.localstorage.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signuplink = findViewById<TextView>(R.id.login_screen_signuplink_textview)

        signuplink.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        // TODO("Remove this part with real login")
        val loginButton = findViewById<Button>(R.id.login_screen_login_button)
        val emailText = findViewById<EditText>(R.id.login_screen_mail_edittext)
        var typeUser = TypeUser.ADMIN
        loginButton.setOnClickListener {
            when (emailText.text.toString()) {
                "client" -> typeUser = TypeUser.CLIENT
                "restaurateur" -> typeUser = TypeUser.SHOP
                "livreur" -> typeUser = TypeUser.DELIVERYPERSONNE
                else -> {}
            }

            val repository = AppRepository(application)
            lifecycleScope.launch {
                val userDTO = UserDTO(1L, typeUser.toString(), typeUser.toString(), typeUser, 1L)
                withContext(Dispatchers.IO) {
                    repository.setUser(userDTO)
                }
                val usersDTO = withContext(Dispatchers.IO) {
                    repository.getUser()
                }
                if (usersDTO.isNotEmpty()) {
                    Toast.makeText(
                        this@LoginActivity,
                        "user trouvé : ${usersDTO}", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "user non trouvé", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}