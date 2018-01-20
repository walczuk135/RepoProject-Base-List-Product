package database.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "base")
public class Database implements BaseModel{

    public Database() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "List",canBeNull = false,unique = true)
    private String product;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
