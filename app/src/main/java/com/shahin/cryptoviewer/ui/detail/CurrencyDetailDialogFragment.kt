package com.shahin.cryptoviewer.ui.detail

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shahin.cryptoviewer.MainActivity
import com.shahin.cryptoviewer.R
import com.shahin.cryptoviewer.databinding.FragmentCurrencyDetailDialogBinding
import com.shahin.cryptoviewer.di.ViewModelFactory
import com.shahin.cryptoviewer.ui.list.CurrencyListViewModel
import com.shahin.cryptoviewer.ui.utils.getImageUrl
import javax.inject.Inject


class CurrencyDetailDialogFragment : DialogFragment() {

    private val args: CurrencyDetailDialogFragmentArgs by navArgs()
    private lateinit var binding: FragmentCurrencyDetailDialogBinding
    private lateinit var dialog: AlertDialog

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: CurrencyListViewModel by lazy {
        ViewModelProvider(
            requireParentFragment(),
            viewModelFactory
        )[CurrencyListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_currency_detail_dialog,
            null,
            false
        )

        dialog = MaterialAlertDialogBuilder(requireContext()).create()
        dialog.apply {
            setView(binding.root)
            setCancelable(true)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.attributes?.gravity = Gravity.BOTTOM
            window?.setLayout(
                requireActivity().resources.displayMetrics.widthPixels,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currency = viewModel.currencySuccess.value?.find { it.id == args.currencyId }
        currency?.let {
            binding.apply {
                currencyId = currency.id
                name = currency.name
                price = currency.priceUsd
                rank = currency.rank.toString()
                marketCap = currency.marketCapUsd
                volume24h = currency.volume24.toString()
                percent24h = currency.percentChange24h.toString()
                percent1h = currency.percentChange1h.toString()
                percent7d = currency.percentChange7d.toString()
            }
        }
    }

}