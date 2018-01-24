package com.keng.common.jdbc;

import com.keng.common.util.StringUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class JDBCUtil {

    public static void useFetchSize(DataSource dataSource, String sql, Consumer<ResultSet> consumer) {
        if (dataSource != null && consumer != null && StringUtil.isNotEmpty(sql)) {
            Connection connection = null;
            PreparedStatement pstmt = null;
            try {
                connection = dataSource.getConnection();
                pstmt = connection.prepareStatement(sql,
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);

                pstmt.setFetchSize(Integer.MIN_VALUE);

                pstmt.setFetchDirection(ResultSet.FETCH_REVERSE);
                consumer.accept(pstmt.executeQuery());

            } catch (SQLException e) {

            } finally {
                closeQuiet(connection, pstmt);
            }
        }
    }

    public static void useConnection(DataSource dataSource, Consumer<Connection> consumer) {
        useConnection(dataSource, consumer, false);
    }

    public static void useConnection(DataSource dataSource, Consumer<Connection> consumer, boolean isTransaction) {
        if (dataSource != null && consumer != null) {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                if (isTransaction) {
                    connection.setAutoCommit(false);
                }
                consumer.accept(connection);
                if (isTransaction) {
                    connection.commit();
                }
            } catch (SQLException e) {
                if (isTransaction) {
                    rollback(connection);
                }
            }finally {
                closeQuiet(connection);
            }
        }
    }

    public static <R> R useConnection(DataSource dataSource, Function<Connection, R> function) {
        return  useConnection(dataSource, function, false);
    }

    public static <R> R useConnection(DataSource dataSource, Function<Connection, R> function, boolean isTransaction) {
        if (dataSource != null && function != null) {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                if (isTransaction) {
                    connection.setAutoCommit(false);
                }
                R apply = function.apply(connection);
                if (isTransaction) {
                    connection.commit();
                }
                return apply;
            } catch (SQLException e) {
                if (isTransaction) {
                    rollback(connection);
                }
            }finally {

            }

        }
        return null;
    }



    public static void rollback(Connection connection) {
        rollback(connection, null);
    }

    public static void rollback(Connection connection, Savepoint savepoint) {
        if (connection != null) {
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                }else {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeQuiet(Connection conn) {
        closeQuiet(conn, null, null);
    }

    public static void closeQuiet(Statement stmt, ResultSet rs) {
        closeQuiet(null, stmt, rs);
    }

    public static void closeQuiet(Statement stmt) {
        closeQuiet(stmt, null);
    }

    public static void closeQuiet(ResultSet rs) {
        closeQuiet(null, null, rs);
    }

    public static void closeQuiet(Connection conn, Statement stmt) {
        closeQuiet(conn, stmt, null);
    }

    public static void closeQuiet(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                if (!conn.getAutoCommit()) {
                    conn.setAutoCommit(true);
                }
            } catch (Throwable var3) {

            }

            try {
                conn.close();
            } catch (Throwable var2) {

            }
        }
    }
}
