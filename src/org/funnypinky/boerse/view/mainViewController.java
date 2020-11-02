package org.funnypinky.boerse.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.funnypinky.boerse.structure.Company;
import org.funnypinky.boerse.structure.Stock;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class mainViewController implements Initializable {

	private ObservableMap<Company, Stock> stockMap = FXCollections.observableHashMap();
	@FXML
	private TableView<Map.Entry<Company, Stock>> stock = new TableView<Map.Entry<Company, Stock>>(
			FXCollections.observableArrayList(stockMap.entrySet()));

	@FXML
	private TableColumn<Company, String> companyNameCol;

	@FXML
	private TableColumn<Company, String> countryCol;
	
	@FXML
	private TableColumn<Company, String> sectorCol;
	
	@FXML
	private TableColumn<Company, String> currencyCol;
	
	@FXML
	private TableColumn<Company, Double> actPrizeCol;
	
	@FXML
	private TableColumn<Company, Double> divShareCol;
	
	@FXML
	private TableColumn<Company, Double> divRenditCol;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		stockMap.addListener((MapChangeListener.Change<? extends Company, ? extends Stock> change) -> {
			stock.getItems().clear();
			stock.getItems().addAll(stockMap.entrySet());
		});

		companyNameCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Company, String> arg0) {
						Map.Entry<Company, Stock> temp = (Map.Entry<Company, Stock>) arg0.getValue();
						Company comp = (Company) temp.getKey();
						return new SimpleStringProperty(comp.toString());
					}
				});

		countryCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Company, String> arg0) {
						Map.Entry<Company, Stock> temp = (Map.Entry<Company, Stock>) arg0.getValue();
						Company comp = (Company) temp.getKey();
						return new SimpleStringProperty(comp.getCountry());
					}
				});
		sectorCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Company, String> arg0) {
						Map.Entry<Company, Stock> temp = (Map.Entry<Company, Stock>) arg0.getValue();
						Company comp = (Company) temp.getKey();
						return new SimpleStringProperty(comp.getSector());
					}
				});
		sectorCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Company, String> arg0) {
						Map.Entry<Company, Stock> temp = (Map.Entry<Company, Stock>) arg0.getValue();
						Company comp = (Company) temp.getKey();
						return new SimpleStringProperty(comp.getSector());
					}
				});
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
				// result logic
			}
		});
	}

	public ObservableMap<Company, Stock> getStockMap() {
		return stockMap;
	}

}
