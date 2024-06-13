package fr.epf.min1.speedycart.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Delivery
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.DeliveryListFragment
import fr.epf.min1.speedycart.ui.fragments.EmptyDeliveryFragment

class DeliveryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delivery_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initDeliveries()
    }

    private fun fetchDeliveries(): List<Delivery>{
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)
        val deliveries = Delivery.generateDeliveries()

        return deliveries.filter { d -> d.prepared }
    }

    private fun initDeliveries(){
        val deliveryList = fetchDeliveries()

        if(deliveryList.isEmpty()){
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_list_screen_fragment_container, EmptyDeliveryFragment())
                .commit()
        }else{
            val fragment = DeliveryListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_list_screen_fragment_container, fragment)
                .commit()
            fragment.setDeliveryList(deliveryList)
        }
    }
}