package com.sunilsahoo.assignment.myapplication.datasource;

import com.sunilsahoo.assignment.myapplication.features.todo.model.Score;

public class NetworkRepo {
     public static Score getScore(){
         return new Score();
     }
}
