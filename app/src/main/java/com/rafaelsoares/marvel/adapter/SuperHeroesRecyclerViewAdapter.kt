package com.rafaelsoares.marvel.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.balysv.materialripple.MaterialRippleLayout
import com.rafaelsoares.marvel.R
import com.rafaelsoares.marvel.model.Superhero
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class SuperHeroesRecyclerViewAdapter(
    private val mContext: Context,
    items: MutableList<Superhero>
) :
    RecyclerView.Adapter<SuperHeroesRecyclerViewAdapter.ViewHolder>() {

    private var mData: MutableList<Superhero>? = null

    private var tapListener: ObjectTapListener? = null

    fun setObjectTapListener(listener: ObjectTapListener) {
        tapListener = listener
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.characterImage)
        val name: TextView = view.findViewById(R.id.characterName)
        val clickAction: MaterialRippleLayout = view.findViewById(R.id.characterCell)
    }

    init {
        mData = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(
            R.layout.inflate_superheroes_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItemAt(position)
        val path = item.thumbnail.path+"/standard_medium.jpg"
        Picasso.get().load(path)
            .resize(300,300)
            .into(holder.image)
        holder.name.text = item.name
        holder.clickAction.setOnClickListener {
            tapListener!!.onItemSelection(position)
        }
    }

    private fun getItemAt(position: Int): Superhero = mData!![position]

    override fun getItemCount(): Int {
        return mData!!.size
    }

    interface ObjectTapListener {
        fun onItemSelection(position: Int)
    }
}