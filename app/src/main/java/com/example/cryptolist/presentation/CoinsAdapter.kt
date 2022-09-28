package com.example.cryptolist.presentation

import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptolist.data.models.CoinsMarketModel
import com.example.cryptolist.databinding.CoinItemBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class CoinsAdapter(private val showCoinInfo: (String) -> Unit): RecyclerView.Adapter<CoinsAdapter.ProductsHolder>() {

    private val coinsList = ArrayList<CoinsMarketModel>()
    private var currency: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding = CoinItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)

    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(coinsList[position], showCoinInfo, currency.toString())
    }

    fun setList(coins: List<CoinsMarketModel>) {
        coinsList.clear()
        coinsList.addAll(coins)
    }

    fun getCurrency(currencyFromCoins: String){
        currency = currencyFromCoins
    }

    class ProductsHolder(private val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            coinsMarketModel: CoinsMarketModel,
            showCoinInfo: (String) -> Unit,
            currency: String
        ){

            //val number2digits:Double = String.format("%.2f", coinsMarketModel.price_change_percentage_24h).toDouble()
            val number2digits:Double = (coinsMarketModel.price_change_percentage_24h * 100.0).roundToInt().toDouble()/100
            binding.nameProduct.text = coinsMarketModel.name
            binding.shortName.text = coinsMarketModel.symbol.uppercase()
            binding.priceCoin.text = coinsMarketModel.current_price.toString()
            binding.currency.text = currency
            binding.percentCoin.text =  number2digits.toString()

            Picasso.get().load(coinsMarketModel.image).into(binding.imageProduct)

            binding.cardCategory.setOnClickListener(View.OnClickListener {
                showCoinInfo(coinsMarketModel.id)
            })

            if (coinsMarketModel.price_change_percentage_24h > 0){
                binding.percentCoin.setTextColor(Color.parseColor("#2A9D8F"))
                binding.percent.setTextColor(Color.parseColor("#2A9D8F"))
            }
            else {
                binding.percentCoin.setTextColor(Color.parseColor("#EB5757"))
                binding.percent.setTextColor(Color.parseColor("#EB5757"))
            }

        }
    }
}