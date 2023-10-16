package com.example.pass3

import java.io.Serializable

class Item(var Title: String, var UserName: String) : Serializable{

    var StartingLetter = Title[0] - if (Title[0].toInt()<=90) 0 else 32
}