package com.example.shop.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.shop.R
import com.example.shop.databinding.ActivityMainBinding
import com.example.shop.fragment.CartFragment
import com.example.shop.fragment.FavotitsFragment
import com.example.shop.fragment.ProfileFragment
import com.example.shop.fragment.MainFragment
import com.example.shop.fragment.OrdersFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeBtn -> {
                    replaceFragment(MainFragment())
                    true
                }
                R.id.cartBtn -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.favoriteBtn -> {
                    replaceFragment(FavotitsFragment())
                    true
                }
                R.id.ordersBtn -> {
                    replaceFragment(OrdersFragment())
                    true
                }
                R.id.profileBtn -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Установите начальный фрагмент
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.homeBtn
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameL1, fragment)
        fragmentTransaction.commit()
    }
}