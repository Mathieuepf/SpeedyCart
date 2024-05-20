package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.click

const val USER_EXTRA: String = "user"

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val userMailEditText = findViewById<EditText>(R.id.signup_screen_mail_edittext)
        val userPasswordEditText = findViewById<EditText>(R.id.signup_screen_password_edittext)
        val userPasswordRetypeEditText = findViewById<EditText>(R.id.signup_screen_password_retype_edittext)
        val clientSignupButton = findViewById<Button>(R.id.signup_screen_client_button)
        val shopSignupButton = findViewById<Button>(R.id.signup_screen_shop_button)
        val deliveryPersonSignupButton = findViewById<Button>(R.id.signup_screen_delivery_person_button)

        clientSignupButton.click {
            if(userPasswordEditText.text.toString() == userPasswordRetypeEditText.text.toString()){
                val intent = Intent(this, SignupClientActivity::class.java)
                intent.putExtra("user", User(
                    null,
                    userMailEditText.text.toString(),
                    userPasswordEditText.text.toString(),
                    null,
                    null,
                    null,
                    null
                ))
                startActivity(intent)
            }
        }

        shopSignupButton.click {
            if(userPasswordEditText.text.toString() == userPasswordRetypeEditText.text.toString()){
                val intent = Intent(this, SignupShopActivity::class.java)
                intent.putExtra("user", User(
                    null,
                    userMailEditText.text.toString(),
                    userPasswordEditText.text.toString(),
                    null,
                    null,
                    null,
                    null
                ))
                startActivity(intent)
            }
        }

        deliveryPersonSignupButton.click {
            if(userPasswordEditText.text.toString() == userPasswordRetypeEditText.text.toString()){
                val intent = Intent(this, SignupDeliveryPersonActivity::class.java)
                intent.putExtra("user", User(
                    null,
                    userMailEditText.text.toString(),
                    userPasswordEditText.text.toString(),
                    null,
                    null,
                    null,
                    null
                ))
                startActivity(intent)
            }
        }
    }
}