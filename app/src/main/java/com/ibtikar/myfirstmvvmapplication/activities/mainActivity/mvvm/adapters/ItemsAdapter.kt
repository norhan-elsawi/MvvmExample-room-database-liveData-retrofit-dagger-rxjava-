package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ibtikar.myfirstmvvmapplication.BR
import com.ibtikar.myfirstmvvmapplication.R
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item


class ItemsAdapter(var list: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var inflater = LayoutInflater.from(parent?.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Holder).bindData(list[position])
    }

    class Holder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Item) {
            binding.setVariable(BR.item, item)
        }
    }
}
