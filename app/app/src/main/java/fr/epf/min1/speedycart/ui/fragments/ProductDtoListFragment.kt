package fr.epf.min1.speedycart.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.ProductDTO2
import fr.epf.min1.speedycart.ui.adapters.ProductDtoAdapter

class ProductDtoListFragment : Fragment() {
    private lateinit var productDtoRecyclerView: RecyclerView
    private val productDtoListLiveData = MutableLiveData<List<ProductDTO2>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_dto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDtoRecyclerView = view.findViewById(R.id.fragment_product_dto_list_recyclerview)
        productDtoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        productDtoListLiveData.observe(viewLifecycleOwner, Observer { productDtoList ->
            if (productDtoList != null) {
                val adapter = ProductDtoAdapter(productDtoList)
                productDtoRecyclerView.adapter = adapter
            }
        })
    }

    fun setproductDtoList(productDtoList: List<ProductDTO2>) {
        productDtoListLiveData.value = productDtoList
    }
}