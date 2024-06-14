package fr.epf.min1.speedycart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.MainActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.TypeUser
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.activities.AdminAccountActivity
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.DeliveryListActivity
import fr.epf.min1.speedycart.ui.activities.DeliveryPersonAccountActivity
import fr.epf.min1.speedycart.ui.activities.DeliveryPersonDeliveryPlanActivity
import fr.epf.min1.speedycart.ui.activities.LoginActivity
import fr.epf.min1.speedycart.ui.activities.ShopAccountActivity
import fr.epf.min1.speedycart.ui.activities.ShopCartActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val TAG = "NavigationBarFragment"

class NavigationBarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_navigation_bar, container, false)

        val homeButton = view.findViewById<ImageButton>(R.id.fragment_navbar_home_imagebutton)
        homeButton.click {
            val intent = Intent(view.context, MainActivity::class.java)
            startActivity(intent)
        }

        // show user login or account on button click
        loginAccountUserButton(view)

        // show cart
        shopCartButton(view)

        return view
    }

    private fun loginAccountUserButton(view: View) {
        val userDetailsButton =
            view.findViewById<ImageButton>(R.id.fragment_navbar_user_imagebutton)
        val repository = AppRepository(requireActivity().application)

        userDetailsButton.click {

            lifecycleScope.launch {
                val users = withContext(Dispatchers.IO) {
                    repository.getUser()
                }
                val intent = if (users.isEmpty()) {
                    Intent(view.context, LoginActivity::class.java)
                } else {
                    when (users[0].typeUser) {
                        TypeUser.CLIENT -> Intent(view.context, ClientAccountActivity::class.java)
                        TypeUser.SHOP -> Intent(view.context, ShopAccountActivity::class.java)
                        TypeUser.ADMIN -> Intent(view.context, AdminAccountActivity::class.java)
                        else -> Intent(view.context, DeliveryPersonAccountActivity::class.java)
                    }
                }
                startActivity(intent)
            }
        }
    }

    private fun intentForDeliveryPerson(view: View, id: Long): Intent {
        val waitingOrders = fetchDeliveryByDeliveryPersonInfo(id)
        Log.d(TAG, waitingOrders.toString())
        return if (waitingOrders.isEmpty()) {
            Intent(view.context, DeliveryListActivity::class.java)
        } else {
            Intent(view.context, DeliveryPersonDeliveryPlanActivity::class.java)
        }
    }

    private fun shopCartButton(view: View) {
        val cartButton =
            view.findViewById<ImageButton>(R.id.fragment_navbar_cart_imagebutton)
        val repository = AppRepository(requireActivity().application)

        cartButton.click {
            lifecycleScope.launch {
                val users = withContext(Dispatchers.IO) {
                    repository.getUser()
                }
                val intent = if (users.isEmpty()) {
                    Intent(view.context, LoginActivity::class.java)
                } else {
                    when (users[0].typeUser) {
                        TypeUser.CLIENT -> Intent(view.context, ShopCartActivity::class.java)
                        TypeUser.DELIVERYPERSONNE -> intentForDeliveryPerson(
                            view,
                            users[0].typeUserId
                        )

                        TypeUser.SHOP -> Intent(view.context, MainActivity::class.java)
                        else -> Intent(view.context, MainActivity::class.java)
                    }
                }
                startActivity(intent)
            }
        }
    }

    private fun fetchDeliveryByDeliveryPersonInfo(id: Long): List<OrderDTO> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getDeliveryWaitingByDeliveryPerson(id)
                if (response.isSuccessful && response.body() != null) {
                    val orderDTOS = response.body()!!
                    Log.d(TAG, "$orderDTOS")
                    orderDTOS
                } else {
                    Log.d(TAG, "call for waiting delivery list is empty or unsuccessful")
                    emptyList()
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emptyList()
            }
        }
    }
}