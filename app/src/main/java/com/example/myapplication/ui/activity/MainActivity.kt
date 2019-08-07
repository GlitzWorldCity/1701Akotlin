package com.example.myapplication.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main
    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { taskId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(taskId)!!)
            beginTransaction.commit()
        }
    }
}
