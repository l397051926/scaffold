
package com.lmx.scaffold.dao.script;

import com.alibaba.druid.pool.DruidDataSource;

import com.lmx.scaffold.commons.utils.ConnectionUtils;
import com.lmx.scaffold.commons.utils.ScriptRunner;
import com.lmx.scaffold.dao.AbstractBaseDao;
import com.lmx.scaffold.dao.datasource.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class DBInitDao extends AbstractBaseDao {

    public static final Logger logger = LoggerFactory.getLogger(DBInitDao.class);
    /**
     * sql文件 存储位置
     */
    private static final String initSqlPath = "/sql/init/release-1.0.0_schema/mysql/";
    /**
     *  初始化数据库时使用的mysql jdbc driver
     */
    private static final String mysqlDriver = "com.mysql.jdbc.Driver";
    /**
     * 判断是否需要初始化数据表 所依赖的表名
     */
    private static final String initTableEnable = "bi_dm_extract_ident";
    /**
     * dml sql 语句文件
     */
    private static final String dmlSql = "dashboard_dml.sql";
    /**
     * ddl sql 语句文件
     */
    private static final String ddlSql = "dashboard_ddl.sql";
    private static final String rootDir = System.getProperty("user.dir");
    protected static final DruidDataSource dataSource = getDataSource();

    @Override
    protected void init() {

    }

    /**
     * get datasource
     * @return DruidDataSource
     */
    public static DruidDataSource getDataSource(){
        ConnectionFactory instance = ConnectionFactory.getInstance();
        DruidDataSource druidDataSource = instance.dataSource();
        druidDataSource.setInitialSize(2);
        druidDataSource.setMinIdle(2);
        druidDataSource.setMaxActive(2);

        return druidDataSource;
    }



    /**
     * init schema
     */
    public void initSchema(){
        initSchema(initSqlPath);
    }


    /**
     * init scheam
     * @param initSqlPath initSqlPath
     */
    public void initSchema(String initSqlPath) {

        // 初始化数据库
        String dataBase = runInitDataBase();

        if (isExistsTable(initTableEnable, dataBase)){
            logger.info("表存在 不继续创建数据库了");
            return;
        }

        // Execute the mrst DDL, it cannot be rolled back
        runInitDDL(initSqlPath);

        // Execute the mrst DML, it can be rolled back
        runInitDML(initSqlPath);

    }

    /**
     * init data bases
     */
    private String runInitDataBase() {

        String url = dataSource.getUrl();
        String userName = dataSource.getUsername();
        String passWord = dataSource.getPassword();

        JdbcUrlSplitter splitter = new JdbcUrlSplitter(url);
        String database = splitter.database;
        String tmpUrl = splitter.url;

        Connection conn = null;
        try {
            Class.forName(mysqlDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            String databaseSql = "create database if not exists " + database;
            conn = DriverManager.getConnection(tmpUrl, userName, passWord);
            Statement smt = conn.createStatement();
            if (conn != null) {
                System.out.println("数据库连接成功！");
                smt.executeUpdate(databaseSql);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return database;
    }

    /**
     * run DML
     * @param initSqlPath initSqlPath
     */
    private void runInitDML(String initSqlPath) {
        Connection conn = null;
        if (StringUtils.isEmpty(rootDir)) {
            throw new RuntimeException("Environment variable user.dir not found");
        }
        String mysqlSQLFilePath = rootDir + initSqlPath + dmlSql;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            // Execute the dashboard_dml.sql script to import related data of mrst
            ScriptRunner initScriptRunner = new ScriptRunner(conn, false, true);
            Reader initSqlReader = new FileReader(new File(mysqlSQLFilePath));
            initScriptRunner.runScript(initSqlReader);

            conn.commit();
        } catch (IOException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage(),e1);
            }
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage(),e1);
            }
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            ConnectionUtils.releaseResource(null, null, conn);

        }

    }

    /**
     * run DDL
     * @param initSqlPath initSqlPath
     */
    private void runInitDDL(String initSqlPath) {
        Connection conn = null;
        if (StringUtils.isEmpty(rootDir)) {
            throw new RuntimeException("Environment variable user.dir not found");
        }
        //String mysqlSQLFilePath = rootDir + "/sql/create/release-1.0.0_schema/mysql/dashboard_ddl.sql";
        String mysqlSQLFilePath = rootDir + initSqlPath + ddlSql;
        try {
            conn = dataSource.getConnection();
            // Execute the dashboard_ddl.sql script to create the table structure of mrst
            ScriptRunner initScriptRunner = new ScriptRunner(conn, true, true);
            Reader initSqlReader = new FileReader(new File(mysqlSQLFilePath));
            initScriptRunner.runScript(initSqlReader);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            ConnectionUtils.releaseResource(null, null, conn);

        }

    }

    public boolean isExistsTable( String tableName, String dataBase) {
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            rs = conn.getMetaData().getTables(null, null, tableName, null);
            while (rs.next()) {
                if (rs.getString("TABLE_CAT").equals(dataBase)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            ConnectionUtils.releaseResource(rs, null, conn);
        }

    }

}
