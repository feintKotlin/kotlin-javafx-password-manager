package com.feint.fx.data

import java.sql.PreparedStatement
import java.sql.ResultSet

interface SQLHelper {
    fun select():Any;
    fun <T>insert(obj:T):Boolean;
    fun delete(whereArg:String):Boolean;
}