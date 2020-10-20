package com.example.ezparkjava

import android.content.Context
import android.nfc.Tag
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.ezparkjava.model.Map


private const val TAG = "MapsAdapter"
class MapsAdapter(val context: Context, val Map: List<Map>, val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface OnClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = Map.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val Map = Map[position]
        holder.itemView.setOnClickListener {
            Log.i(TAG, "Tapped on position $position")
            onClickListener.onItemClick(position)
        }
        val textViewTitle = holder.itemView.findViewById<TextView>(android.R.id.text1)
        textViewTitle.text = Map.title
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
