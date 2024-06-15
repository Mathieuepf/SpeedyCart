package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.transferwise.sequencelayout.SequenceStep
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.data.Delivery
import fr.epf.min1.speedycart.data.DeliveryPerson
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val TAG = "DeliveryStatusActivity"
private lateinit var client: Client
private lateinit var deliveryToUpdate: Delivery

class DeliveryStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delivery_status)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val step1 = findViewById<View>(R.id.step1) as SequenceStep
        val step2 = findViewById<View>(R.id.step2) as SequenceStep
        val step3 = findViewById<View>(R.id.step3) as SequenceStep
        val step4 = findViewById<View>(R.id.step4) as SequenceStep
        val button = findViewById<Button>(R.id.delivery_status_screen_button)
        val endMessage = findViewById<TextView>(R.id.delivery_status_screen_end_message_textview)
        endMessage.text = ""

        lifecycleScope.launch {
            when(init()){
                4 -> {
                    step1.setTitle("Votre commande à été reçu par notre service")
                    step2.setTitle("Votre commande est en attente d'un livreur disponible")
                    step3.setTitle("Votre commande à été prise en charge par un livreur")
                    step4.setTitle("Votre commande est arrivée !")
                    endMessage.text = "Vous avez récupéré cette commande"
                }
                3 ->{
                    step1.setTitle("Votre commande à été reçu par notre service")
                    step2.setTitle("Votre commande est en attente d'un livreur disponible")
                    step3.setTitle("Votre commande à été prise en charge par un livreur")
                    step4.setTitle("Votre commande est arrivée !")
                    button.isEnabled = true
                }
                2 -> {
                    step1.setTitle("Votre commande à été reçu par notre service")
                    step2.setTitle("Votre commande est en attente d'un livreur disponible")
                    step3.setTitle("Votre commande à été prise en charge par un livreur")
                    step3.setActive(true)
                }
                1 -> {
                    step1.setTitle("Votre commande à été reçu par notre service")
                    step2.setTitle("Votre commande est en attente d'un livreur disponible")
                    step2.setActive(true)
                }
                0 -> {
                    step1.setTitle("Votre commande à été reçu par notre service")
                    step1.setActive(true)
                }
            }

            button.click {
                setGot(deliveryToUpdate)
            }
        }


        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_status_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    private fun init(): Int{

        val testDel = Delivery(
            null,
            20.00,
            null,
            true,
            true,
            true,
            false,
            false,
            DeliveryPerson.generate1DeliveryPerson()
        )

        //var client = Client.generate1Client()
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)
        val repository = AppRepository(application)
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                client = tryfetchClient(repository.getUser().first().id)!!.client!!
            }
        }
        return runBlocking {
            val response = clientService.getOrders()
            if(response.isSuccessful){
                val clientOrderList = response.body()!!.filter { order -> order.order.client.id == client.id }
                Log.d(TAG, clientOrderList.size.toString())
                val lastOrder = clientOrderList.last().order
                deliveryToUpdate = lastOrder.delivery

                val boolList: List<Boolean> = listOf(lastOrder.delivery.got, lastOrder.delivery.prepared, lastOrder.delivery.accepted, lastOrder.delivery.delivered)
                //Log.d(TAG, boolList.toString())
                val boolListTrue: List<Boolean> = boolList.filter { bool -> bool == true}
                Log.d(TAG, boolListTrue.toString())
                boolListTrue.size
            }else{
                0
            }
        }
    }

    private fun tryfetchClient(id: Long): User? {
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getUserById(id)
                if (response.isSuccessful
                    && response.body() != null
                    && response.body()!!.client != null
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

    private fun setGot(delivery: Delivery){
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)

        runBlocking {
            try {
                clientService.setDeliveryGot(delivery.id!!, delivery)
                startActivity(Intent(this@DeliveryStatusActivity, DeliveryStatusActivity::class.java))
            }catch (e: Exception){
                throw Exception(e.message)
            }
        }
    }
}