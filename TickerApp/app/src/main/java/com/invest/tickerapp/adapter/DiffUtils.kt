package com.invest.tickerapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.invest.tickerapp.model.data.Company

class DiffUtils(private val oldList: List<Company>, private val newList: List<Company>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].companyName == newList[newItemPosition].companyName

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition] && oldItemPosition.isEven() == newItemPosition.isEven()

}

fun Int.isEven() = this % 2