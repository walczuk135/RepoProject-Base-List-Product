package database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import database.model.Database;


import java.io.IOException;
import java.sql.SQLException;

public class DbManager {
    private static final Logger LOGGER= LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_WD="jdbc:h2:./listDB";
    private static final String USER="admin";
    private static final String PASS="admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionSource();
        dropTable(); //zeby nie usuwało za każdym razem zakomentować
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource(){
        try{
            connectionSource=new JdbcConnectionSource(JDBC_DRIVER_WD,USER,PASS);
        }catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource==null){
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource(){
        if(connectionSource!=null){
            try{
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Database.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource,Database.class,true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
