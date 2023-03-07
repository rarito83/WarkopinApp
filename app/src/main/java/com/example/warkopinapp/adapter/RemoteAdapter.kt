package com.example.warkopinapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warkopinapp.view.RemoteFragment

class RemoteAdapter : RecyclerView.Adapter<RemoteAdapter.ViewHolder>() {

    private val data = ArrayList<Remote>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(movieList: List<Remote>) {
        if (movieList.isNullOrEmpty()) return
        with(this.data) {
            clear()
            addAll(movieList)
            notifyDataSetChanged()
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: RemoteFragment) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = ItemRemoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class MovieViewHolder(private val binding: ItemRemoteeBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        @SuppressLint("SetTextI18n")
        fun bind(data: Remote) {
            with(binding) {

                movTitle.text = data.originalTitle

                movPopularity.text = data.popularity.toString() + " " + "Viewers"

                movRating.text = data.voteAverage.toString()

                movRelease.text = data.releaseDate

                Glide.with(itemView.context)
                    .load(IMAGE_URL + data.posterPath)
                    .into(imgPoster)

            }

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(data.id.toString())
                Log.d("Movie Fragment", "item movie click : ${data.id.toString()}")
            }
        }
    }
}