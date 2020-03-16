package com.example.android.guesstheword.Utils

object StringUtils {

    @JvmStatic
    fun getString(num:Int):String{
        return "$num"
    }

    @JvmStatic
    fun getScoreString(num:Int):String{
        return "Score: ${getString(num)}"
    }
}