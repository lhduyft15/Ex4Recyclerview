package com.hnam.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(var items: ArrayList<Movie>, val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {

    lateinit var mListener: MovieItemCLickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        movieViewHolder.tvTitle.text = "#$position ${items[position].title}"
        movieViewHolder.tvOverview.text = items[position].overview

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/" + items[position].poster_path)
            .into(movieViewHolder.ivPoster)

        movieViewHolder.itemView.setOnClickListener {
            mListener.onItemCLicked(position)
        }

        movieViewHolder.itemView.setOnLongClickListener {
            mListener.onItemLongCLicked(position)
            true
        }
    }

    fun setListener(listener: MovieItemCLickListener) {
        this.mListener = listener
    }

    fun setData(items: ArrayList<Movie>){
        this.items = items
        notifyDataSetChanged()
    }

}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvTitle = view.tvTitle
    var tvOverview = view.tvOverview
    var ivPoster= view.ivPoster
}
