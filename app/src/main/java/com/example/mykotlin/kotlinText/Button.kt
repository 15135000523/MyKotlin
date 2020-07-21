package com.example.mykotlin.kotlinText

/**
 * open表示开放类，子类可以继承
 * sealed将类修饰为密封类，不能在类外部拥有子类
 */
open class Button :Clickable,Focusable {
    lateinit var text:String
    lateinit var name:String

   constructor(text:String){
        this.text = text
        show(false);
    }
    constructor(text:String,nane:String):this(text){
        this.name = nane;
        click();
    }

    constructor()

    override fun show(b: Boolean) {
    }

    override fun click() {
    }

    override fun showoff(){

    }

    /**
     * open修饰的表示类可以继承，
     * open类中的override方法可以在子类中实现，在open加final表示子类方法不能被重写
     * open类中，没有使用open修饰的方法，在子类中无法重写
     */
    fun showText(){

    }

    open fun showText(res:String){

    }

    inner class Buttond{
        fun getButtonFuncation(){
            var ds:DaggerText = DaggerText();
        }
    }



}