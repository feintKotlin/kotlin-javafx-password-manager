package com.feint.fx.data

import com.feint.fx.entity.Pwd
import com.feint.fx.utility.DbUtils
import com.feint.fx.utility.forEach
import java.sql.Connection
import java.util.*

class PwdDbHelper:SQLHelper{
    val DATABASE_NAME="pwd_db";
    var conn:Connection;
    constructor(){
        conn=DbUtils.getConnection(DATABASE_NAME);
        PwdContract.PwdEntity.createTable(conn.createStatement());
    }

    override fun select(): MutableList<Pwd> {
        val stmt=conn.createStatement()
        val resultSet=stmt.executeQuery("select *from ${PwdContract.PwdEntity.TABLE_NAME}");
        val list=ArrayList<Pwd>();

        resultSet.forEach {
            val pwd:Pwd= Pwd(it.getInt(PwdContract.PwdEntity.COLUMN_ID),
                    it.getString(PwdContract.PwdEntity.COLUMN_PWD),
                    it.getString(PwdContract.PwdEntity.COLUMN_TOPIC));
            list.add(pwd);
        }

        return list;
    }

    override fun <T> insert(obj: T): Boolean {
        if(obj is Pwd){
            val stmt=conn.prepareStatement("insert into ${PwdContract.PwdEntity.TABLE_NAME}" +
                    "(${PwdContract.PwdEntity.COLUMN_PWD},${PwdContract.PwdEntity.COLUMN_TOPIC}) values " +
                    "(?,?)");

            stmt.setString(1,obj.pwd);
            stmt.setString(2,obj.topic);
            val result=stmt.execute();
            stmt.close();
            return result;
        }
        return false;
    }

    override fun delete(whereArg: String): Boolean {
        return false;
    }
}