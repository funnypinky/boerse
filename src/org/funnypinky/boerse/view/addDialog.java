package org.funnypinky.boerse.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import org.funnypinky.boerse.api.collectData;

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
	private ListView<String> resultView;
	
	private mainViewController parent;
	
	public addDialog(mainViewController parent) {
		this.parent = parent;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resultView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		resultView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val,
		                String new_val) -> {
		                	selectedSymbol = new_val;
		                });
	}
	
	@FXML
	public void add(ActionEvent event) {
		
	}
	
	@FXML
	public void search(ActionEvent event) {
		Map<String, String> result = collectData.getSearchResult(searchPattern.getText());
		if(!result.isEmpty()) {
			result.forEach((key,item) ->{
				StringBuilder line = new StringBuilder();
				line.append(item).append(" (Symbol: ").append(key).append(")");
				resultView.getItems().add(line.toString());
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
