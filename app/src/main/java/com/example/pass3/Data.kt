package com.example.pass3

import java.io.Serializable

class Data(t: String, n: String,p: String, w:String, note: String, c: String) : Serializable{
    var name = n
    var password = p
    var title = t
    var website = w
    var note = note
    var category = c
}