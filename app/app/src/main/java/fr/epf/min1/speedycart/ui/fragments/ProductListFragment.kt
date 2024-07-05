package fr.epf.min1.speedycart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.ui.adapters.ProductAdapter

class ProductListFragment : Fragment() {
    private lateinit var productRecyclerView: RecyclerView
    private val productListLiveData = MutableLiveData<List<Product>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRecyclerView = view.findViewById(R.id.fragment_product_list_recyclerview)
        productRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        productListLiveData.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                val adapter = ProductAdapter(productList)
                productRecyclerView.adapter = adapter
            }
        })
    }

    fun setProductList(productList: List<Product>) {
        productListLiveData.value = productList
    }
}