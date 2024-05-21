package fr.epf.min1.speedycart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Address
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.click
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class SignupShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_shop)

        val nameEditText = findViewById<EditText>(R.id.shop_signup_screen_name_edittext)
        val descriptionEditText = findViewById<EditText>(R.id.shop_signup_screen_description_edittext)
        val siretEditText = findViewById<EditText>(R.id.shop_signup_screen_siret_edittext)
        val addressNumberEditText = findViewById<EditText>(R.id.shop_signup_screen_adress_number_edittext)
        val addressNameEditText = findViewById<EditText>(R.id.shop_signup_screen_adress_name_edittext)
        val postalCodeEditText = findViewById<EditText>(R.id.shop_signup_screen_postal_code_edittext)
        val cityEditText = findViewById<EditText>(R.id.shop_signup_screen_city_edittext)
        val createButton = findViewById<Button>(R.id.shop_signup_screen_create_button)

        val user = intent.extras?.getParcelable(USER_EXTRA) as? User

        createButton.click {
            val newUser = User(
                1,
                user?.mail,
                user?.password,
                null,
                Shop(
                    1,
                    nameEditText.text.toString(),
                    descriptionEditText.text.toString(),
                    Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                    null,
                    siretEditText.text.toString(),
                    Address(
                        1,
                        addressNumberEditText.text.toString(),
                        addressNameEditText.text.toString(),
                        postalCodeEditText.text.toString(),
                        cityEditText.text.toString(),
                        null
                    )
                ),
                null,
                null
            )

            Log.d("shop_create", newUser.toString())
        }
    }
}