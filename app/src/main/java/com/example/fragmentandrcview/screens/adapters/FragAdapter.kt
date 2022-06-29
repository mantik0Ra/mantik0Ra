package com.example.fragmentandrcview.screens.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentandrcview.R
import com.example.fragmentandrcview.data.Result
import com.example.fragmentandrcview.screens.RcViewFragment


class FragAdapter(private var characters: List<Result>,
                  val itemClickListener: RcViewFragment
) : RecyclerView.Adapter<FragAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                characters.get(adapterPosition).id.let { it -> itemClickListener.onItemClick(it) }
            }
        }
        val textView = itemView.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = characters[position].name
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}