package com.example.passwordManager

import java.io.Serializable

class Item(var Title: String, var UserName: String) : Serializable{

    var startingLetter = Title[0] - if (Title[0].code <=90) 0 else 32
}