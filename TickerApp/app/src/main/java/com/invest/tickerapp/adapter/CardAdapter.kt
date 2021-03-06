package com.invest.tickerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.invest.tickerapp.IClickCompany
import com.invest.tickerapp.databinding.CardCaptionedItemBinding
import com.invest.tickerapp.model.Company

class CardAdapter(var listData: List<Company>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    private var onClickAction: IClickCompany? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding =
            CardCaptionedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    fun initClickListener(onClickAction: IClickCompany?) {
        this.onClickAction = onClickAction
    }

    inner class CardViewHolder(private val view: CardCaptionedItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(company: Company) = with(view) {
            star.setOnCheckedChangeListener { _, _ -> }
            star.isChecked = company.star
            star.setOnCheckedChangeListener { _, isChecked ->
                company.star = isChecked
                if (isChecked) {
                    onClickAction?.onCheck(company)
                } else {
                    onClickAction?.onUncheck(company)
                }
            }
            val colorRes = if (position % 2 == 0)
                android.R.color.white
            else
                android.R.color.holo_blue_light
            cardView.setCardBackgroundColor(ContextCompat.getColor(root.context, colorRes))
            companyText.text = company.companyName
            tickerText.text = company.companyTicker
            costText.text = company.cost
            deltaCostText.text = company.deltaCost
            //TODO search Glide
            logoIcon.setImageDrawable(
                ContextCompat.getDrawable(logoIcon.context, company.logoIdImage)
            )
        }
    }

}