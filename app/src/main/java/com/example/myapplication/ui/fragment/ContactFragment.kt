package com.example.myapplication.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.adapter.ContactListAdapter
import com.example.myapplication.adapter.EMContactListenerAdapter
import com.example.myapplication.contract.ContactContract
import com.example.myapplication.preasenter.ContactPresenter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient




class ContactFragment :BaseFragment(),ContactContract.View{

    override fun getLayoutBaseId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter =ContactListAdapter(context,presenter.contactListItems)
        }
        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter() {
            override fun onContactDeleted(p0: String?) {
                presenter.loadContacts()
            }
        })


        presenter.loadContacts()
    }
    override fun onLoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFaied() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(R.string.load_contacts_failed)
    }


}