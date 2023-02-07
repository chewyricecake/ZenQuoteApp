package com.example.zenquotesapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zenquotesapi.Models.Quotes
import com.example.zenquotesapi.R

class RecyclerAdapter (val quotes: Quotes): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    class ViewHolder(recyclerView: View): RecyclerView.ViewHolder(recyclerView) {
        var quote: TextView
        var author: TextView
        init{
            quote = recyclerView.findViewById(R.id.quote)
            author = recyclerView.findViewById(R.id.author)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recyclerView = LayoutInflater.from(parent.context).inflate(R.layout.quote_item,parent,false)
        return ViewHolder(recyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.quote.text = quotes[position].q
        holder.author.text = quotes[position].a
    }

    override fun getItemCount(): Int {
        return quotes.size
    }


}