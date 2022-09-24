package com.example.cryptolist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptolist.data.models.CoinInfoModel
import com.example.cryptolist.databinding.CoinItemBinding

class CoinsAdapter: RecyclerView.Adapter<CoinsAdapter.ProductsHolder>() {

    private val coinsList = ArrayList<CoinInfoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding = CoinItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(coinsList[position])
    }

    fun setList(coins: List<CoinInfoModel>) {
        coinsList.clear()
        coinsList.addAll(coins)

    }

    class ProductsHolder(private val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            productModel: CoinInfoModel
        ){
            binding.nameProduct.text = productModel.categories.toString()

        }
    }
}