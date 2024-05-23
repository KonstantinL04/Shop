package com.example.shop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.shop.Adapter.CartAdapter
import com.example.shop.Helper.ManagmentCart
import com.example.shop.databinding.FragmentCartBinding
import com.example.shop.network.ExchangeRateService
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var exchangeRate: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managmentCart = ManagmentCart(requireContext())

        setVariable()
        initCartList()
        fetchExchangeRateAndCalculateCart()
    }

    private fun initCartList() {
        with(binding) {
            emptyTxt.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }

        binding.viewCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter =
            CartAdapter(managmentCart.getListCart(), requireContext(), object : ChangeNumberItemsListener {
                override fun onChanged() {
                    fetchExchangeRateAndCalculateCart()
                }
            })
    }

    private fun fetchExchangeRateAndCalculateCart() {
        lifecycleScope.launch {
            exchangeRate = ExchangeRateService.fetchExchangeRate() ?: 1.0
            calculateCart()
        }
    }

    private fun calculateCart() {
        val totalRub = Math.round(((managmentCart.getTotalFee()) * 100) / 100)
        val totalUsd = Math.round((totalRub / exchangeRate) * 100) / 100.0

        with(binding) {
            totalFeeTxt.text = "$totalRub₽"
            totalTxt.text = "$totalRub₽"
            totalFeeUsdTxt.text = "$totalUsd$"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { activity?.onBackPressed() }
    }
}
