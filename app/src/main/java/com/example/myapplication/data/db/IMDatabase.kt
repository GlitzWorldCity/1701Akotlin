package com.example.myapplication.data.db

import com.example.myapplication.extentions.toVararArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class IMDatabase {
    companion object{
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }
    fun saveContact(contact: Contact){
        databaseHelper.use {
            insert(ContactTable.NANME,*contact.map.toVararArray())
        }
    }
    fun getAllCintacts():List<Contact> = databaseHelper.use {
        select(ContactTable.NANME).parseList(object :MapRowParser<Contact>{
            override fun parseRow(columns: Map<String, Any?>): Contact {
                return Contact(columns.toMutableMap())
            }
        })
    }
    fun deleAllContact(){
        databaseHelper.use {
            delete(ContactTable.NANME, null,null)
        }
    }

}