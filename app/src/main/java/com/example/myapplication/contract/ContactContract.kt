package com.example.myapplication.contract

import com.example.myapplication.BasePresenter

interface ContactContract{
    interface Presenter :BasePresenter{
        fun loadContacts()
    }
    interface View{
        fun onLoadContactSuccess()
        fun onLoadContactFaied()
    }
}