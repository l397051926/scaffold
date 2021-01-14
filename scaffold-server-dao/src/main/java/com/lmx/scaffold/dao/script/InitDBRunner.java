package com.lmx.scaffold.dao.script;

import java.sql.SQLException;

/**
 * @author:
 * @description: 数据库初始化脚本
 * @date: 2020/9/28 10:35
 */
public class InitDBRunner {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBInitDao dbInitDao = new DBInitDao();
        dbInitDao.initSchema();
    }

}
