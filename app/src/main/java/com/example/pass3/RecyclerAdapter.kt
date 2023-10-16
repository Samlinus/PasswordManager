package com.example.pass3

import android.graphics.Color
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var itemList: ArrayList<Item>):
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private lateinit var Listener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        Listener = listener
    }

    class MyViewHolder(itemView: View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val icon_texview: TextView = itemView.findViewById(R.id.alphabet_icon)
        val title_textview: TextView = itemView.findViewById(R.id.Title_item_textview)
        val userName_textview: TextView = itemView.findViewById(R.id.Username_item_textview)
        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,
            parent,false)
        return MyViewHolder(itemView,Listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.icon_texview.setBackgroundColor(getRandomColor())
        holder.icon_texview.text = currentItem.StartingLetter.toString()
        holder.title_textview.text = currentItem.Title
        holder.userName_textview.text = currentItem.UserName

    }
    fun setFilteredList(filteredList: ArrayList<Item>){
        this.itemList = filteredList
        notifyDataSetChanged()
    }
    private fun getRandomColor(): Int {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.rgb(red,green,blue)
    }
}