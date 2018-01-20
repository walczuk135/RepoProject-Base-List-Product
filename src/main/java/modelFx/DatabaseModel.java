package modelFx;

import database.dao.DatabaseDao;
import database.dbutils.DbManager;
import database.model.Database;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import utils.convertes.ConverterDatabase;
import utils.exceptions.ApplicationException;

import java.util.List;

public class DatabaseModel {

    private ObservableList<DatabaseFx> productsList = FXCollections.observableArrayList();
    private ObjectProperty<DatabaseFx> products = new SimpleObjectProperty<>();
    private TreeItem<String> root=new TreeItem<>();

    public void init() throws ApplicationException {
        DatabaseDao databaseDao = new DatabaseDao(DbManager.getConnectionSource());//tworzenie
        List<Database> categories = databaseDao.queryForAll(Database.class);//pobieranie
        initProductList(categories);//inicjowanie
        initRoot(categories);
        DbManager.closeConnectionSource();

    }

    private void initRoot(List<Database> categories) {
        this.root.getChildren().clear();
        categories.forEach(c->{
            TreeItem<String> categoryItem=new TreeItem<>(c.getProduct());

            root.getChildren().add(categoryItem);//dodawanie itemow do rooota
        });
    }

    private void initProductList(List<Database> categories) {
        this.productsList.clear();
        categories.forEach(c -> {
            DatabaseFx databaseFx = ConverterDatabase.convertToCategory(c);
            this.productsList.add(databaseFx);
        });
    }

    public void deleteProductById() throws ApplicationException {
        DatabaseDao databaseDao = new DatabaseDao(DbManager.getConnectionSource());
        databaseDao.deleteById(Database.class,products.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public void saveProductIntDataBase(String product) throws ApplicationException {
        DatabaseDao databaseDao = new DatabaseDao(DbManager.getConnectionSource());
        Database database = new Database();
        database.setProduct(product);
        databaseDao.creatOrUpdate(database);
        DbManager.closeConnectionSource();
        init();//zaglada do bazy danych i ją aktualizuje
    }

    public void updateProductInDataBase() throws ApplicationException {
        DatabaseDao databaseDao = new DatabaseDao(DbManager.getConnectionSource());
        Database tempDatabase = databaseDao.findById(Database.class, getProducts().getId());
        tempDatabase.setProduct(getProducts().getProduct());
        databaseDao.creatOrUpdate(tempDatabase);
        DbManager.closeConnectionSource();
        init();
    }

    public ObservableList<DatabaseFx> getProductsList() {
        return productsList;
    }

    public void setProductsList(ObservableList<DatabaseFx> productsList) {
        this.productsList = productsList;
    }

    public DatabaseFx getProducts() {
        return products.get();
    }

    public ObjectProperty<DatabaseFx> productsProperty() {
        return products;
    }

    public void setProducts(DatabaseFx products) {
        this.products.set(products);
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }
}