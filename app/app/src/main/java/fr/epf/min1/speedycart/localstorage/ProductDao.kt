package fr.epf.min1.speedycart.localstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.min1.speedycart.data.ProductDTO

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(productDTO: ProductDTO)

    @Query("SELECT * FROM product")
    fun getAll(): List<ProductDTO>

    @Delete
    fun deleteProduct(productDTO: ProductDTO)

    @Query("DELETE FROM product")
    fun wipeCart()
}