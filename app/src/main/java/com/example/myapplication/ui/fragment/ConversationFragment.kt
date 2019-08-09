package com.example.myapplication.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*

class ConversationFragment :BaseFragment(){
    override fun getLayoutBaseId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context)
        }
    }

}