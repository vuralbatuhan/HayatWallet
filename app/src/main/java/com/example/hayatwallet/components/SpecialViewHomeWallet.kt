package com.example.hayatwallet.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.hayatwallet.R
import com.example.hayatwallet.databinding.SpecialLayoutHomeWalletBinding

class SpecialViewHomeWallet @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: SpecialLayoutHomeWalletBinding

    init {
        binding = SpecialLayoutHomeWalletBinding.inflate(LayoutInflater.from(context), this, true)
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SpecialViewHomeWallet)
        try {
            val marka = ta.getString(R.styleable.SpecialViewHomeWallet_marka)
            val time = ta.getString(R.styleable.SpecialViewHomeWallet_time)
            val money = ta.getString(R.styleable.SpecialViewHomeWallet_money)
            val image = ta.getString(R.styleable.SpecialViewHomeWallet_image)
            binding.textSpecialHomeMarka.text = marka
            binding.textSpecialHometime.text = time
            binding.textSpecialHomeMoney.text = money
            Glide.with(binding.imageSpecialHomeWallet).load(image).into(binding.imageSpecialHomeWallet)
        } finally {
            ta.recycle()
        }
    }

    fun updateUI(marka: String, money: String, time: String, image: Int) {
        binding.textSpecialHomeMarka.text = marka
        binding.textSpecialHometime.text = time
        binding.textSpecialHomeMoney.text = money
        Glide.with(binding.imageSpecialHomeWallet).load(image).circleCrop().into(binding.imageSpecialHomeWallet)
    }
}