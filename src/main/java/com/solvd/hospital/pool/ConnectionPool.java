package com.solvd.hospital.pool;

import com.solvd.hospital.util.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private static final int SIZE = 5;
    private static ConnectionPool instance;

    private Queue<Connection> connections = new LinkedList<>();

    private ConnectionPool() {
        try {
            for (int i = 0; i < SIZE; i++) {
                connections.add(
                        DriverManager.getConnection(
                                DBConfig.URL,
                                DBConfig.USER,
                                DBConfig.PASSWORD
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) instance = new ConnectionPool();
        return instance;
    }

    public synchronized Connection getConnection() {
        return connections.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.offer(connection);
    }
}