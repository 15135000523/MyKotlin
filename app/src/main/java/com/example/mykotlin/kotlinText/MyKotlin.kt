package com.example.mykotlin.kotlinText

import android.util.Log
import com.example.mykotlin.MainBean
import com.example.mykotlin.goPackBefore

class MyKotlin {

    fun go(){
        sum(1,2);
        sum(1,2,3);
        vars(1,2,3,4,5);
        vars(1,2,3);
        ifCode();
        dollar(MainBean("闫文浩", "男", "乒乓", "随便吧"));
        forCode();
        /**
         * lambda表达式
         * var <标识符> : <类型> = <初始化值>可变量
         * val <标识符> : <类型> = <初始化值>不可变变量
         */
        val jian:(Int,Int,Int)-> Int ={x,y,z->x-y-z}
        val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
    }

    fun classCode(){
        var button = Button("你好");
        button.showoff()
    }

    /**
     *$ 表示一个变量名或者变量值
     *$varName 表示变量值
     *${varName.fun()} 表示变量的方法返回值:
     */
    fun dollar(bean: MainBean){
        var a = 1;
        var s1 = "a is $a"
        var b = "这个方法的值是${sum(1,4)}"
        /**
         * 内部函数
         */
        fun goPack(a:Int,b:String):Int{
            var c= a+b.hashCode()+ bean.aihao.hashCode();
            return c.hashCode();
        }
        bean.goPackBefore();
        goPack(a,s1);
    }

    fun sum(a:Int,b:Int) =a+b
    /**
     * public修饰的必须标明返回值
     * ?修饰表示返回值可以为空
     */
    public fun sum(a: Int, b: Int,c:Int): Int? = a+b+c

    /**
     * 函数的变长参数可以用 vararg 关键字进行标识：
     */
    fun vars(vararg v:Int){
        for(vt in v){
            print(vt)
        }
    }

    /**
     *使用 in 运算符来检测某个数字是否在指定区间内，区间格式为 x..y
     * when 相当于java中switch语句结尾用else代替,
     * when既可以当作表达式，也可以当作语句，语句需要加括号
     * 如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔
     * 也可以用in或者！in来检测是否在此区间
     * is用来判断是否当前类型
     * is String
     */
    fun ifCode(){
        var a= 1;
        var b = 2;
        var max = a;
        if(a<b)max=b;

        // 作为表达式
        val max1 = if (a > b) a else b

        val x = 5
        val y = 9
        if (x in 1..8) {
            Log.e("x 在区间内:","是的他在");
        }

        when(x){
            1->{
                Log.e("--1--","---1--")
                vars(1,2,3);
            }
            2->Log.e("--2--","---2--")
            else->{
                Log.e("--3--","---不是一也不是二--")
            }
        }
    }

    /**
     * 循环语句
     * continue跳过当次循环，进入下次循环，
     * break跳出循环提
     *
     */

     fun forCode(){
        var list:ArrayList<String> = ArrayList<String>();
        list.add("数据一")
        list.add("数据二")
        list.add("数据三")
        list.add("数据四")
        list.add("数据五")
        for(a in list){
            Log.e("forcode 打印数据","---标明类型的----$a")
        }
        for(a:String in list){
            Log.e("forcode 打印数据","-------$a")
        }
        var a = 1
        do {
            Log.e("do while 打印数据","-------$a")
            a++;
        }while(a<10);
    }
}
class Objf{
    lateinit var name:String
    get
    lateinit var sex:String
    lateinit var aihao :String
}