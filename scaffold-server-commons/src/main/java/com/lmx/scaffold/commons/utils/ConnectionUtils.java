
package com.lmx.scaffold.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ConnectionUtils {

	public static final Logger logger = LoggerFactory.getLogger(ConnectionUtils.class);

	private static ConnectionUtils instance;

	ConnectionUtils() {
	}

	public static ConnectionUtils getInstance() {
		if (null == instance) {
			syncInit();
		}
		return instance;
	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new ConnectionUtils();
		}
	}

	public void release(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					logger.error(e.getMessage(),e);
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void releaseResource(ResultSet rs, PreparedStatement ps, Connection conn) {
		ConnectionUtils.getInstance().release(rs,ps,conn);
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}
		}

		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}
		}

		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
}
