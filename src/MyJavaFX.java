import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class MyJavaFX extends Application {

	ObservableList<String> makeObservableOfRoomType(ArrayList<Room> arr) {

		ArrayList<String> roomArray = new ArrayList<String>();

		for (int i = 0; i < arr.size(); i++) {
			roomArray.add(arr.get(i).room_type);
		}
		ObservableList<String> newArr = FXCollections.observableArrayList(roomArray);
		return newArr;
	}

	int total = 0;
	String roomType= "";

	Scene loginScene, optionsScene, bookingScene, userInfoScene, submitButtonIdScene, billServiceMenuScene;

	ObservableList<Integer> number_options = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	ObservableList<String> room_options = FXCollections.observableArrayList("Single_room", "Double_room", "Delux_room",
			"Pent_house");

	@Override
	public void start(Stage primaryStage) {

		Login admin1 = new Login(1, "password");
		Login admin2 = new Login(2, "password");

		ArrayList<Login> adminList = new ArrayList<Login>();
		adminList.add(admin1);// adding admin class object
		adminList.add(admin2);

		Room room1 = new Room(1, "Single_room");
		Room room2 = new Room(2, "Single_room");
		Room room3 = new Room(3, "Single_room");
		Room room4 = new Room(4, "Single_room");
		Room room5 = new Room(5, "Single_room");
		Room room6 = new Room(6, "Single_room");
		Room room7 = new Room(7, "Single_room");

		ArrayList<Room> roomList = new ArrayList<Room>();
		roomList.add(room1);
		roomList.add(room2);
		roomList.add(room3);
		roomList.add(room4);
		roomList.add(room5);
		roomList.add(room6);
		roomList.add(room7);

		ObservableList<String> roomAvailibleOptions = this.makeObservableOfRoomType(roomList);

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(10);
		pane.setVgap(5.5);
		pane.setMinWidth(400);
		pane.setMinHeight(300);

		// Enter id
		pane.add(new Label("ID:"), 0, 0);

		final TextField idTextField = new TextField();
		idTextField.setText("1");
		pane.add(idTextField, 1, 0);

		pane.add(new Label("Password:"), 0, 1);

		final TextField passwordTextField = new TextField();
		passwordTextField.setText("password");
		pane.add(passwordTextField, 1, 1);

		Button btnSubmit = new Button("Submit");

		pane.add(btnSubmit, 1, 2);

		loginScene = new Scene(pane);
		primaryStage.setTitle("ShowGridPane");
		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Hotel Reservation System");
		primaryStage.show();

		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if ((!idTextField.getText().matches(".*\\d.*")) || idTextField.getText() == "") {
					System.out.println("Invalid ID");
				} else if (isNullOrEmpty(passwordTextField.getText())) {

					System.out.println("Invalid password");
				} else {

					String password = passwordTextField.getText();
					int id = Integer.valueOf(idTextField.getText());

					Login admin_tmp = new Login(id, password);

					if (validate(adminList, admin_tmp)) {

						GridPane pane = new GridPane();
						pane.setAlignment(Pos.CENTER);
						pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
						pane.setHgap(5.5);
						pane.setVgap(5.5);
						pane.setMinWidth(400);
						pane.setMinHeight(300);

						Button btnBookRoom = new Button("Book room");
						pane.add(btnBookRoom, 0, 0);

						Button btnBillService = new Button("Bill service");
						pane.add(btnBillService, 0, 1);

						Button btnCurrentBookings = new Button("Current Bookings");
						pane.add(btnCurrentBookings, 0, 2);

						Button btnAvailibleRooms = new Button("Availible rooms");
						pane.add(btnAvailibleRooms, 0, 3);

						Button btnExit = new Button("Exit");
						pane.add(btnExit, 0, 4);

						optionsScene = new Scene(pane);
						primaryStage.setTitle("ShowGridPane");
						primaryStage.setScene(optionsScene);
						primaryStage.setTitle("Hotel Reservation System");
						primaryStage.show();

						btnBookRoom.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								GridPane pane = new GridPane();
								pane.setAlignment(Pos.CENTER_LEFT);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(750);
								pane.setMinHeight(300);

								Label aaa = new Label("rate based on room type and days booked)");
								final ComboBox<Integer> singleRoomComboBox = new ComboBox<Integer>();
								final ComboBox<Integer> doubleRoomComboBox = new ComboBox<Integer>();
								Label singleLabel = new Label("Number of singles");
								Label doubleLabel = new Label("Number of doubles");

								pane.add(new Label("Number of guests:"), 0, 0);

								final ComboBox<Integer> numberOfGuestsComboBox = new ComboBox<Integer>();
								numberOfGuestsComboBox.getItems().addAll(number_options);
								numberOfGuestsComboBox.getSelectionModel().selectFirst();
								pane.add(numberOfGuestsComboBox, 1, 0);

								pane.add(new Label("Rooms availible:"), 0, 1);

								final ComboBox<String> RoomsAvailibleComboBox = new ComboBox<String>();
								RoomsAvailibleComboBox.getItems().addAll(roomAvailibleOptions);
								// ADD BACK AFTER WE FIX CLICK BACK NO OF GUESTS
								// RoomsAvailibleComboBox.getSelectionModel().selectFirst();

								pane.add(RoomsAvailibleComboBox, 1, 1);

								pane.add(new Label("Type of room:"), 0, 2);

								final ComboBox<String> typeOfRoomComboBox = new ComboBox<String>();
								typeOfRoomComboBox.getItems().addAll(room_options);
								// ADD BACK AFTER WE FIX CLICK BACK NO OF GUESTS
								// RoomsAvailibleComboBox.getSelectionModel().selectFirst();

								pane.add(typeOfRoomComboBox, 1, 2);

								numberOfGuestsComboBox.valueProperty().addListener(new ChangeListener<Integer>() {

									@Override
									public void changed(ObservableValue<? extends Integer> observable, Integer oldValue,
											Integer newValue) {
										// If the condition is not met and the new value is not null: "rollback"
										if (newValue == 1) {
											try {
												room_options = FXCollections.observableArrayList("Single_room",
														"Double_room", "Delux_room", "Pent_house");

												typeOfRoomComboBox.getItems().clear();
												typeOfRoomComboBox.getItems().addAll(room_options);

												if (pane.getChildren().contains(doubleRoomComboBox)) {
													System.out.println("exists so delete");
													pane.getChildren().remove(doubleRoomComboBox);
													pane.getChildren().remove(singleRoomComboBox);
													pane.getChildren().remove(singleLabel);
													pane.getChildren().remove(doubleLabel);
													total = 0;
												}
											} catch (Exception e) {
												// Block of code to handle errors
											}

										}
										else if (newValue == 2) {
											try {
												room_options = FXCollections.observableArrayList("Single_room",
														"2_Single_rooms", "Double_room", "Delux_room", "Pent_house");

												typeOfRoomComboBox.getItems().clear();
												typeOfRoomComboBox.getItems().addAll(room_options);
												if (pane.getChildren().contains(doubleRoomComboBox)) {
													System.out.println("exists so delete");
													pane.getChildren().remove(doubleRoomComboBox);
													pane.getChildren().remove(singleRoomComboBox);
													pane.getChildren().remove(singleLabel);
													pane.getChildren().remove(doubleLabel);
													total = 0;
												}
											} catch (Exception e) {
												// Block of code to handle errors
												System.out.println("something went wrong");
											}

										} else if (newValue == 3) {
											try {
												room_options = FXCollections.observableArrayList("2_Single_rooms",
														"Double_room", "Delux_room", "Pent_house");
												typeOfRoomComboBox.getItems().clear();
												typeOfRoomComboBox.getItems().addAll(room_options);

												if (pane.getChildren().contains(doubleRoomComboBox)) {
													System.out.println("exists so delete");
													pane.getChildren().remove(doubleRoomComboBox);
													pane.getChildren().remove(singleRoomComboBox);
													pane.getChildren().remove(singleLabel);
													pane.getChildren().remove(doubleLabel);
													total = 0;
												}
											} catch (Exception e) {
												System.out.println("something went wrong");
											}

										} else if (newValue > 3) {
											try {
												String tmpSingleOption = (newValue + "_Single_rooms");
												String tmpDoubleOption = (newValue / 2 + "_Double_rooms");

												room_options = FXCollections.observableArrayList(tmpSingleOption,
														tmpDoubleOption, "Combination(singles and doubles)",
														"Delux_room", "Pent_house");
												typeOfRoomComboBox.getItems().clear();
												typeOfRoomComboBox.getItems().addAll(room_options);

												if (pane.getChildren().contains(doubleRoomComboBox)) {
													System.out.println("exists so delete");
													pane.getChildren().remove(doubleRoomComboBox);
													pane.getChildren().remove(singleRoomComboBox);
													pane.getChildren().remove(singleLabel);
													pane.getChildren().remove(doubleLabel);
													total = 0;
												}
											} catch (Exception e) {
												System.out.println("something went wrong");
											}

										} else {
											System.out.println("error-mine");
										}
									}
								});

								typeOfRoomComboBox.valueProperty().addListener(new ChangeListener<String>() {

									@Override
									public void changed(ObservableValue<? extends String> observable, String oldValue,
											String newValue) {

										if (newValue.equals("Combination(singles and doubles)")) {
											total = 0;
											int guestNum = numberOfGuestsComboBox.getValue() + 1;
											pane.add(singleLabel, 2, 2);

											pane.add(singleRoomComboBox, 3, 2);
											singleRoomComboBox.getItems().clear();
											for (int i = 0; i < guestNum; i++) {
												singleRoomComboBox.getItems().add(i);
											}
											singleRoomComboBox.getSelectionModel().selectFirst();

											pane.add(doubleLabel, 4, 2);

											guestNum = (numberOfGuestsComboBox.getValue() + 2) / 2;

											pane.add(doubleRoomComboBox, 5, 2);
											doubleRoomComboBox.getItems().clear();

											for (int i = 0; i < guestNum; i++) {
												doubleRoomComboBox.getItems().add(i);
											}

											doubleRoomComboBox.getSelectionModel().selectFirst();

											doubleRoomComboBox.valueProperty()
													.addListener(new ChangeListener<Integer>() {

														@Override
														public void changed(
																ObservableValue<? extends Integer> observable,
																Integer oldValue, Integer newValue) {
															if (newValue != null || newValue != 0) {

																total += newValue * 60;

															}

														}
													});
											singleRoomComboBox.valueProperty()
													.addListener(new ChangeListener<Integer>() {

														@Override
														public void changed(
																ObservableValue<? extends Integer> observable,
																Integer oldValue, Integer newValue) {
															if (newValue != null || newValue != 0) {

																total += newValue * 40;

															}

														}
													});

										} else {

//											pane.getChildren().remove(doubleRoomComboBox);
//											pane.getChildren().remove(singleRoomComboBox);
//											pane.getChildren().remove(singleLabel);
//											pane.getChildren().remove(doubleLabel);
										}

										if (newValue.equals("Single_room")) {
											roomType = newValue;
											total = 40;
										} else if (newValue.equals("Double_room")) {
											roomType = newValue;
											total = 60;
										} else if (newValue.equals("Delux_room")) {
											roomType = newValue;
											total = 80;
										} else if (newValue.equals("Pent_house")) {
											roomType = newValue;
											total = 100;
										} else {
											String tmpNum = newValue.substring(0, newValue.indexOf("_"));
											int tmpSliceAt = tmpNum.length() + 1;

											Integer tmpNumToInt = Integer.valueOf(tmpNum);

											String tmpRoomType = newValue.substring(tmpSliceAt);

											if (tmpRoomType.equals("Single_rooms")) {
												roomType = newValue;
												total = 40 * tmpNumToInt;

											} else if (tmpRoomType.equals("Double_rooms")) {
												roomType = newValue;
												total = 60 * tmpNumToInt;

											}
										}
									}
								});

								pane.add(new Label("Number of days booked:"), 0, 3);

								final ComboBox<Integer> numberOfDaysBookedComboBox = new ComboBox<Integer>();
								numberOfDaysBookedComboBox.getItems().addAll(number_options);
								numberOfDaysBookedComboBox.getSelectionModel().selectFirst();
								pane.add(numberOfDaysBookedComboBox, 1, 3);


								pane.add(new Label("Rate per day:"), 0, 4);
								pane.add((aaa), 1, 4);
								Button btnCalcTotal = new Button("Calculate");

								pane.add(btnCalcTotal, 2, 4);

								btnCalcTotal.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										aaa.setText(String.valueOf(total));
									}
								});

								Button btnSubmit = new Button("Submit");

								pane.add(btnSubmit, 1, 6);

								bookingScene = new Scene(pane);
								primaryStage.setTitle("ShowGridPane");
								primaryStage.setScene(bookingScene);
								primaryStage.setTitle("Hotel Reservation System");
								primaryStage.show();

								btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {

										if(numberOfDaysBookedComboBox.getValue() == 0 ||
										   numberOfGuestsComboBox.getValue() == 0  ||
										!isNullOrEmpty( typeOfRoomComboBox.getValue() ) ){
											
										

											
										if(typeOfRoomComboBox.getValue().equals("Combination(singles and doubles)")) {
										
											int roomsCanFit = 0;
											
											roomsCanFit = (doubleRoomComboBox.getValue() * 2) + (singleRoomComboBox.getValue()* 2);
											
											if(roomsCanFit < numberOfGuestsComboBox.getValue()) {
												System.out.println("cannot fit the amount of guests you have");
												primaryStage.setScene(bookingScene);
											}
											else {
												
												GridPane pane = new GridPane();
												pane.setAlignment(Pos.CENTER);
												pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
												pane.setHgap(5.5);
												pane.setVgap(5.5);
												pane.setMinWidth(400);
												pane.setMinHeight(300);

												pane.add(new Label("Title:"), 0, 0);

												final TextField tilteTextField = new TextField();
												tilteTextField.setText("1");
												pane.add(tilteTextField, 1, 0);

												pane.add(new Label("First name:"), 0, 1);

												final TextField firstNameTextField = new TextField();
												firstNameTextField.setText("1");
												pane.add(firstNameTextField, 1, 1);

												pane.add(new Label("Last name:"), 0, 2);

												final TextField lastNameTextField = new TextField();
												lastNameTextField.setText("1");
												pane.add(lastNameTextField, 1, 2);

												pane.add(new Label("Address:"), 0, 3);

												final TextField addressTextField = new TextField();
												addressTextField.setText("1");
												pane.add(addressTextField, 1, 3);

												pane.add(new Label("Phone:"), 0, 4);

												final TextField phoneTextField = new TextField();
												phoneTextField.setText("1");
												pane.add(phoneTextField, 1, 4);

												pane.add(new Label("Email:"), 0, 5);

												final TextField emailTextField = new TextField();
												emailTextField.setText("1");
												pane.add(emailTextField, 1, 5);

												Button btnSubmit = new Button("Submit");

												pane.add(btnSubmit, 1, 6);

												userInfoScene = new Scene(pane);
												primaryStage.setTitle("ShowGridPane");
												primaryStage.setScene(userInfoScene);
												primaryStage.setTitle("Hotel Reservation System");
												primaryStage.show();

												btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
													@Override
													public void handle(ActionEvent event) {

														// TO DO - Save USER DATA
														// Send back to options scene
														primaryStage.setScene(optionsScene);

													}
												});
											}
										}
							
										}
										else {
										
											System.out.println("missing some nessecary inputs");
											
										}
									}
								});
							}

						});
						btnBillService.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								GridPane pane = new GridPane();
								pane.setAlignment(Pos.CENTER);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(400);
								pane.setMinHeight(300);

								pane.add(new Label("Enter booking ID:"), 0, 0);

								final TextField bookingIdTextField = new TextField();
								bookingIdTextField.setText("1");
								pane.add(bookingIdTextField, 0, 1);

								Button btnSubmit = new Button("Submit");

								pane.add(btnSubmit, 1, 2);

								submitButtonIdScene = new Scene(pane);
								primaryStage.setTitle("ShowGridPane");
								primaryStage.setScene(submitButtonIdScene);
								primaryStage.setTitle("Hotel Reservation System");
								primaryStage.show();

								btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {

										primaryStage.setScene(submitButtonIdScene);
										GridPane pane = new GridPane();
										pane.setAlignment(Pos.CENTER);
										pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
										pane.setHgap(5.5);
										pane.setVgap(5.5);
										pane.setMinWidth(400);
										pane.setMinHeight(300);

										/// IMPLEMENT
										Label aaa = new Label("*implement*(ID based on Booking id entered)");

										pane.add(new Label("Booking ID:"), 0, 0);
										pane.add(aaa, 1, 0);

										pane.add(new Label("Guest name:"), 0, 1);

										final TextField guestNameTextField = new TextField();
										guestNameTextField.setText("name");
										pane.add(guestNameTextField, 1, 1);

										pane.add(new Label("First name:"), 0, 2);

										final ComboBox<Integer> numberOfDaysBookedComboBox = new ComboBox<Integer>();
										numberOfDaysBookedComboBox.getItems().addAll(number_options);
										numberOfDaysBookedComboBox.getSelectionModel().selectFirst();
										pane.add(numberOfDaysBookedComboBox, 1, 2);

										pane.add(new Label("Last name:"), 0, 3);

										final TextField lastNameTextField = new TextField();
										lastNameTextField.setText("1");
										pane.add(lastNameTextField, 1, 3);

										pane.add(new Label("Address:"), 0, 4);

										final TextField addressTextField = new TextField();
										addressTextField.setText("1");
										pane.add(addressTextField, 1, 4);

										pane.add(new Label("Phone:"), 0, 5);

										final TextField phoneTextField = new TextField();
										phoneTextField.setText("1");
										pane.add(phoneTextField, 1, 5);

										pane.add(new Label("Email:"), 0, 6);

										final TextField emailTextField = new TextField();
										emailTextField.setText("1");
										pane.add(emailTextField, 1, 6);

										Button btnSubmit = new Button("Submit");

										pane.add(btnSubmit, 1, 7);

										Scene billServiceMenuScene = new Scene(pane);
										primaryStage.setTitle("ShowGridPane");
										primaryStage.setScene(billServiceMenuScene);
										primaryStage.setTitle("Hotel Reservation System");
										primaryStage.show();

										btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent event) {

												// TO DO - Save USER DATA
												// Send back to options scene
												primaryStage.setScene(optionsScene);

											}
										});

									}
								});
							}

						});
						btnCurrentBookings.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								GridPane pane = new GridPane();
								pane.setAlignment(Pos.CENTER);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(400);
								pane.setMinHeight(300);

								Scene scene = new Scene(pane);
								primaryStage.setTitle("ShowGridPane");
								primaryStage.setScene(scene);
								primaryStage.setTitle("Hotel Reservation System");
								primaryStage.show();
							}

						});

						btnAvailibleRooms.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								GridPane pane = new GridPane();
								pane.setAlignment(Pos.CENTER);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(400);
								pane.setMinHeight(300);

								Scene scene = new Scene(pane);
								primaryStage.setTitle("ShowGridPane");
								primaryStage.setScene(scene);
								primaryStage.setTitle("Hotel Reservation System");
								primaryStage.show();
							}

						});

						btnExit.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								Stage stage = (Stage) btnExit.getScene().getWindow();
								stage.close();
							}

						});
					} else {
						System.out.println("bad acc");

					}
				}
			}
		});
	}

	boolean validate(ArrayList<Login> accList, Login acc) {

		for (int i = 0; i < accList.size(); i++) {

			if (acc.id == accList.get(i).id && acc.password.equals(accList.get(i).password)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty())
			return false;
		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
