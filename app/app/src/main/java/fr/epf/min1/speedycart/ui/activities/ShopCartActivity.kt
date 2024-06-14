package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.MainActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Address
import fr.epf.min1.speedycart.data.Delivery
import fr.epf.min1.speedycart.data.Order
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.ProductDTO
import fr.epf.min1.speedycart.data.ProductDTO2
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.data.UserDTO
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.adapters.DELETE_CART_EXTRA
import fr.epf.min1.speedycart.ui.adapters.ProductCartAdapter
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import sqip.Callback
import sqip.CardEntry
import sqip.CardEntry.DEFAULT_CARD_ENTRY_REQUEST_CODE
import sqip.CardEntryActivityResult
import java.time.LocalDateTime

private const val TAG = "ShopCartActivity"

class ShopCartActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)

        val nameContainer = findViewById<TextView>(R.id.shop_cart_screen_name_container_textview)
        val adressContainer = findViewById<TextView>(R.id.shop_cart_screen_adress_container_textview)
        val productRecycler = findViewById<RecyclerView>(R.id.shop_cart_screen_product_list_recyclerview)
        val paymentButton = findViewById<Button>(R.id.shop_cart_screen_payment_button)

        //val testClient = Client.generate1Client()

        //nameContainer.text = "${testClient.firstname} ${testClient.lastname}"

        productRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val repository = AppRepository(application)
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                if(intent.extras != null){
                    repository.deleteFromCart(intent.extras?.getParcelable(DELETE_CART_EXTRA, ProductDTO::class.java)!!)
                }

                val productList = repository.getCart()
                val productAdapter = ProductCartAdapter(productList)
                productRecycler.adapter = productAdapter

                val userDTO = findUserInLDB(repository)
                val user = tryfetchClient(userDTO.id)

                nameContainer.text = "${user!!.client!!.lastname} ${user.client!!.firstname}"
                adressContainer.text = "10 Rue Monge, 34070 Montpellier"
            }
        }

        paymentButton.setOnClickListener {
            CardEntry.startCardEntryActivity(this, true, DEFAULT_CARD_ENTRY_REQUEST_CODE)
        }

        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.cart_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        CardEntry.handleActivityResult(data, object : Callback<CardEntryActivityResult> {
            override fun onResult(result: CardEntryActivityResult) {
                when {
                    result.isSuccess() -> {
                        val cardResult = result.getSuccessValue()
                        val card = cardResult.card
                        val nonce = cardResult.nonce
                        Log.d("ShopCartActivity", "paiement accepté")
                        lifecycleScope.launch {
                            withContext(Dispatchers.IO){
                                addOrder(AppRepository(application).getCart(), AppRepository(application))
                            }
                        }
                    }
                    result.isCanceled() -> {
                        Log.d("ShopCartActivity", "paiement annulé")
                    }
                }
            }
        })
    }

    private suspend fun findUserInLDB(repository: AppRepository): UserDTO {
        val usersDTO = withContext(Dispatchers.IO) {
            repository.getUser()
        }
        return usersDTO[0]
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

    private fun addOrder(prodList: List<ProductDTO>, repo: AppRepository){
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val prodListAPI = toDTOAPI(prodList)
                val client = tryfetchClient(repo.getUser().first().id)!!.client
                val date = LocalDateTime.now().plusHours(2)
                Log.d(TAG, LocalDateTime.now().plusHours(2).toString())
                val delivery = Delivery(
                    null,
                    20.00,
                    date,
                    false,
                    false,
                    false,
                    false,
                    false,
                    null
                )
                val shipAddress = Address.generate1Address()

                val orderToAdd = OrderDTO(
                    Order(
                        null,
                        LocalDateTime.now(),
                        false,
                            client!!,
                            delivery,
                            shipAddress,
                            shipAddress
                        ),
                    prodListAPI
                )
                Log.d(TAG, orderToAdd.toString())
                try {
                    val response = clientService.addOrder(orderToAdd)
                    if(response.isSuccessful){
                        val intent = Intent(this@ShopCartActivity, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        throw Exception("commande pas passée")
                    }
                }catch (e: Exception){
                    Log.d(TAG, "erreur de commande")
                    throw Exception(e.message)
                }
            }
        }
    }

    private fun toDTOAPI(productsDTO: List<ProductDTO>): List<ProductDTO2>{
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)
        var result = emptyList<ProductDTO2>()

        runBlocking{
            try {
                val response = clientService.getProducts()
                if(response.isSuccessful){
                    val listProd = response.body()
                    result = productsDTO.map { product -> ProductDTO2(
                        listProd!!.find { prod -> prod.id == product.id }!!,
                        1
                    ) }
                }else{
                    throw Exception("liste non récupérée")
                }
            }catch (e: Exception){
                throw Exception(e.message)
            }
        }

        return result
    }
}