package com.example.zenquotesapi.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.zenquotesapi.Models.Quotes.Quote
import com.example.zenquotesapi.databinding.QuoteItemBinding

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.ViewHolder>(){

    var onItemClick : ((Quote)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(val binding: QuoteItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun setData(quote: Quote){
            binding.apply{
                quoteText.text=quote.q
                authorText.text=quote.a
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<Quote>(){
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.q == newItem.q
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}