package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.MainActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.UserDTO
import fr.epf.min1.speedycart.data.getCompleteAddress
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val TAG = "ShopAccountActivity"

class ShopAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_account)

        val repository = AppRepository(application)

        lifecycleScope.launch {

            // find user in local database
            val userDTO = findUserInLDB(repository)

            // find user in remote database
            val user = tryfetchShop(userDTO.id)

            // show user Info
            setUserInfoOnPage(user)

            // user can logout
            setLogoutButton(repository, userDTO)
        }

        // call navBar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.shop_account_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    private suspend fun findUserInLDB(repository: AppRepository): UserDTO {
        val usersDTO = withContext(Dispatchers.IO) {
            repository.getUser()
        }
        return usersDTO[0]
    }

    private fun setLogoutButton(repository: AppRepository, userDTO: UserDTO) {
        val logoutButton = findViewById<ImageButton>(R.id.shop_account_logout_imagebutton)
        logoutButton.setOnClickListener {

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    repository.deleteUser(userDTO)
                }
                val intent = Intent(this@ShopAccountActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setUserInfoOnPage(user: User?) {
        val nameTextView = findViewById<TextView>(R.id.shop_account_name_textview)
        val adddressTextView = findViewById<TextView>(R.id.shop_account_address_textview)
        val siretTextView = findViewById<TextView>(R.id.shop_account_siret_textview)
        val descripTextView = findViewById<TextView>(R.id.shop_account_description_textview)

        if (user != null) {
            nameTextView.text = user.shop!!.name
            adddressTextView.text = user.shop!!.getCompleteAddress()
            siretTextView.text = user.shop!!.siret
            descripTextView.text = user.shop!!.description
        } else {
            nameTextView.text = "error"
            adddressTextView.text = "error"
            siretTextView.text = "error"
            descripTextView.text = "error"
        }
    }

    private fun tryfetchShop(id: Long): User? {
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getUserById(id)
                if (response.isSuccessful
                    && response.body() != null
                    && response.body()!!.shop != null
                ) {
                    response.body()!!
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                null
            }
        }
    }
}