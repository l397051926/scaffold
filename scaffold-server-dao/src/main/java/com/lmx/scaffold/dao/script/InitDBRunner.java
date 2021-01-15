package com.lmx.scaffold.dao.script;

/**
 * @author:
 * @description: 数据库初始化脚本
 * @date: 2020/9/28 10:35
 */
public class InitDBRunner {

    public static void main(String[] args) {
        DBInitDao dbInitDao = new DBInitDao();
        dbInitDao.initSchema();
    }

}
