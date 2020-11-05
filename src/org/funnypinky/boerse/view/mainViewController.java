package org.funnypinky.boerse.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.funnypinky.boerse.structure.company;
import org.funnypinky.boerse.structure.Stock;

import javafx.beans.property.SimpleDoubleProperty;
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

	private ObservableMap<company, Stock> stockMap = FXCollections.observableHashMap();
	@FXML
	private TableView<Map.Entry<company, Stock>> stock = new TableView<Map.Entry<company, Stock>>(
			FXCollections.observableArrayList(stockMap.entrySet()));

	@FXML
	private TableColumn<company, String> companyNameCol;

	@FXML
	private TableColumn<company, String> countryCol;
	
	@FXML
	private TableColumn<company, String> sectorCol;
	
	@FXML
	private TableColumn<company, String> currencyCol;
	
	@FXML
	private TableColumn<company, Double> actPrizeCol;
	
	@FXML
	private TableColumn<company, String> divShareCol;
	
	@FXML
	private TableColumn<company, String> divRenditCol;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		stockMap.addListener((MapChangeListener.Change<? extends company, ? extends Stock> change) -> {
			stock.getItems().clear();
			stock.getItems().addAll(stockMap.entrySet());
		});

		companyNameCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<company, String> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						return new SimpleStringProperty(comp.toString());
					}
				});

		countryCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<company, String> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						return new SimpleStringProperty(comp.getCountry());
					}
				});
		sectorCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<company, String> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						return new SimpleStringProperty(comp.getSector());
					}
				});
		currencyCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<company, String> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						return new SimpleStringProperty(comp.getCurrency());
					}
				});
		divShareCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<company, String> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						String formated = String.format("%.3f %s", comp.getDiviende(), comp.getCurrency());
						return new SimpleStringProperty(formated);
					}
				});
		divRenditCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<company, String> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						String formated = String.format("%.3f %%", comp.getDivienderendite()*100);
						return new SimpleStringProperty(formated);
					}
				});
		actPrizeCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<company, Double>, ObservableValue<Double>>() {

					@Override
					public ObservableValue<Double> call(CellDataFeatures<company, Double> arg0) {
						Map.Entry<company, Stock> temp = (Map.Entry<company, Stock>) arg0.getValue();
						company comp = (company) temp.getKey();
						
						return new SimpleDoubleProperty(comp.getLastPrice()).asObject();
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

	public ObservableMap<company, Stock> getStockMap() {
		return stockMap;
	}

}
