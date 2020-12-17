package controllers.popupControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import models.*;
import utils.ColorUtil;
import utils.OfferUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AgreementPopupController extends PopupController implements Initializable {
    @FXML
    public ComboBox<String> choosePlayerBox;
    @FXML
    public ComboBox<String> chooseOfferBox;
    @FXML
    public ComboBox<String> chooseOppositeOfferBox;
    @FXML
    public VBox chooseOppositeOfferVBox;
    @FXML
    public VBox chooseOfferVBox;

    private Agreement agreement;

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    void submitButtonClicked(ActionEvent event) throws IllegalAccessException, InstantiationException {
        String offerType = chooseOfferBox.getSelectionModel().getSelectedItem();
        System.out.println(offerType);

        City city = new City(123, new double[]{1, 2, 3, 4, 5}, "asd",3);
        Offer offer;

        if (offerType.equals("Sell Region")) {
            offer = new SellRegion(city);
        }
        else if (offerType.equals("Give Money")) {
            offer = new GiveMoney(123);
        }
        else if (offerType.equals("Pay Rent or Not")) {
            offer = new PayRentOrNot(city);
        }
        else if (offerType.equals("Take Percentage")) {
            offer = new TakePercentage(city, 12);
        }
        closeStage(event);
    }

    public void setAgreement(Agreement agreement){
        this.agreement = agreement;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("aggrement popup initialized");
        choosePlayerBox.setItems(FXCollections.observableArrayList(ColorUtil.colors));
        chooseOfferBox.setItems(FXCollections.observableArrayList(OfferUtil.offers));
        chooseOppositeOfferBox.setItems(FXCollections.observableArrayList(OfferUtil.offers));

        chooseOfferBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String offer = chooseOfferBox.getValue();
                if (offer.equals("Sell Region") || offer.equals("Pay Rent or Not")) {
                    chooseOfferVBox.getChildren().clear();
                    chooseOfferVBox.getChildren().addAll(chooseOfferBox, new Button("test"));
                }
                else if (offer.equals("Give Money")) {
                    chooseOfferVBox.getChildren().clear();
                    chooseOfferVBox.getChildren().addAll(chooseOfferBox);
                }
                else if (offer.equals("Take Percentage")) {
                    chooseOfferVBox.getChildren().clear();
                    chooseOfferVBox.getChildren().addAll(chooseOfferBox);
                }
            }
        });
    }
}
