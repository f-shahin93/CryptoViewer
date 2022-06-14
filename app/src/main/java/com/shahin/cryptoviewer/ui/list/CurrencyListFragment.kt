package com.shahin.cryptoviewer.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.shahin.cryptoviewer.MainActivity
import com.shahin.cryptoviewer.R
import com.shahin.cryptoviewer.databinding.FragmentCurrencyListBinding
import com.shahin.cryptoviewer.di.ViewModelFactory
import com.shahin.cryptoviewer.ui.base.BaseFragment
import com.shahin.cryptoviewer.ui.utils.marginItemDecoration
import com.shahin.cryptoviewer.ui.utils.snackBar
import javax.inject.Inject

class CurrencyListFragment : BaseFragment<FragmentCurrencyListBinding>(
    R.layout.fragment_currency_list
), CurrencyListAdapter.CurrencyItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by activityViewModels<CurrencyListViewModel> { viewModelFactory }

    private lateinit var adapter: CurrencyListAdapter

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupRecycler()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        setupSearchBox()

    }

    private fun setupSearchBox() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.search(query) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(newText) }
                return true
            }
        })
    }

    private fun setupObservers() {
        viewModel.mainList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            checkLoading(it.isNullOrEmpty())
        }
        viewModel.currencyLoading.observe(viewLifecycleOwner) {
            checkLoading(it)
        }
        viewModel.currencyError.observe(viewLifecycleOwner) {
            snackBar(binding.root, requireContext(), getString(R.string.error))
        }

    }

    private fun checkLoading(it: Boolean) {
        binding.loading.visibility = if (it && adapter.currentList.isNullOrEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setupAdapter() {
        adapter = CurrencyListAdapter(lifecycleOwner = viewLifecycleOwner, listener = this)
    }

    private fun setupRecycler() {
        binding.list.addItemDecoration(
            marginItemDecoration(
                context = requireContext(),
                marginTop = R.dimen.margin_small
            )
        )
        binding.list.adapter = this.adapter
    }

    private fun navigateToDetail(currencyId: Long) {
        findNavController().navigate(CurrencyListFragmentDirections.listToDetail(currencyId))
    }

    override fun onItemClicked(currencyId: Long) {
        navigateToDetail(currencyId)
    }

}