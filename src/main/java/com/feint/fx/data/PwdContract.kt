package com.feint.fx.data

import java.sql.Statement

class PwdContract{
    companion object PwdEntity{
        public val TABLE_NAME="pwd";
        public val COLUMN_ID="id";
        public val COLUMN_PWD="pwd";
        public val COLUMN_TOPIC="topic"

        fun createTable(stmt:Statement){
            val sql="CREATE TABLE IF NOT EXISTS $TABLE_NAME" +
                    "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_PWD VARCHAR(25) NOT NULL," +
                    "$COLUMN_TOPIC VARCHAR(64) NOT NULL);";

            stmt.executeUpdate(sql);
            stmt.close();
        }
    }
}