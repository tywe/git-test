package org.teng.java.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 数据库管理工具类（读写分离，读的后缀为_R，写的后缀为_W）
 * 
 * @author libin
 * @date 2014-1-26
 */
public class DbRwSplittingTool {
	private static Logger logger = Logger.getLogger(DbRwSplittingTool.class);
	private static String SUFFIX_READ = "_R";
	private static String SUFFIX_WRITE = "_W";

	protected String dbJndiPrefix = null;

	protected DbRwSplittingTool(String dbJndiPrefix) {
		if (StringHandler.isNullorEmpty(dbJndiPrefix)) {
			logger.error("数据库的JNDI不能为空！");
			return;
		}

		this.dbJndiPrefix = dbJndiPrefix;
	}

	public Connection getConn(String suffix) {
		if (StringHandler.isNullorEmpty(suffix)) {
			return null;
		}

		Connection conn = null;
		try {
			DataSource source = (DataSource) new InitialContext().lookup("java:comp/env/"
					+ this.dbJndiPrefix + suffix);
			conn = source.getConnection();
		} catch (Exception e) {
			logger.error("初始化数据库连接时出错：" + e.getMessage(), e);
		}

		return conn;
	}

	@SuppressWarnings("rawtypes")
	public List<Object> executeSQL(String sql, Class clazz, String... parameters) {
		List<Object> list = new ArrayList<Object>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn(SUFFIX_READ);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.length; i++) {
					psmt.setString(i + 1, parameters[i]);
				}
				logger.debug("sql=" + sql);
				rs = psmt.executeQuery();
				list = resultSet2Object(rs, clazz);
			} catch (InstantiationException e) {
				logger.error("InstantiationException", e);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	public List<Map<String, Object>> executeSQL(String sql, String... parameters) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn(SUFFIX_READ);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.length; i++) {
					psmt.setString(i + 1, parameters[i]);
				}
				logger.debug("sql=" + sql);
				rs = psmt.executeQuery();
				list = resultSet2List(rs);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	public int executeUpdate(String sql, String... parameters) {
		PreparedStatement psmt = null;
		int rs = 0;
		Connection conn = getConn(SUFFIX_WRITE);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.length; i++) {
					psmt.setString(i + 1, parameters[i]);
				}
				logger.debug("sql=" + sql);
				rs = psmt.executeUpdate();
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return rs;
	}

	/**
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> executeSQL(String sql, List<Object> parameters) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn(SUFFIX_READ);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.size(); i++) {
					Object obj = parameters.get(i);
					if (obj instanceof String) {
						psmt.setString(i + 1, (String) obj);
					} else {
						psmt.setInt(i + 1, (Integer) obj);
					}
					obj = null;
				}
				logger.debug("sql=" + sql);
				rs = psmt.executeQuery();
				list = resultSet2List(rs);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	public Map<String, Map<String, Object>> executeSQL(String sql, String keyColumn,
			List<String> parameters) throws SQLException {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn(SUFFIX_READ);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				if (parameters != null && !parameters.isEmpty()) {
					for (int i = 0; i < parameters.size(); i++) {
						psmt.setString(i + 1, parameters.get(i));
					}
				}
				logger.debug("---------executeSQL=" + sql);
				rs = psmt.executeQuery();
				map = ResultSet2Map(rs, keyColumn);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return map;
	}

	/**
	 * @author Liangjiameng
	 * @param sql
	 *            参数化SQL语句
	 * @param clazz
	 *            对象
	 * @param parameters
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> executeSQL(String sql, Class clazz, List<String> parameters) {
		List<Object> list = new ArrayList<Object>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn(SUFFIX_READ);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.size(); i++) {
					psmt.setString(i + 1, parameters.get(i));
				}
				logger.debug("---------executeSQL=" + sql);
				rs = psmt.executeQuery();
				list = resultSet2Object(rs, clazz);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} catch (InstantiationException e) {
				logger.error("InstantiationException", e);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	/**
	 * @author liangjiameng
	 * @param sql
	 *            参数化SQL语句
	 * @param parameters
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String sql, List<String> parameters) {
		logger.debug("________________________________________________________________");
		logger.debug("executeUpdate:" + sql);
		for (String dataItem : parameters) {
			logger.debug(dataItem);
		}
		PreparedStatement psmt = null;
		int rs = 0;
		Connection conn = getConn(SUFFIX_WRITE);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.size(); i++) {
					psmt.setString(i + 1, parameters.get(i));
				}
				rs = psmt.executeUpdate();
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return rs;
	}

	/**
	 * chau并返回上次插入的ID
	 */
	public int executeInsert(String sql, List<String> parameters) {
		logger.debug("________________________________________________________________");
		logger.debug("executeInsert:" + sql);
		for (String dataItem : parameters) {
			logger.debug(dataItem);
		}

		PreparedStatement psmt = null;
		int insertedId = 0;
		Connection conn = getConn(SUFFIX_WRITE);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < parameters.size(); i++) {
					psmt.setString(i + 1, parameters.get(i));
				}
				psmt.executeUpdate();
				ResultSet rs = psmt.getGeneratedKeys();
				if (rs != null && rs.next()) {
					insertedId = rs.getInt(1);
				}
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return insertedId;
	}

	/**
	 * 批量更新数据库
	 * 
	 * @param sql
	 *            SQL语句
	 * @param paramList
	 *            参数列表
	 * @return
	 */
	public boolean executeBatchUpdate(String sql, List<List<String>> paramList) {
		logger.debug("\nsql:" + sql);
		boolean isSuccess = false;

		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = getConn(SUFFIX_WRITE);
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			psmt = conn.prepareStatement(sql);

			List<String> params = null;
			for (int i = 0; i < paramList.size(); i++) {
				params = paramList.get(i);

				for (int j = 0; j < params.size(); j++) {
					psmt.setString(j + 1, params.get(j));
				}

				psmt.addBatch();

				if (i % 1000 == 0) {
					if (psmt.executeBatch().length > 0) {
						isSuccess = true;
					}
				}
			}

			if (psmt.executeBatch().length > 0) {
				isSuccess = true;
			}

			conn.commit();
			conn.setAutoCommit(autoCommit);

			return isSuccess;
		} catch (SQLException e) {
			logger.error("executeBatchUpdate", e);
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					logger.error("SQLException", e);
				}
				psmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("SQLException", e);
				}
				conn = null;
			}
		}

		return false;
	}

	/**
	 * @author liangjiameng
	 * @param sql
	 *            参数化的Sql语句
	 * @param parameters
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @return
	 */
	public boolean queryExist(String sql, List<String> parameters) {
		boolean isExist = false;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn(SUFFIX_READ);
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.size(); i++) {
					psmt.setString(i + 1, parameters.get(i));
				}
				rs = psmt.executeQuery();
				isExist = rs.last();
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return isExist;
	}

	/*
	 * 将rs结果转换成对象列表
	 * 
	 * @param rs jdbc结果集
	 * 
	 * @param clazz 对象的映射类 return 封装了对象的结果列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> resultSet2Object(ResultSet rs, Class clazz) throws SQLException,
			InstantiationException, IllegalAccessException {
		// 结果集的元素对象
		// 返回结果的列表集合
		List<Object> list = new ArrayList<Object>();
		if (rs != null) {
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取结果集的元素个数
			int colCount = rsmd.getColumnCount();
			// 业务对象的属性数组
			Field[] fields = clazz.getDeclaredFields();
			while (rs.next()) {// 对每一条记录进行操作
				Object obj = clazz.newInstance();// 构造业务对象实体
				// 将每一个字段取出进行赋值
				for (int i = 1; i <= colCount; i++) {
					Object value = rs.getObject(i);

					// 处理Timestamp字段为String
					if (value instanceof Timestamp) {
						value = TimerTool.formatTimeToString((Timestamp) value);
					} else if (value instanceof Date) {
						value = TimerTool.formatDateToString((Date) value);
					}

					// 寻找该列对应的对象属性
					for (int j = 0; j < fields.length; j++) {
						Field f = fields[j];
						// 如果匹配进行赋值
						if (f.getName().equalsIgnoreCase(rsmd.getColumnLabel(i))) {
							boolean flag = f.isAccessible();
							f.setAccessible(true);
							f.set(obj, value);
							f.setAccessible(flag);
						}
					}
				}
				list.add(obj);
			}
		}
		return list;
	}

	public List<Map<String, Object>> resultSet2List(ResultSet rs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 封装resultSet数据 ,利用ResultSetMetaData对象可获得表的结构
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()) {// 循环表
				// 每行记录放到一个map里！
				Map<String, Object> map = new HashMap<String, Object>();// 每行记录放到一个
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
		return list;
	}

	public Map<String, Map<String, Object>> ResultSet2Map(ResultSet rs, String keyColumn)
			throws SQLException {
		Map<String, Map<String, Object>> rsmap = new HashMap<String, Map<String, Object>>();
		try {
			// 封装resultSet数据 ,利用ResultSetMetaData对象可获得表的结构
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			String mapkey = "";
			while (rs.next()) {// 循环表
				// 每行记录放到一个map里！
				Map<String, Object> map = new HashMap<String, Object>();// 每行记录放到一个
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					if (keyColumn.equals(rsmd.getColumnName(i))) {
						mapkey = StringHandler.nullToEmpty(rs.getObject(i));
					}
				}
				rsmap.put(mapkey, map);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
		return rsmap;
	}
}