package net.chineseguide.jukuu.ui.util

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<T>(private val compare: (T, T) -> Boolean) : DiffUtil.Callback() {

    private val oldList: MutableList<T> = mutableListOf()
    private val newList: MutableList<T> = mutableListOf()

    fun getDiffResult(newList: List<T>, detectMoves: Boolean = true): DiffUtil.DiffResult {
        oldList.clear()
        oldList.addAll(this.newList)
        this.newList.clear()
        this.newList.addAll(newList)
        return DiffUtil.calculateDiff(this, detectMoves)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        compare(oldList[oldItemPosition], newList[newItemPosition])

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}