package com.example.mykotlin.kotlinText

import kotlin.concurrent.thread

fun main(args: Array<String>) {
    KotlinMain().printString()
}

class KotlinMain {

    val task = {
        println("C")
        callback()
    }
    val callback = {
        println("D")
    }
    fun printString() {
        println("A")
        thread(block = task)
        println("B")
    }


}