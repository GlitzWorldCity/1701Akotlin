package com.example.myapplication.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.adapter.ContactListAdapter
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*

class ContactFragment :BaseFragment(){
    override fun getLayoutBaseId(): Int = R.layout.fragment_contacts
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter =ContactListAdapter(context)
        }

    }
}