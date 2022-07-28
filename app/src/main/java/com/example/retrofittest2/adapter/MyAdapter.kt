package com.example.retrofittest2.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest2.databinding.RowLayoutBinding
import com.example.retrofittest2.model.Post

class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder((RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)))
    }

    override fun getItemCount() : Int{
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.userIdTxt.text = myList[position].userId.toString()
        holder.binding.idTxt.text = myList[position].id.toString()
        holder.binding.titleTxt.text = myList[position].title
        holder.binding.bodyTxt.text = myList[position].body
    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }


}