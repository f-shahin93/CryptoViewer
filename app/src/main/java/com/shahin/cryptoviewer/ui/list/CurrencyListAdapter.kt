package com.shahin.cryptoviewer.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahin.cryptoviewer.databinding.ItemCurrencyBinding
import com.shahin.cryptoviewer.datasource.model.Currency


class CurrencyListAdapter(val lifecycleOwner: LifecycleOwner, val listener: CurrencyItemClickListener) :
    ListAdapter<Currency, CurrencyListAdapter.CurrencyViewHolder>(CurrencyDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply { lifecycleOwner = this@CurrencyListAdapter.lifecycleOwner }, listener
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CurrencyViewHolder(val binding: ItemCurrencyBinding, listener: CurrencyItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private var itemVH: Currency? = null

        init {
            binding.root.setOnClickListener {
                itemVH?.let { item -> listener.onItemClicked(item.id) }
            }
        }

        fun bind(item: Currency) {
            itemVH = item
            binding.run {
                symbol = item.symbol
                name = item.name
                price = item.priceUsd
                rank = item.rank.toString()
            }
        }
    }

    interface CurrencyItemClickListener {
        fun onItemClicked(currencyId: String)
    }

}

class CurrencyDiffUtilCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
        oldItem == newItem

}