package com.example.zenquotesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.zenquotesapi.Models.Quotes.Quote
import com.example.zenquotesapi.ViewModel.ViewModel
import com.example.zenquotesapi.databinding.ActivityQuoteDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteDetail : AppCompatActivity() {
    private lateinit var binding: ActivityQuoteDetailBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quote = intent.getParcelableExtra<Quote>("Quote")
        if(quote!=null){
            binding.quote.text = quote.q
            binding.author.text = quote.a
        }


        viewModel.getRandomPicture()
        viewModel.randomPicture.observe(this, Observer {
            binding.image.load(it)
            binding.progressBar.visibility = View.GONE

        })

    }
}