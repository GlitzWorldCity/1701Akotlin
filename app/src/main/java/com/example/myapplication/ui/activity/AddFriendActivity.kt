package com.example.myapplication.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.AddFriendListAdapter
import com.example.myapplication.contract.AddFriendContract
import com.example.myapplication.preasenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity :BaseActivity() ,AddFriendContract.View{

    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    val presenter = AddFriendPresenter(this)

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.addFriendItems)
        }

        search.setOnClickListener { search() }
        userName.setOnEditorActionListener { v, actionId, event ->
            search()
            true
        }
    }
    fun search(){
        hideSoftKeyBoard()
        showProgress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchFailed() {
        dissmissProgress()
        toast(R.string.search_failed)
    }

    override fun onSearchSuccess() {
        dissmissProgress()
        toast(R.string.search_success)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}


