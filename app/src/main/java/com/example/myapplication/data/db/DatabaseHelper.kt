package com.example.myapplication.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.app.IMAppLication
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context = IMAppLication.instance) :
    ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NANME,true,ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NANME,true)
        onCreate(db)
    }

    companion object{
        val NAME = "im.db"
        val VERSION = 1
    }

}