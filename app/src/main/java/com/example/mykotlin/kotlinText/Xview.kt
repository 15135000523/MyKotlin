package com.example.mykotlin.kotlinText

class Xview(time:String,author:String) : Button() {
    lateinit var time:String
    lateinit var author:String


    init {
        this.time=time;
        this.author = author
        showText(this.time);
    }
    override fun showText(res: String) {
        super.showText(res)
    }
}