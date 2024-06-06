package fr.epf.min1.speedycart.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.adapters.ProductAdapter
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val FRAG_TAG = "ProductListFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mutableView: View? = null

        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        runBlocking {
            try {
                val response = clientService.getProducts()
                if (response.isSuccessful && response.body() != null) {
                    mutableView = inflater.inflate(R.layout.fragment_product_list, container, false)

                    val productRecyclerView = mutableView?.findViewById<RecyclerView>(R.id.fragment_product_recyclerview)
                    productRecyclerView?.layoutManager =
                        GridLayoutManager(this@ProductListFragment.context, 2, GridLayoutManager.VERTICAL, false)

                    val productList = response.body()!!
                    Log.d(FRAG_TAG, "$productList")
                    val productAdapter = ProductAdapter(productList)
                    productRecyclerView?.adapter = productAdapter
                } else {
                    mutableView = inflater.inflate(R.layout.fragment_product_empty, container, false)
                    Log.d(FRAG_TAG, "call for product list is empty or unsuccessful")
                }
            } catch (e: Exception) {
                mutableView = inflater.inflate(R.layout.fragment_product_empty, container, false)
                Log.d(FRAG_TAG, e.toString())
            }
        }

        return mutableView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}