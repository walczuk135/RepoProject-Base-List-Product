package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import modelFx.DatabaseFx;
import modelFx.DatabaseModel;
import utils.DialogsUtils;
import utils.exceptions.ApplicationException;


public class DatabaseController {

    @FXML
    public Button addBaseButton;
    @FXML
    public Button deleteBaseButton;
    @FXML
    public Button editBaseButton;
    @FXML
    public TextField ProductTextField;
    @FXML
    public ComboBox<DatabaseFx> baseComboBox;
    @FXML
    private TreeView<String> baseTreeView;

    private DatabaseModel databaseModel;

    @FXML
    public void initialize() {
        this.databaseModel =new DatabaseModel();
        try {
            this.databaseModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage() );
        }
        this.baseComboBox.setItems(this.databaseModel.getProductsList());
        this.baseTreeView.setRoot(this.databaseModel.getRoot() );
        initBindings();

    }

    private void initBindings() {
        this.addBaseButton.disableProperty().bind(ProductTextField.textProperty().isEmpty());
        this.deleteBaseButton.disableProperty().bind(this.databaseModel.productsProperty().isNull());
        this.editBaseButton.disableProperty().bind(this.databaseModel.productsProperty().isNull());//blokowanie przycisków
    }

    public void addBaseOnAction() {

        try {
            databaseModel.saveProductIntDataBase(ProductTextField.getText());
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        ProductTextField.clear();
    }

    public void onActionDeleteButton() throws ApplicationException {
        this.databaseModel.deleteProductById();
    }

    public void onActionComboBox() {
        this.databaseModel.setProducts(this.baseComboBox.getSelectionModel().getSelectedItem());


    }

    public void onActionEditBase() {
        String newCategoryName= DialogsUtils.editDialog(this.databaseModel.getProducts().getProduct());
        if(newCategoryName!=null){
            this.databaseModel.getProducts().setProduct(newCategoryName);
            try {
                this.databaseModel.updateProductInDataBase();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
    }
}

