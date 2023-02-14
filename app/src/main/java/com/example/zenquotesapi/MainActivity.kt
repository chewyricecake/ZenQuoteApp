package com.example.zenquotesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenquotesapi.Adapter.RecyclerAdapter
import com.example.zenquotesapi.Extra.Events

import com.example.zenquotesapi.Models.Quotes
import com.example.zenquotesapi.ViewModel.QuotesViewModel
import com.example.zenquotesapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val quotesViewModel: QuotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quotesViewModel.get()

        quotesViewModel.data.observe(this, Observer{
            when (it) {
                is Events.Loading ->{

                }
                is Events.Success ->{
                    it.let{
                        it.data?.let{it -> setAdapter(it)}
                    }
                }
                is Events.Error ->{
                    it.let{
                        binding.rcvMessage.text = it.msg.toString()
                    }
                }
            }
        })
    }

    fun setAdapter(list: Quotes) = binding.recyclerview.apply{
        adapter = RecyclerAdapter(list)
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}