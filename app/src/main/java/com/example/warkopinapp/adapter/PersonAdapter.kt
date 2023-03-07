package com.example.warkopinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warkopinapp.R
import com.example.warkopinapp.model.Person

class PersonAdapter(private val listPerson: ArrayList<Person>) :
    RecyclerView.Adapter<PersonAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_name_email, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, name, email) = listPerson[position]
        holder.id.text = id.toString()
        holder.tvName.text = name
        holder.tvEmail.text = email
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPerson[holder.adapterPosition])
        }

    }

    override fun getItemCount(): Int = listPerson.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.tv_item_id)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_item_email)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Person)
    }
}