package database.dao;

import com.j256.ormlite.support.ConnectionSource;

public class DatabaseDao extends CommonDao {
    public DatabaseDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }
}
