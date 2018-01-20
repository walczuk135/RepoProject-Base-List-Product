package modelFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DatabaseFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty Product = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getProduct() {
        return Product.get();
    }

    public StringProperty productProperty() {
        return Product;
    }

    public void setProduct(String product) {
        this.Product.set(product);
    }
    public String toString() {
        return Product.getValue();
    }

}