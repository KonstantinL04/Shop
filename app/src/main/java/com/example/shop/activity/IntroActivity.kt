package com.example.shop.activity

import android.content.Intent
import android.os.Bundle
import com.example.shop.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            startActivity(Intent(this@IntroActivity, RegisterActivity::class.java))
        }

        binding.loginTxt.setOnClickListener {
            startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
        }
    }

}