package com.example.breastfeedingdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var entryAdapter: EntryAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        entryAdapter = EntryAdapter(mutableListOf())

        rvBreastfeedingDiaryItems.adapter = entryAdapter
        rvBreastfeedingDiaryItems.layoutManager = LinearLayoutManager(this)

        btnAddEntry.setOnClickListener {
            val entryTitle = etBFDTtitle.text.toString()
            if(entryTitle.isNotEmpty()) {
                val entry = Entry(entryTitle)
                entryAdapter.addEntry(entry)
                etBFDTtitle.text.clear()
            }
        }
        btnDelEntry.setOnClickListener{
            entryAdapter.deleteEntry()
        }


    }
}