package net.chineseguide.jukuu.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.ItemResultBinding
import net.chineseguide.jukuu.databinding.ItemResultBinding.bind
import net.chineseguide.jukuu.domain.entity.Sentence

class SentencesAdapter(private val onItemClick: (Sentence) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private val itemList = mutableListOf<Sentence>()

    fun setItemList(sentences: List<Sentence>) {
        itemList.clear()
        itemList.addAll(sentences)
        notifyDataSetChanged()
    }

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

class ViewHolder(itemView: View, private val onItemClick: (Sentence) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemResultBinding = bind(itemView)

    fun bind(item: Sentence) {
        binding.originalSentence.text = item.originalSentence
        binding.translatedSentence.text = item.translatedSentence
        binding.itemRoot.setOnClickListener { onItemClick(item) }
    }
}