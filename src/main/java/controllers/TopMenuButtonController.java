package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonController {


    public static final String ADD_BASE_FXML = "/fxml/AddBase.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    public void addCategory() {
        mainController.setCenter(ADD_BASE_FXML);
    }





}
