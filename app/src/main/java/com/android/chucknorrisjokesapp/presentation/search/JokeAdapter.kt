package com.android.chucknorrisjokesapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.chucknorrisjokesapp.databinding.ItemJokeBinding
import com.android.chucknorrisjokesapp.presentation.model.JokeVM

class JokeAdapter(
    private val itemClickListener : (JokeVM) -> Unit
) : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    private val jokeList = ArrayList<JokeVM>()

    fun clearList() {
        jokeList.clear()
        notifyDataSetChanged()
    }

    fun setJokeList(jokes: List<JokeVM>) {
        jokeList.addAll(jokes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(
            ItemJokeBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokeList[position], itemClickListener)
    }

    override fun getItemCount(): Int = jokeList.size

    class JokeViewHolder(
        private val binding: ItemJokeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(jokeVM: JokeVM, itemClickListener: (JokeVM) -> Unit) {
            binding.joke = jokeVM
            binding.root.setOnClickListener {
                itemClickListener(jokeVM)
            }
        }

    }

}