package fr.epf.min1.speedycart.localstorage

import android.app.Application
import androidx.room.Room
import fr.epf.min1.speedycart.data.ProductDTO
import fr.epf.min1.speedycart.data.UserDTO

class AppRepository(application: Application) {
    private val userDao: UserDao
    private val productDao: ProductDao

    init {
        val db = Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            "speedycart-db"
        ).build()
        userDao = db.userDao()
        productDao = db.productDao()
    }

    fun getUser() = userDao.getAll()

    fun setUser(userDTO: UserDTO) {
        userDao.insertUser(userDTO)
    }

    fun deleteUser(userDTO: UserDTO) {
        userDao.delete(userDTO)
    }

    fun getCart() = productDao.getAll()

    fun addToCart(productDTO: ProductDTO){
        productDao.insertProduct(productDTO)
    }

    fun deleteFromCart(productDTO: ProductDTO){
        productDao.deleteProduct(productDTO)
    }

    fun wipeCart(){
        productDao.wipeCart()
    }
}