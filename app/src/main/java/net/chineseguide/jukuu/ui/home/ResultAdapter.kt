package net.chineseguide.jukuu.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.ItemResultBinding
import net.chineseguide.jukuu.databinding.ItemResultBinding.bind
import net.chineseguide.jukuu.domain.entity.Result

class ResultAdapter(private val onItemClick: (Result) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    var itemList: List<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_result, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int =
        itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}

class ViewHolder(itemView: View, private val onItemClick: (Result) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemResultBinding = bind(itemView)

    fun bind(item: Result) {
        binding.title.text = item.title
        binding.itemRoot.setOnClickListener { onItemClick(item) }
    }
}