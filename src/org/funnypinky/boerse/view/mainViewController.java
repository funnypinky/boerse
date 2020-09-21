package org.funnypinky.boerse.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.funnypinky.boerse.structure.Stock;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class mainViewController implements Initializable {

	
	
	@FXML
	private TableView<Stock> stock;
	
	@FXML
	private TableColumn<Stock, String> companyNameCol;
	
	@FXML
	private TableColumn<Stock, String> countryCol;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
    void onOpenDialog(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchAndAddDialog.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            return new addDialog(this);
        });
        Parent parent = fxmlLoader.load();
        addDialog dialogController = fxmlLoader.<addDialog>getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println(dialogController.getSelecetSymbol());
                //result logic
            }
        });
    }

	
	
}
