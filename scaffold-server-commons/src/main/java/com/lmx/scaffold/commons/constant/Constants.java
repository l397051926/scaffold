package com.lmx.scaffold.commons.constant;

import java.util.regex.Pattern;

/**
 * @author:
 * @description:
 * @date: 2020/8/20 14:26
 */
public class Constants {

    public static final String DATASOURCE_PROPERTIES = "/application-dao.yml";

    public static final String SLASH = "/";

    public static final String PERCENTAGE = "%";

    public static final String UNDERLINE = "_";

    /**
     * data source config
     */
    public static final String SPRING_DATASOURCE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";

    public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";

    public static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";

    public static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";

    public static final String SPRING_DATASOURCE_VALIDATION_QUERY_TIMEOUT = "spring.datasource.validationQueryTimeout";

    public static final String SPRING_DATASOURCE_INITIAL_SIZE = "spring.datasource.initialSize";

    public static final String SPRING_DATASOURCE_MIN_IDLE = "spring.datasource.minIdle";

    public static final String SPRING_DATASOURCE_MAX_ACTIVE = "spring.datasource.maxActive";

    public static final String SPRING_DATASOURCE_MAX_WAIT = "spring.datasource.maxWait";

    public static final String SPRING_DATASOURCE_TIME_BETWEEN_EVICTION_RUNS_MILLIS = "spring.datasource.timeBetweenEvictionRunsMillis";

    public static final String SPRING_DATASOURCE_TIME_BETWEEN_CONNECT_ERROR_MILLIS = "spring.datasource.timeBetweenConnectErrorMillis";

    public static final String SPRING_DATASOURCE_MIN_EVICTABLE_IDLE_TIME_MILLIS = "spring.datasource.minEvictableIdleTimeMillis";

    public static final String SPRING_DATASOURCE_VALIDATION_QUERY = "spring.datasource.validationQuery";

    public static final String SPRING_DATASOURCE_TEST_WHILE_IDLE = "spring.datasource.testWhileIdle";

    public static final String SPRING_DATASOURCE_TEST_ON_BORROW = "spring.datasource.testOnBorrow";

    public static final String SPRING_DATASOURCE_TEST_ON_RETURN = "spring.datasource.testOnReturn";

    public static final String SPRING_DATASOURCE_POOL_PREPARED_STATEMENTS = "spring.datasource.poolPreparedStatements";

    public static final String SPRING_DATASOURCE_DEFAULT_AUTO_COMMIT = "spring.datasource.defaultAutoCommit";

    public static final String SPRING_DATASOURCE_KEEP_ALIVE = "spring.datasource.keepAlive";

    public static final String SPRING_DATASOURCE_MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE = "spring.datasource.maxPoolPreparedStatementPerConnectionSize";


    /**
     * page size
     * 每页数据条数
     */
    public  static final String PAGE_SIZE = "pageSize";

    /**
     * current page no
     * 当前页码
     */
    public  static final String PAGE_NUMBER = "pageNo";


    /**
     * date format of `yyyy-MM-dd HH:mm:ss`
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * date format of yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * date format of yyyy-MM-dd
     */
    public static final String MM_DD = "MM.dd";


    /**
     * date format of yyyyMMddHHmmss
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * http connect time out
     */
    public static final int HTTP_CONNECT_TIMEOUT = 60 * 1000;


    /**
     * http connect request time out
     */
    public static final int HTTP_CONNECTION_REQUEST_TIMEOUT = 60 * 1000;

    /**
     * httpclient soceket time out
     */
    public static final int SOCKET_TIMEOUT = 60 * 1000;

    /**
     * http header
     */
    public static final String HTTP_HEADER_UNKNOWN = "unKnown";

    /**
     * http X-Forwarded-For
     */
    public static final String HTTP_X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * http X-Real-IP
     */
    public static final String HTTP_X_REAL_IP = "X-Real-IP";

    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * user name regex
     */
    public static final Pattern REGEX_USER_NAME = Pattern.compile("^[a-zA-Z0-9_-]{3,20}$");

    /**
     * email regex
     */
    public static final Pattern REGEX_MAIL_NAME = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    public static final String COMMA = ",";

    public static final String SESSION_USER = "session.user";
}
