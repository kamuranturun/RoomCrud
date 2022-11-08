package com.kamuran.room.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.kamuran.room.R
import com.kamuran.room.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var userList= emptyList<User>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_text.text= currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.lastName_text.text= currentItem.lastName
        holder.itemView.age_txt.text= currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
         /*
         val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
          */
        }
    }

    fun setData(user:List<User>){
        this.userList= user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}