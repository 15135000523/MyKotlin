package com.example.mykotlin.view

import android.graphics.RectF

 class Point(var startAngle:Float,var sweepAngle:Float,var writeFectf: RectF){
  var x:Float
  get() =x;
  set(value) {
   x = value
  }
  var y:Float
   get() =y;
   set(value) {
    y = value
   }
 }