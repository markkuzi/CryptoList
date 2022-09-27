package com.example.cryptolist.presentation

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.example.cryptolist.R
import com.example.cryptolist.databinding.CoinInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinInfo : Fragment() {

    private var binding: CoinInfoBinding? = null
    private val coinInfoViewModel: CoinInfoViewModel by viewModel()
    private var getPreferences: SharedPreferences? = null
    private var nameCoin: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CoinInfoBinding.inflate(inflater, container, false)

        getPreferences = (context as Activity).getSharedPreferences(
            "SaveData",
            Context.MODE_PRIVATE
        )

        nameCoin = getPreferences?.getString("name", "SaveData")

        loadCoinInfo()

        binding?.btnBack?.setOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })

        progressBarStatus()
        errStatus(nameCoin.toString())

        return binding?.root
    }

    private fun loadCoinInfo(){

        coinInfoViewModel.getCoinInfo((context as Activity),nameCoin.toString().lowercase())
        coinInfoViewModel.loadCoinInfo.observe(viewLifecycleOwner, Observer {
            binding?.coinName?.text = it[0].name
            binding?.descriptionCoin?.text = Html.fromHtml(it[0].description.en)
            binding?.categoriesCoin?.text = it[0].categories.toString().replace("[", "").replace("]","")
            Picasso.get().load(it[0].image.large).into(binding?.imageCoin)
        })

    }

    private fun progressBarStatus(){
        coinInfoViewModel.progressBarStatus.observe(viewLifecycleOwner, Observer {
            if (it){
                binding?.progressBar?.show()
                binding?.scrollView?.visibility = View.GONE
            }
            else
                binding?.progressBar?.hide()
            binding?.scrollView?.visibility = View.VISIBLE
        })
    }

    private fun errStatus(id:String){

        binding?.btnErr?.setOnClickListener(View.OnClickListener {
            coinInfoViewModel.getCoinInfo((context as Activity),id)
        })

        coinInfoViewModel.errorStatus.observe(viewLifecycleOwner, Observer {
            if (it){
                binding?.btnErr?.visibility = View.VISIBLE
                binding?.imageErr?.visibility = View.VISIBLE
                binding?.textErr?.visibility = View.VISIBLE
                binding?.scrollView?.visibility = View.GONE
            }
            else{
                binding?.btnErr?.visibility = View.GONE
                binding?.imageErr?.visibility = View.GONE
                binding?.textErr?.visibility = View.GONE
                binding?.scrollView?.visibility = View.GONE
            }
        })
    }

}