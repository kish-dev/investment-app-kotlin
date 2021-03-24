package com.invest.tickerapp.view

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.invest.tickerapp.IClickCompany
import com.invest.tickerapp.adapter.CardAdapter
import com.invest.tickerapp.adapter.DiffUtils
import com.invest.tickerapp.model.data.Company


open class UpdateListFragment : Fragment() {

    open var adapter: CardAdapter? = null

    var onClickAction: IClickCompany? = null

    fun initRecycler(recyclerView: RecyclerView) {
        if (adapter == null) {
            adapter = CardAdapter(mutableListOf())
        }
        recyclerView.adapter = adapter
        adapter!!.initClickListener(onClickAction)
    }

    fun update(list: List<Company>) {
        if (adapter == null) {
            adapter = CardAdapter(list)
        }
        val diff = DiffUtil.calculateDiff(DiffUtils(adapter!!.listData, list))
        adapter!!.listData = list
        diff.dispatchUpdatesTo(adapter!!)
    }
}