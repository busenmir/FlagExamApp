package com.example.flagexamapp.Model

import android.annotation.SuppressLint
import com.example.flagexamapp.DatabaseAssistant

class FlagsDao {

    @SuppressLint("Range")
    fun randomFlag(vt : DatabaseAssistant) : ArrayList<Flags>{
        val flagList= ArrayList<Flags>()

        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5", null)

        while(cursor.moveToNext()){
            val flag = Flags(cursor.getInt(cursor.getColumnIndex("bayrak_id"))
                ,cursor.getString(cursor.getColumnIndex("bayrak_ad"))
                ,cursor.getString(cursor.getColumnIndex("bayrak_resim")))
            flagList.add(flag)
        }
        return flagList

    }
    @SuppressLint("Range")
    fun randomFlagFalse(vt : DatabaseAssistant, bayrak_id :Int) : ArrayList<Flags>{
        val flagList= ArrayList<Flags>()

        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id!= $bayrak_id ORDER BY RANDOM() LIMIT 3", null)

        while(cursor.moveToNext()){
            val flag = Flags(cursor.getInt(cursor.getColumnIndex("bayrak_id"))
                ,cursor.getString(cursor.getColumnIndex("bayrak_ad"))
                ,cursor.getString(cursor.getColumnIndex("bayrak_resim")))
            flagList.add(flag)
        }
        return flagList

    }
}