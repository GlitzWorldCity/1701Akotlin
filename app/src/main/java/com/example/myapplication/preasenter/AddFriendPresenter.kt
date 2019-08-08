package com.example.myapplication.preasenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.myapplication.contract.AddFriendContract
import com.example.myapplication.data.AddFriendItem
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

class AddFriendPresenter(val view: AddFriendContract.View) :AddFriendContract.Presenter{

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object :FindListener<BmobUser>(){
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1==null){
                    //处理数据


                    doAsync {
                        p0?.forEach {
                            val addFriendItem = AddFriendItem(it.username,it.createdAt)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread { view.onSearchSuccess() }
                    }
                }
                else view.onSearchFailed()
            }
        })
    }

}