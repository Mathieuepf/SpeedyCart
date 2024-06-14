package fr.epf.min1.speedycart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.ui.adapters.DeliveryAdapter

class DeliveryListFragment : Fragment() {
    private lateinit var deliveryRecyclerView: RecyclerView
    private val deliveryListLiveData = MutableLiveData<List<OrderDTO>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deliveryRecyclerView = view.findViewById(R.id.fragment_delivery_list_recyclerview)
        deliveryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        deliveryListLiveData.observe(viewLifecycleOwner, Observer { deliveryList ->
            if (deliveryList != null) {
                val adapter = DeliveryAdapter(deliveryList)
                deliveryRecyclerView.adapter = adapter
            }
        })
    }

    fun setDeliveryList(deliveryList: List<OrderDTO>) {
        deliveryListLiveData.value = deliveryList
    }
}