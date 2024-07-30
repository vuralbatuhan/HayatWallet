package com.example.hayatwallet.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.hayatwallet.R
import com.example.hayatwallet.databinding.SpecialLayoutBinding

class SpecialView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: SpecialLayoutBinding

    init {
        binding = SpecialLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SpecialView)
        try {
            val title = ta.getString(R.styleable.SpecialView_title)
            val result = ta.getString(R.styleable.SpecialView_result)
            binding.textViewTitle.text = title
            binding.textViewResult.text = result
        } finally {
            ta.recycle()
        }
    }

    fun updateUI(title: String, result: String) {
        binding.textViewTitle.text = title
        binding.textViewResult.text = result
    }
}
