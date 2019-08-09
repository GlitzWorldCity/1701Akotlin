package com.example.myapplication.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.adapter.ContactListAdapter
import com.example.myapplication.adapter.EMContactListenerAdapter
import com.example.myapplication.contract.ContactContract
import com.example.myapplication.preasenter.ContactPresenter
import com.example.myapplication.ui.activity.AddFriendActivity
import com.example.myapplication.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.startActivity


class ContactFragment :BaseFragment(),ContactContract.View{

    override fun getLayoutBaseId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)

    val contractListener = object : EMContactListenerAdapter() {
        override fun onContactDeleted(p0: String?) {
            //获取联系人数据
            presenter.loadContacts()
        }

        override fun onContactAdded(p0: String?) {
            presenter.loadContacts()
        }
    }

    override fun init() {
        super.init()
        initHeader()
        initSwipeRefreshLayout()
        initRecyclerView()
        EMClient.getInstance().contactManager().setContactListener(contractListener)
        initSlideBar()
        presenter.loadContacts()
    }

    private fun initSlideBar() {
        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                recyclerView.smoothScrollToPosition(getPostion(firstLetter))
            }
            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }
    }

    private fun initHeader() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener { context?.startActivity<AddFriendActivity>() }
    }

    private fun getPostion(firstLetter: String): Int =
        presenter.contactListItems.binarySearch {
            contactListItem -> contactListItem.firstLetter.minus(firstLetter[0])
        }



    override fun onLoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFaied() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(R.string.load_contacts_failed)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(contractListener)
    }

}