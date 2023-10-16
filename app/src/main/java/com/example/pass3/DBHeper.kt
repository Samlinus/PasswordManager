package com.example.pass3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "PasswordManager"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "my_data"
        const val NAME = "userName"
        const val PASSWORD = "password"
        const val TITLE = "title"
        const val WEBSITE = "website"
        const val NOTE = "note"
        const val CATEGORY = "category"
        const val USER_TABLE = "user"
        const val USER_PASSWORD = "u_password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        println("onCreate()")
        db?.execSQL("create table if not exists $USER_TABLE ($USER_PASSWORD TEXT)")
        db?.execSQL("create table if not exists $TABLE_NAME ($TITLE TEXT, $NAME TEXT, $PASSWORD TEXT, $WEBSITE TEXT, $NOTE TEXT, $CATEGORY TEXT)")
    }

    fun insert(title: String, name: String, password: String, website: String, note: String, category: String): Long {
        val db = writableDatabase
        val value = ContentValues()
        value.put(TITLE, title)
        value.put(NAME, name)
        value.put(PASSWORD, password)
        value.put(WEBSITE, website)
        value.put(NOTE, note)
        value.put(CATEGORY, category)
        val s = db.insert(TABLE_NAME, null, value)
        db.close()
        return s
    }

    @SuppressLint("Range")
    fun retrieveData(): ArrayList<Item> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val itemList = ArrayList<Item>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                if (cursor.getColumnIndex(NAME) >=0 && cursor.getColumnIndex(PASSWORD)>=0) {

                    val title = cursor.getString(cursor.getColumnIndex(TITLE))
                    val name = cursor.getString(cursor.getColumnIndex(NAME))
                    itemList.add(Item(title,name))
                    cursor.moveToNext()
                }
            }
        }
        cursor.close()
        db.close()
        return itemList
    }
    fun isUserCorrect(password: String): Boolean {
        val db = readableDatabase
        val s = db.rawQuery("select count(*) from $USER_TABLE where $USER_PASSWORD='$password'", null)
        s.moveToFirst()
        val count = s.getInt(0)
        return count == 1
    }
    fun insert(password: String): Long {
        val db = writableDatabase
        val value = ContentValues()
        value.put(USER_PASSWORD, password)
        val s = db.insert(USER_TABLE, null, value)
        db.close()
        return s
    }
    @SuppressLint("Recycle")
    fun isFreshUser(): Boolean {
        val db = this.readableDatabase
        val s = db.rawQuery("select count(*) from $USER_TABLE", null)
        s.moveToFirst()
        val count = s.getInt(0)
        return count == 0
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("onUpgrade()")
    }
}