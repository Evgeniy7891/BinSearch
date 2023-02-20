package ru.cft.binapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.cft.binapp.databinding.ItemHistoryBinding
import ru.cft.binapp.models.BinModel

class HistoryAdapter (private val list: List<BinModel>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BinModel) {
            with(binding) {
                tvBankResult.text = item.bin.toString()
                tvNetworkResult.text = item.scheme
                tvBrandResult.text = item.brand
                tvLengthResult.text = item.number.length.toString()
                tvLuhnResult.text = item.number.luhn.toString()
                tvTypeResult.text = item.type
                tvPrepaidResult.text = if (item.prepaid) "Yes" else "No"
                tvCountryResult.text = item.country.name
                tvBankResult.text = item.bank.name + ", " + item.bank.city
                tvWebResult.text = item.bank.url
                tvPhoneResult.text = item.bank.phone
            }
        }
    }
}