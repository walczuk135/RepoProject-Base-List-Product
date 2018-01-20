package utils.convertes;


import database.model.Database;
import modelFx.DatabaseFx;

public class ConverterDatabase {
    public static DatabaseFx convertToCategory(Database database){
        DatabaseFx databaseFx = new DatabaseFx();
        databaseFx.setId(database.getId());
        databaseFx.setProduct(database.getProduct());
        return databaseFx;
    }
}
