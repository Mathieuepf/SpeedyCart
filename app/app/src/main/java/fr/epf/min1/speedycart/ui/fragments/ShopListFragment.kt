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
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.ui.adapters.ShopAdapter

class ShopListFragment : Fragment() {
    private lateinit var shopRecyclerView: RecyclerView
    private val shopListLiveData = MutableLiveData<List<Shop>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopRecyclerView = view.findViewById(R.id.fragment_shop_list_recyclerview)
        shopRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        shopListLiveData.observe(viewLifecycleOwner, Observer { shopList ->
            if (shopList != null) {
                val adapter = ShopAdapter(shopList)
                shopRecyclerView.adapter = adapter
            }
        })
    }

    fun setShopList(shopList: List<Shop>) {
        shopListLiveData.value = shopList
    }
}