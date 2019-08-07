package com.example.myapplication.extentions

fun String.isValiUserName() :Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))
fun String.isValipassword() :Boolean = this.matches(Regex("^[0-9]{3,20}$"))