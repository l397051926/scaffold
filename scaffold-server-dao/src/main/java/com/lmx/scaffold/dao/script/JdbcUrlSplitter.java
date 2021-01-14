package com.lmx.scaffold.dao.script;

/**
 * @author: lmx
 * @create: 2020/12/11
 **/
public class JdbcUrlSplitter {
    public String driverName, host, port, database, params, url;

    public JdbcUrlSplitter(String jdbcUrl) {
        int pos, pos1, pos2;
        String connUri;

        if (jdbcUrl == null || !jdbcUrl.startsWith("jdbc:")
                || (pos1 = jdbcUrl.indexOf(':', 5)) == -1) {
            throw new IllegalArgumentException("Invalid JDBC url.");
        }

        driverName = jdbcUrl.substring(5, pos1);
        if ((pos2 = jdbcUrl.indexOf(';', pos1)) == -1) {
            connUri = jdbcUrl.substring(pos1 + 1);
        } else {
            connUri = jdbcUrl.substring(pos1 + 1, pos2);
            params = jdbcUrl.substring(pos2 + 1);
        }

        if (connUri.startsWith("//")) {
            if ((pos = connUri.indexOf('/', 2)) != -1) {
                host = connUri.substring(2, pos);
                int tmp = connUri.indexOf("?");
                database = connUri.substring(pos + 1, tmp);
                params = connUri.substring(tmp + 1);

                if ((pos = host.indexOf(':')) != -1) {
                    port = host.substring(pos + 1);
                    host = host.substring(0, pos);
                }
            }
        } else {
            database = connUri;
        }
        url = "jdbc:mysql://" + host + ":" + port;
    }
}
