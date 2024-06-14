package fr.epf.min1.speedycart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.MainActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.TypeUser
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.ui.activities.AdminAccountActivity
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.DeliveryListActivity
import fr.epf.min1.speedycart.ui.activities.DeliveryPersonAccountActivity
import fr.epf.min1.speedycart.ui.activities.LoginActivity
import fr.epf.min1.speedycart.ui.activities.ShopAccountActivity
import fr.epf.min1.speedycart.ui.activities.ShopCartActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        shopCartButton(view)

//        val shopCartButton = view.findViewById<ImageButton>(R.id.fragment_navbar_cart_imagebutton)
//        shopCartButton.click {
//            //val intent = Intent(view.context, ShopCartActivity::class.java)
//            val intent = Intent(view.context, DeliveryListActivity::class.java)
//            startActivity(intent)
//        }

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

    private fun shopCartButton(view: View){
        val cartButton =
            view.findViewById<ImageButton>(R.id.fragment_navbar_cart_imagebutton)
        val repository = AppRepository(requireActivity().application)

        cartButton.click {
            lifecycleScope.launch {
                val users = withContext(Dispatchers.IO) {
                    repository.getUser()
                }
                val intent = if(users.isEmpty()){
                    Intent(view.context, LoginActivity::class.java)
                } else {
                    when (users[0].typeUser) {
                        TypeUser.CLIENT -> Intent(view.context, ShopCartActivity::class.java)
                        TypeUser.DELIVERYPERSONNE -> Intent(view.context, DeliveryListActivity::class.java)
                        TypeUser.SHOP -> Intent(view.context, MainActivity::class.java)
                        else -> Intent(view.context, MainActivity::class.java)
                    }
                }
                startActivity(intent)
            }
        }
    }
}