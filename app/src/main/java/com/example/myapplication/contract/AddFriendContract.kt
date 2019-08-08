package com.example.myapplication.contract

import com.example.myapplication.BasePresenter

interface AddFriendContract {
    interface Presenter :BasePresenter{
        fun search(key:String)
    }
    interface View{
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}