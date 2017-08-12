package com.feint.fx.utility

import com.feint.fx.data.SQLHelper
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

object DbUtils{

    lateinit var conn:Connection;

    fun getConnection(dbName:String):Connection{
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:${dbName}");
        }catch (e:Exception){
            e.printStackTrace();
        }finally {
            return conn;
        }
    }
}

fun ResultSet.forEach(deal:(ResultSet)->Unit){
    while (this.next()){
        deal(this);
    }
}
