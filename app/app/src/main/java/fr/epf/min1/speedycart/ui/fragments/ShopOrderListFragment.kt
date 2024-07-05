package fr.epf.min1.speedycart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.ui.adapters.ShopOrderAdapter

class ShopOrderListFragment : Fragment() {
    private lateinit var orderRecyclerView: RecyclerView
    private val orderListLiveData = MutableLiveData<List<OrderDTO>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_order_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderRecyclerView = view.findViewById(R.id.fragment_shop_order_recyclerview)
        orderRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        orderListLiveData.observe(viewLifecycleOwner, Observer { orderList ->
            if (orderList != null) {
                val adapter = ShopOrderAdapter(orderList)
                orderRecyclerView.adapter = adapter
            }
        })
    }

    fun setOrderList(orderList: List<OrderDTO>) {
        orderListLiveData.value = orderList
    }
}