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
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val TAG = "AdminAccountActivity"

class AdminAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_account)

        val repository = AppRepository(application)

        lifecycleScope.launch {

            // find user in local database
            val userDTO = findUserInLDB(repository)

            // find user in remote database
            val user = tryfetchAdmin(userDTO.id)

            // show user Info
            setUserInfoOnPage(user)

            // user can logout
            setLogoutButton(repository, userDTO)
        }

        // call navBar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.admin_account_navbar_fragment_container, NavigationBarFragment())
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
        val logoutButton = findViewById<ImageButton>(R.id.admin_account_logout_imagebutton)
        logoutButton.setOnClickListener {

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    repository.deleteUser(userDTO)
                }
                val intent = Intent(this@AdminAccountActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setUserInfoOnPage(user: User?) {
        val descriptTextView = findViewById<TextView>(R.id.admin_account_description)
        if (user != null) {
            descriptTextView.text = user.admin!!.description
        } else {
            descriptTextView.text = "error"
        }
    }

    private fun tryfetchAdmin(id: Long): User? {
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getUserById(id)
                if (response.isSuccessful
                    && response.body() != null
                    && response.body()!!.admin != null
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