package com.example.shop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.shop.R
import com.example.shop.databinding.ActivityProfileBinding
import com.example.shop.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.logOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
        }
        initBanner()
    }

    private fun initBanner() {
        binding.homeBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@ProfileActivity,
                    MainActivity::class.java
                )
            )
        }
        binding.cartBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@ProfileActivity,
                    CartActivity::class.java
                )
            )
        }
    }
}