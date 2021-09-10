package com.app_devs.myapplication.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.myapplication.R
import com.app_devs.myapplication.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var userList= emptyList<User>()
    class MyViewHolder(view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){
            val model=userList[position]
            holder.itemView.tv_id.text=model.id.toString()
            holder.itemView.tv_age.text=model.age.toString()
            holder.itemView.tv_name.text=model.name
            holder.itemView.tv_status.text=model.vaccinationStatus
            holder.itemView.row_layout.setOnClickListener{
                val action=ListFragmentDirections.actionListFragmentToUpdateFragment(model)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }
    fun setData(user:List<User>)
    {
        this.userList=user
        notifyDataSetChanged()
    }
}