package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class UserWindowController implements Initializable{
	@FXML Button _continueButton;
	@FXML Button _addButton;
	@FXML Button _deleteButton;
	@FXML Button _quitButton;
	@FXML ListView<String> _userList1;
	@FXML ListView<String> _userList2;
	
	@FXML
	public void handleContinueClick() {
		
	}
	
	@FXML
	public void handleAddClick() {
		
	}
	
	@FXML 
	public void handleDeleteClick() {
		
	}
	
	@FXML
	public void handleQuitClick() {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> teachers = FXCollections.observableArrayList(Context.getInstance().currentGame().getClassRoom().listTeachers());
		ObservableList<String> students = FXCollections.observableArrayList(Context.getInstance().currentGame().getClassRoom().listStudents());
		
		_userList1.setItems(students);
		_userList2.setItems(teachers);
		
		_userList1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		_userList2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
}
