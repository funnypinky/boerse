package org.funnypinky.boerse.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.funnypinky.boerse.api.collectData;
import org.funnypinky.boerse.structure.Company;
import org.funnypinky.boerse.structure.Stock;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class addDialog implements Initializable {

	private String selectedSymbol;

	@FXML
	private TextField searchPattern;

	@FXML
	private ListView<Company> resultView;

	private mainViewController parent;

	public addDialog(mainViewController parent) {
		this.parent = parent;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resultView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void add(ActionEvent event) {
		Company company = collectData.getCompanyData(resultView.getSelectionModel().getSelectedItem().getSymbol());
		if (!containsSymbol(company, this.parent.getStockMap())) {
			company.setSeriesDaily(collectData.collectDailySeries(company.getSymbol()));
			this.parent.getStockMap().put(company, new Stock());
		}
	}

	@FXML
	public void search(ActionEvent event) {
		Button source = (Button) event.getSource();
		ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("search.gif")));
		icon.setPreserveRatio(true);
		icon.setFitHeight(16);
		
		source.setGraphic(icon);
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Map<String, String> result = collectData.getSearchResult(searchPattern.getText());
				if (!result.isEmpty()) {
					resultView.getItems().clear();
					result.forEach((key, item) -> {
						resultView.getItems().add(new Company(key, item));
					});
				}
				source.setGraphic(null);
			}

		});

	}

	@FXML
	public void cancel(ActionEvent event) {
		closeStage(event);
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public String getSelecetSymbol() {
		return this.selectedSymbol;
	}

	private boolean containsSymbol(Company company, Map<Company, Stock> map) {
		boolean value = false;
		for (Company item : map.keySet()) {
			if (item.getSymbol().equals(company.getSymbol())) {
				value = true;
			}
		}
		return value;

	}
}
