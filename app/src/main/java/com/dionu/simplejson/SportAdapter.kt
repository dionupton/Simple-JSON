package com.dionu.simplejson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sport_view.view.*

class SportAdapter(private val sportArray : ArrayList<Sport>) : RecyclerView.Adapter<SportAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sport_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = sportArray.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = sportArray[position].strSport
        Picasso.get().load(sportArray[position].strSportThumb).into(holder.imageView)

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val textView = itemView.textView_sportName
        val imageView = itemView.imageView
    }
}