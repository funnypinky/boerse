package org.funnypinky.boerse.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import org.funnypinky.boerse.api.collectData;
import org.funnypinky.boerse.structure.Company;
import org.funnypinky.boerse.structure.Stock;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
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
		this.parent.getStockMap().put(company, new Stock());
	}
	
	@FXML
	public void search(ActionEvent event) {
		Map<String, String> result = collectData.getSearchResult(searchPattern.getText());
		if(!result.isEmpty()) {
			resultView.getItems().clear();
			result.forEach((key,item) ->{
				resultView.getItems().add(new Company(key,item));
			});
		}
	}
	
	@FXML
	public void cancel(ActionEvent event) {
		closeStage(event);
	}

	 private void closeStage(ActionEvent event) {
	        Node  source = (Node)  event.getSource(); 
	        Stage stage  = (Stage) source.getScene().getWindow();
	        stage.close();
	    }

	 public String getSelecetSymbol() {
		 return this.selectedSymbol;
	 }

	
}
