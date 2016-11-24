package main.java.bntu.connection;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import main.java.bntu.dao.exception.ConnectionPoolException;

/**
 * Singleton connection pool implementation.
 * @author Veronika
 *
 */
public enum PoolConnection {
	INSTANCE;
	
	public static final int DEFAULT_CAPACITY = 5;
	private final Logger log = Logger.getLogger(getClass().getSimpleName());
	private static final int TIMEOUT = 2;
	
	private Lock lock;
	private BlockingQueue<DbConnection> connections;
	private int connectionsCreated;
	private int connPoolCapacity;
	private Properties connectionProps;
	
static{
	try{PoolConnection.INSTANCE.init(10);
	}
	catch(ConnectionPoolException e){
		e.printStackTrace();
	}
}	
	private PoolConnection() {
		connectionProps = new Properties();
		connectionProps.put("user", "root");
		connectionProps.put("password","root");
		connectionProps.put("url", "jdbc:mysql://localhost:3306/mydb");
	}
	
	/**
	 * Method init() should be called once before using getConnection method.
	 * 
	 * @param connectionNum - number of connections in connection pool
	 * @throws ConnectionPoolException
	 */
	public void init(int connectionNum) throws ConnectionPoolException {
		this.connections = new LinkedBlockingQueue<>(connectionNum);
		this.connectionsCreated = 0;
		this.connPoolCapacity = connectionNum;
		this.lock = new ReentrantLock(true);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new ConnectionPoolException("Driver for database was not found");
		}
	}
	
	/**
	 * Checks available connections. If all connections are busy, wait until any
	 * connection is released.
	 * 
	 * @return DBConnection - pooled connection to the database
	 * @throws ConnectionPoolException
	 */
	public DbConnection getConnection() throws ConnectionPoolException {
		try {
			if (connPoolCapacity > connectionsCreated) {
				this.lock.lock();
				try {
					//static!!!
					if ((connections.isEmpty()) && (connPoolCapacity > connectionsCreated)){
						Connection conn = DriverManager.getConnection(connectionProps.getProperty("url"), connectionProps);
						DbConnection dbConnection = new DbConnection(conn);
						++connectionsCreated;
						return dbConnection;
					}
				} finally {
					this.lock.unlock();
				}
			}
			DbConnection dbConnection = connections.take();
			if(dbConnection.isValid(TIMEOUT)) {
				return dbConnection;
			} else {
				Connection conn = DriverManager.getConnection(connectionProps.getProperty("url"), connectionProps);
				dbConnection = new DbConnection(conn);
				return dbConnection;
			}
		} catch (InterruptedException | SQLException e) {
			throw new ConnectionPoolException(e);
		}
	}
	
	void returnDbConnection(DbConnection connection) {
		try {
			this.connections.put(connection);
		} catch (InterruptedException e) {
			log.error("Thread was interrupted during connection releasing", e);
		}
	}
	
	/**
	 * Releases all resources acquired by the connection pool.
	 * Method should be called once when finish using connection pool.
	 * @throws ConnectionPoolException 
	 */
	public void releaseResources() throws ConnectionPoolException {
		try {
			for (DbConnection dbConnection : connections) {
				dbConnection.releaseConnection(TIMEOUT);
			} 
		} catch (SQLException e) {
			throw new ConnectionPoolException("Was not able to close connections", e);
		}
	}
}
