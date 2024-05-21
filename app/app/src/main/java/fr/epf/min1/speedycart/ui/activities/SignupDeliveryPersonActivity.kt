package fr.epf.min1.speedycart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.datepicker.MaterialDatePicker
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Address
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.data.DeliveryPerson
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.click
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class SignupDeliveryPersonActivity : AppCompatActivity() {
    var epochDate: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_delivery_person)

        val lastNameEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_lastname_edittext)
        val firstNameEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_firstname_edittext)
        val dobButton = findViewById<Button>(R.id.deliveryperson_signup_screen_dob_button)
        val vehicleEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_vehicle_edittext)
        val addressNumberEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_adress_number_edittext)
        val addressNameEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_adress_name_edittext)
        val postalCodeEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_postal_code_edittext)
        val cityEditText = findViewById<EditText>(R.id.deliveryperson_signup_screen_city_edittext)
        val createButton = findViewById<Button>(R.id.deliveryperson_signup_screen_create_button)

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setTitleText("Votre date de naissance")
            .build()

        val user = intent.extras?.getParcelable(USER_EXTRA) as? User

        dobButton.click {
            datePicker.show(supportFragmentManager, "tag")
        }

        createButton.click {
            epochDate = datePicker.selection?.toLong() ?: 0L
            val newUser = User(
                1,
                user?.mail,
                user?.password,
                null,
                null,
                DeliveryPerson(
                    1,
                    firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(),
                    vehicleEditText.text.toString(),
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(epochDate), ZoneOffset.UTC),
                    LocalDateTime.now(),
                    null,
                    Address(
                        1,
                        addressNumberEditText.text.toString(),
                        addressNameEditText.text.toString(),
                        postalCodeEditText.text.toString(),
                        cityEditText.text.toString(),
                        null
                    )
                ),
                null
            )

            Log.d("deliveryperson_create", newUser.toString())
        }
    }
}