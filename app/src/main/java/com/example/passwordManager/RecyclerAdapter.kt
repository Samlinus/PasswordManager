package com.example.passwordManager

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pass3.R

class RecyclerAdapter(private var itemList: ArrayList<Item>):
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    class MyViewHolder(itemView: View,listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        val icontextview: TextView = itemView.findViewById(R.id.alphabet_icon)
        val titletextview: TextView = itemView.findViewById(R.id.Title_item_textview)
        val userNametextview: TextView = itemView.findViewById(R.id.Username_item_textview)
        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,
            parent,false)
        return MyViewHolder(itemView,listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        // Changing the color of circle xml...
        val drawable = holder.icontextview.background as GradientDrawable
        drawable.setColor(Color.parseColor(getRandomColor()))
        holder.icontextview.text = currentItem.startingLetter.toString()
        holder.titletextview.text = currentItem.Title
        holder.userNametextview.text = currentItem.UserName

    }
    fun setFilteredList(filteredList: ArrayList<Item>){
        this.itemList = filteredList
        notifyDataSetChanged()
    }
    private fun getRandomColor(): String {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return String.format("#%02X%02X%02X", red, green, blue)
    }
}