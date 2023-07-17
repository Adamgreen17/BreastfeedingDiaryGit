package com.example.breastfeedingdiary

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breastfeedingdiary.*
import kotlinx.android.synthetic.main.entry_item.view.*

class EntryAdapter (
    private val entries: MutableList<Entry>

) :RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {

        class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
                return EntryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.entry_item,parent, false))
        }

        fun addEntry (entry: Entry){
                entries.add(entry)
                notifyItemInserted(entries.size - 1)
        }

        fun deleteEntry() {
                entries.removeAll { entry ->
                        entry.isChecked

                }
                notifyDataSetChanged()
        }




        private fun toggleStrikeThrough(tvItemTitle: TextView, isChecked: Boolean)
        {
                if (isChecked){
                        tvItemTitle.paintFlags = tvItemTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
                } else {
                        tvItemTitle.paintFlags = tvItemTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
                }
        }

        override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
                val curEntry = entries[position]
                holder.itemView.apply {

                        tvItemTitle.text = curEntry.title
                        cb.isChecked = curEntry.isChecked
                        toggleStrikeThrough(tvItemTitle, curEntry.isChecked)
                        cb.setOnCheckedChangeListener { _, isChecked -> toggleStrikeThrough(tvItemTitle, isChecked)
                                curEntry.isChecked =!curEntry.isChecked
                        }

                }
        }

        override fun getItemCount(): Int {
                return entries.size
        }
}