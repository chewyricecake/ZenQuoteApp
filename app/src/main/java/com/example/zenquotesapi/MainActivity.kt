package com.example.zenquotesapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenquotesapi.Adapter.QuotesAdapter
import com.example.zenquotesapi.Extra.Events

import com.example.zenquotesapi.ViewModel.ViewModel
import com.example.zenquotesapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quotesAdapter: QuotesAdapter by lazy{QuotesAdapter()}
    private val viewModel: ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ADD SPLASH SCREEN

        viewModel.getQuotes()
        viewModel.quotes.observe(this, Observer{
            when (it) {
                is Events.Loading ->{
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Events.Success ->{
                    binding.progressbar.visibility = View.GONE
                    binding.recyclerview.setHasFixedSize(true)
                    binding.recyclerview.layoutManager = LinearLayoutManager(this)
                    it.let{
                        it.data?.let{it -> quotesAdapter.differ.submitList(it) }
                    }
                    binding.recyclerview.adapter = quotesAdapter

                    quotesAdapter.onItemClick = {
                        val intent = Intent(this,QuoteDetail::class.java)
                        intent.putExtra("Quote",it)
                        startActivity(intent)
                    }
                }
                is Events.Error ->{
                    binding.progressbar.visibility = View.GONE
                    it.let{
                        binding.rcvMessage.text = it.msg.toString()
                    }
                }
            }
        })
    }

}