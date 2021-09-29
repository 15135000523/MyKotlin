package com.example.mykotlin.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.R


class MyAdapter(private val mNames: Array<String>, var onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<MyAdapter.Holder>() {
    private lateinit var backView:TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = View.inflate(parent.context, R.layout.item, null)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.text.text = mNames[position]
        holder.text.setOnClickListener {
            onClick(position)
        }
        holder.itemView.tag = position
    }

    override fun getItemCount(): Int {
        return mNames.size
    }

    inner class Holder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)

    }

}