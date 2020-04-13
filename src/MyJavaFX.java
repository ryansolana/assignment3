import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class MyJavaFX extends Application {

	int 			id = 0;
	int 			total = 0;
	String 			roomType= "";
	int 			noRoomsBooked= 0;

	// count of how many rooms there are available
	
	Scene loginScene, optionsScene, bookingScene, userInfoScene, submitButtonIdScene, billServiceMenuScene;

	ObservableList<Integer> number_options = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	ObservableList<String> room_options = FXCollections.observableArrayList("Single_room", "Double_room", "Delux_room",
			"Pent_house");
	
	ObservableList<String> room_options1Guest = FXCollections.observableArrayList("One single room");
	ObservableList<String> room_options2Guest = FXCollections.observableArrayList("Two single rooms");
	ObservableList<String> room_options3Guest = 
			FXCollections.observableArrayList(
					"One double room", 
					"Two single rooms");
	ObservableList<String> room_options4GuestOrMore = 
			FXCollections.observableArrayList(
					"Multiple double rooms", "Multiple combination of double and single rooms");

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
		Room room5 = new Room(5, "Double_room");
		Room room6 = new Room(6, "Double_room");
		Room room7 = new Room(7, "Double_room");
		Room room8 = new Room(8, "Double_room");
		Room room9 = new Room(9, "Delux_room");
		Room room10 = new Room(10,"Delux_room");
		Room room11 = new Room(11, "Delux_room");
		Room room12 = new Room(12, "Delux_room");
		Room room13 = new Room(13, "Pent_house");
		Room room14 = new Room(14, "Pent_house");
		Room room15 = new Room(15, "Pent_house");
		Room room16 = new Room(16, "Pent_house");

		ArrayList<Room> roomList = new ArrayList<Room>();
		
		roomList.add(room1);
		roomList.add(room2);
		roomList.add(room3);
		roomList.add(room4);
		roomList.add(room5);
		roomList.add(room6);
		roomList.add(room7);
		roomList.add(room8);
		roomList.add(room9);
		roomList.add(room10);
		roomList.add(room11);
		roomList.add(room12);
		roomList.add(room13);
		roomList.add(room14);
		roomList.add(room15);
		roomList.add(room16);

		// arrayList that holds the reservations
		//ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		
		// HashMap holds reservations in order to reference later in option 4
		Map<String, Reservation> reservationList = new HashMap<String, Reservation>();
		
		ArrayList<Guest> guestList = new ArrayList<Guest>();
	

		ObservableList<String> roomAvailibleOptions = this.makeObservableOfRoomType(roomList);

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(10);
		pane.setVgap(5.5);
		pane.setMinWidth(300);
		pane.setMinHeight(300);

		Text titleAdmin = new Text("Administrator Login");
		titleAdmin.setUnderline(true);
		pane.add(titleAdmin, 0, 0);
		
		// Enter id
		pane.add(new Label("ID:"), 0, 1);

		final TextField idTextField = new TextField();
		idTextField.setText("1");
		pane.add(idTextField, 1, 1);

		pane.add(new Label("Password:"), 0, 2);

		final TextField passwordTextField = new TextField();
		passwordTextField.setText("password");
		pane.add(passwordTextField, 1, 2);

		Button btnSubmit = new Button("LOGIN");

		pane.add(btnSubmit, 1, 3);

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

						// START OPTIONS SCREEN
						GridPane pane = new GridPane();
						pane.setAlignment(Pos.TOP_LEFT);
						pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
						pane.setHgap(5.5);
						pane.setVgap(5.5);
						pane.setMinWidth(200);
						pane.setMinHeight(100);

						Button btnBookRoom = new Button("Book room");
						pane.add(btnBookRoom, 0, 0);

						Button btnBillService = new Button("Bill service");
						pane.add(btnBillService, 0, 1);

						Button btnCurrentBookings = new Button("Current Bookings");
						pane.add(btnCurrentBookings, 0, 2);

						Button btnAvailibleRooms = new Button("Available rooms");
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
								pane.setAlignment(Pos.TOP_LEFT);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(700);
								pane.setMinHeight(600);

								// Creating display labels
								Label calculatedRate = new Label("(Rate based on room type and days booked)");
								final ComboBox<Integer> singleRoomComboBox = new ComboBox<Integer>();
								final ComboBox<Integer> doubleRoomComboBox = new ComboBox<Integer>();
								Label singleLabel = new Label("Number of singles");
								Label doubleLabel = new Label("Number of doubles");

								pane.add(new Label("Number of guests:"), 0, 0);

								// Creating Combobox selections
								final ComboBox<Integer> numberOfGuestsComboBox = new ComboBox<Integer>();
								numberOfGuestsComboBox.getItems().addAll(number_options);
								numberOfGuestsComboBox.getSelectionModel().selectFirst();
								pane.add(numberOfGuestsComboBox, 1, 0);

								pane.add(new Label("Rooms available:"), 0, 1);

								// Creating Combobox selections
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
											try {
											pane.getChildren().remove(doubleRoomComboBox);
											pane.getChildren().remove(singleRoomComboBox);
											pane.getChildren().remove(singleLabel);
											pane.getChildren().remove(doubleLabel);
											}catch(Exception err){
												System.out.println("hi err");
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
												
												if(newValue != "" && newValue != null) {
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
										

										}
									}
								});

								pane.add(new Label("Number of days booked:"), 0, 3);

								final ComboBox<Integer> numberOfDaysBookedComboBox = new ComboBox<Integer>();
								numberOfDaysBookedComboBox.getItems().addAll(number_options);
								numberOfDaysBookedComboBox.getSelectionModel().selectFirst();
								pane.add(numberOfDaysBookedComboBox, 1, 3);
								
								pane.add(new Label("Rate per day:"), 0, 4);
								pane.add(calculatedRate, 1, 4);
								Button btnCalcTotal = new Button("Calculate");

								pane.add(btnCalcTotal, 2, 4);

								btnCalcTotal.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										calculatedRate.setText(String.valueOf(total));
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
										boolean tmpCheck = false;
										if(numberOfDaysBookedComboBox.getValue() != 0 &&
										   numberOfGuestsComboBox.getValue() != 0  &&
										!isNullOrEmpty( typeOfRoomComboBox.getValue()) ){
											
											tmpCheck = true;
										
											
										if(typeOfRoomComboBox.getValue().equals("Combination(singles and doubles)")) {
										
											int roomsCanFit = 0;
											int tmpDoubleRoom = doubleRoomComboBox.getValue();
											System.out.println(tmpDoubleRoom);

											int tmpSingleRoom = singleRoomComboBox.getValue();
											int tmpGuestCount = numberOfGuestsComboBox.getValue();
											roomsCanFit = (tmpDoubleRoom * 2) + (tmpSingleRoom * 2);
											
											if(roomsCanFit < tmpGuestCount) {
												System.out.println("cannot fit the amount of guests you have");
												primaryStage.setScene(bookingScene);
												tmpCheck = false;
											}
											else {
																				
											if(!checkAvailiblilty("Single_room",tmpSingleRoom, roomList)) {
												tmpCheck = false;
												singleRoomComboBox.setValue(0);

												System.out.println("not enough single rooms available. Please check the rooms available");

											}
											else if(!checkAvailiblilty("Double_room",tmpDoubleRoom, roomList )) {
												tmpCheck = false;
												doubleRoomComboBox.setValue(0);
												System.out.println("not enough double rooms available. Please check the rooms available");

											}
											else {
												noRoomsBooked = tmpDoubleRoom + tmpSingleRoom;
											}
											 
											
											}
										}
										else {
											// JOHN CHANGE
											ArrayList<Room> tmpRoomList = getAllNonBookedRooms(roomList);
											
											String tmpRoomType = typeOfRoomComboBox.getValue();
											
											if(tmpRoomType.matches(".*\\d.*" ) && tmpRoomType != "" ) {
												
												String tmpNum = tmpRoomType.substring(0, tmpRoomType.indexOf("_"));
												int tmpSliceAt = tmpNum.length() + 1;

												Integer tmpNumToInt = Integer.valueOf(tmpNum);

												String tmpRoomTypeOnlyWord = tmpRoomType.substring(tmpSliceAt);

												if (tmpRoomTypeOnlyWord.equals("Single_rooms")) {
													
													if(!checkAvailiblilty("Single_room",tmpNumToInt, roomList )) {
														tmpCheck = false;
														typeOfRoomComboBox.setValue("");
														System.out.println("not enough single rooms available. Please check the rooms available");

													} else {
														noRoomsBooked = tmpNumToInt;
													}		

												
												} else if (tmpRoomTypeOnlyWord.equals("Double_rooms")) {
													
													if(!checkAvailiblilty("Double_room",tmpNumToInt, roomList )) {
														tmpCheck = false;
														//typeOfRoomComboBox.setValue("");
														System.out.println("not enough double rooms available. Please check the rooms available");

													}
												}	else {
													noRoomsBooked = tmpNumToInt;
												}											
											}
											else {
												
												if(!checkAvailiblilty(tmpRoomType , 1 , roomList )) {
													tmpCheck = false;
													typeOfRoomComboBox.setValue("");
													System.out.println("not enough " + tmpRoomType + " rooms available. Please check the rooms available");
												}
												else {
													noRoomsBooked = 1;
												}
												
											}
										
										}
							
										}
										else {
										
											System.out.println("missing some nessecary inputs");
											
										}
										if(tmpCheck) {
											
											
											GridPane pane = new GridPane();
											pane.setAlignment(Pos.TOP_LEFT);											
											pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
											pane.setHgap(5.5);
											pane.setVgap(5.5);
											pane.setMinWidth(700);
											pane.setMinHeight(600);

											pane.add(new Label("Title:(ex. titleName)"), 0, 0);

											final TextField tilteTextField = new TextField();
											tilteTextField.setText("title");
											pane.add(tilteTextField, 1, 0);

											pane.add(new Label("First name:(ex. John)"), 0, 1);

											final TextField firstNameTextField = new TextField();
											firstNameTextField.setText("John");
											pane.add(firstNameTextField, 1, 1);

											pane.add(new Label("Last name(ex.Doe):"), 0, 2);

											final TextField lastNameTextField = new TextField();
											lastNameTextField.setText("Doe");
											pane.add(lastNameTextField, 1, 2);

											pane.add(new Label("Address:(ex.457 oakwood ave)"), 0, 3);

											final TextField addressTextField = new TextField();
											addressTextField.setText("457 oakwood ave");
											pane.add(addressTextField, 1, 3);

											pane.add(new Label("Phone(ex.1231231234):"), 0, 4);

											final TextField phoneTextField = new TextField();
											phoneTextField.setText("1231231234");
											pane.add(phoneTextField, 1, 4);

											pane.add(new Label("Email:(ex.john@hotmail.com)"), 0, 5);

											final TextField emailTextField = new TextField();
											emailTextField.setText("john@hotmail.com");
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
													

												if(	!tilteTextField.getText().equals("") && nameIsValid(firstNameTextField.getText())  && nameIsValid(lastNameTextField.getText()) &&
													!addressTextField.getText().equals("") && phoneIsValid(phoneTextField.getText()) && emailIsValid(emailTextField.getText()) ) {
														
														// increment the booking id for an additional booking
														int bookingId = idPlus();
														String strBookingId = Integer.toString(bookingId);
													
														Guest guest1 = new Guest(bookingId,tilteTextField.getText(),firstNameTextField.getText(),
																lastNameTextField.getText(), addressTextField.getText(),
																Integer.valueOf(phoneTextField.getText()), emailTextField.getText());
														
														guestList.add(guest1);
														
														SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
														Date now = new Date();
												
														
														Calendar to = Calendar.getInstance();
														to.setTime(new Date()); // Now use today date.
														to.add(Calendar.DATE,numberOfDaysBookedComboBox.getValue()); 
														
														// CHECKPOINT
														
														Date to_ = to.getTime();
														
														String tmpTypeRoom = typeOfRoomComboBox.getValue();
														
														//if roomtype has numbers parse only name
														if(tmpTypeRoom.matches(".*\\d.*")) {
															tmpTypeRoom = getNameNoNumbers(tmpTypeRoom) ;
														}
															
														// ALERT: HARD CODED WITH 100.00 FOR NOW, FIX LATER WITH get reservationObj.getRatePerNight()
														// fixed
														Reservation res= new Reservation(bookingId, now, now,to_,firstNameTextField.getText(),
																tmpTypeRoom, noRoomsBooked, numberOfDaysBookedComboBox.getValue(), Double.parseDouble(calculatedRate.getText()));
														
														// Added to Map, key=id(string) value = res Object
														reservationList.put(strBookingId, res);
														if(	typeOfRoomComboBox.getValue().equals("Combination(singles and doubles)")) {														
															setRoomToBooked(roomList, "Single_room" , singleRoomComboBox.getValue());
															setRoomToBooked(roomList, "Double_room" , doubleRoomComboBox.getValue());
															
		
															}
															else {
															setRoomToBooked(roomList, tmpTypeRoom, noRoomsBooked);
														
															}
														//Reset type combo/ no longer needed
														typeOfRoomComboBox.setValue("");
														primaryStage.setScene(optionsScene);

													}
													else {
														System.out.println("err");
													}
													
												}
													//Guest(   int guestId, String title ,String firstName, 
													//	String lastName, String address, int phone, String email){
															
													// TO DO - Save USER DATA
													// Send back to options scene
													
												
											});
										}
									}
								});
							}

						});
						btnBillService.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								GridPane pane = new GridPane();
								pane.setAlignment(Pos.TOP_LEFT);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(700);
								pane.setMinHeight(600);

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
										pane.setAlignment(Pos.TOP_LEFT);	
										pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
										pane.setHgap(5.5);
										pane.setVgap(5.5);
										pane.setMinWidth(700);
										pane.setMinHeight(500);

										/// Get data
										
										Reservation queriedRes = reservationList.get(bookingIdTextField.getText());

										
										Button btnCalculate = new Button("Calculate total cost");
										Button btnBack = new Button("Back");
										TextField discountEntry = new TextField("1");
										
										/// DISPLAY
										pane.add(new Label("Booking ID: " + bookingIdTextField.getText()), 0, 0);
											
										pane.add(new Label("Guest name: " + queriedRes.getCustomerName()), 0, 1);

										pane.add(new Label("No of rooms booked: " + queriedRes.getNoOfRooms()), 0, 2);

										pane.add(new Label("Type of rooms: " + queriedRes.getRoomType()), 0, 3);

										pane.add(new Label("Rate per night: " + queriedRes.getRatePerNight()), 0, 4);

										// calculate discount
										pane.add(new Label("Discounts: %"), 0, 5);
										pane.add(discountEntry, 1, 5);
										
										Label totalAmount = new Label("Total Amount: ");
										
										pane.add(totalAmount, 0, 6);
										pane.add(btnCalculate, 1, 6);
										
										pane.add(btnBack, 0, 8);

										
										btnBack.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent event) {

												// TO DO - Save USER DATA
												// Send back to options scene
												primaryStage.setScene(optionsScene);

											}
										});
										
										btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent event) {
												Double discountDouble = Double.parseDouble(discountEntry.getText());
												Double discountPercentage = discountDouble / 100;
												Double initialCost = queriedRes.getRatePerNight();
												
												Double calculatedCost = initialCost - (initialCost * discountPercentage);
												
												totalAmount.setText("Total Amount: $" + calculatedCost);
											
											}
										});
										
										
										Scene billServiceMenuScene = new Scene(pane);
										primaryStage.setTitle("ShowGridPane");
										primaryStage.setScene(billServiceMenuScene);
										primaryStage.setTitle("Hotel Reservation System");
										primaryStage.show();
									}
								});
							}

						});
						
						btnCurrentBookings.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								// initialize gridPane
								GridPane pane = new GridPane();
								pane.setAlignment(Pos.TOP_LEFT);
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(700);
								pane.setMinHeight(600);

								// initialize scene
								Scene scene = new Scene(pane);
								primaryStage.setTitle("ShowGridPane");
								primaryStage.setScene(scene);
								primaryStage.setTitle("Hotel Reservation System");
								primaryStage.show();
								
								
								// DISPLAY
								Text title = new Text("Hotel Reservation System");
								title.setUnderline(true); 
								Text curBookings = new Text("No of current bookings are: " + reservationList.size());	
								Text bookingNum = new Text("Booking #");
								Text custName = new Text("Customer Name");
								Text roomType = new Text("Room Type");
								Text noOfRoom = new Text("No of Rooms");
								Text noOfDays = new Text("No of Days");
							
								Button btnBack = new Button("Back");
								btnBack.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										
										//TO DO - Save USER DATA
										//Send back to options scene
										primaryStage.setScene(optionsScene); 

									}});
								
								// adding to pane
								pane.add(title, 0, 1);
								pane.add(curBookings, 0, 2);
								
								// adding to pane of parsed info
								pane.add(bookingNum, 0, 3);
								pane.add(custName, 1, 3);
								pane.add(roomType, 2, 3);
								pane.add(noOfRoom, 3, 3);
								pane.add(noOfDays, 4, 3);
								
								// render the reservations
								int idx = 0;
								for (String key: reservationList.keySet()) {
									
                                    System.out.println("key : " + key);
                                    System.out.println("value : " + reservationList.get(key));
                                    
                                    String dataBookingId = Integer.toString(reservationList.get(key).getBookID());
                                    String dataCustName = reservationList.get(key).getCustomerName();
                                    String dataRoomType = reservationList.get(key).getRoomType();
                                    String dataNoOfRoom = Integer.toString(reservationList.get(key).getNoOfRooms());
                                    String dataNoOfDays = Integer.toString(reservationList.get(key).getNoOfDays());
                                    
                                    Text nodeBookingNum = new Text(dataBookingId),
                                    	nodeCustName = new Text(dataCustName),
                                    	nodeRoomType = new Text(dataRoomType),
                                    	nodeNoOfRoom = new Text(dataNoOfRoom),
                                    	nodeNoOfDays = new Text(dataNoOfDays);
                                    
                                    idx++;
                                    pane.add(nodeBookingNum, 0, 3+idx);
    								pane.add(nodeCustName, 1, 3+idx);
    								pane.add(nodeRoomType, 2, 3+idx);
    								pane.add(nodeNoOfRoom, 3, 3+idx);
    								pane.add(nodeNoOfDays, 4, 3+idx);
                                    
                                }
								
								// moved up based on render of the reservations
								pane.add(btnBack, 0, 5+idx);
								
								
								
							}

						});

						btnAvailibleRooms.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								GridPane pane = new GridPane();
								pane.setAlignment(Pos.TOP_LEFT);			
								pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
								pane.setHgap(5.5);
								pane.setVgap(5.5);
								pane.setMinWidth(700);
								pane.setMinHeight(500);

								Scene scene = new Scene(pane);
								primaryStage.setTitle("ShowGridPane");
								primaryStage.setScene(scene);
								primaryStage.setTitle("Hotel Reservation System");
								primaryStage.show();
								
								
								// get number of available rooms
								int numOfAvRooms = getAllNonBookedRooms(roomList).size();
								
								// DISPLAY
								Text title = new Text("Hotel Reservation System");
                                title.setUnderline(true);
                                Text numAvailableRooms = new Text("No of Available Rooms: " + numOfAvRooms);
                                Text roomID = new Text("Room ID");
                                Text roomType = new Text("Room Type");
                                Button btnBack = new Button("Back");
								btnBack.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										
										//TO DO - Save USER DATA
										//Send back to options scene
										primaryStage.setScene(optionsScene); 

									}});
                              
                                // titles
                                pane.add(title, 0, 1);
                                pane.add(numAvailableRooms, 0, 2);
                                pane.add(roomID, 0, 3);
                                pane.add(roomType, 1, 3);
                                
                                
                                int idx = 0;
								for (Room room: getAllNonBookedRooms(roomList)) {
									
                                    System.out.println("roomid : " + room.getRoom_ID());
                                    System.out.println("roomtype : " + room.getRoom_type());
                                    
                                    // get strings
                                    String dataRoomId = Integer.toString(room.getRoom_ID());
                                    String dataRoomType = room.getRoom_type();
                                    
                                    // create nodes
                                    Text nodeRoomId= new Text(dataRoomId),
                                    	nodeRoomType = new Text(dataRoomType);

                                    // add nodes to pane
                                    idx++;
                                    pane.add(nodeRoomId, 0, 3+idx);
    								pane.add(nodeRoomType, 1, 3+idx);
                                    
                                }
								
								// moved up based on render of the reservations
								pane.add(btnBack, 0, 4+idx);        
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
	public static boolean checkAvailiblilty(String str, int size, ArrayList<Room> roomList) {
		int tmpCnt = 0;

		if(size == 0) {
			return true;
		}
		else {
			for(int i = 0; i < roomList.size(); i++) {
		 
		if(roomList.get(i).room_type.equals(str)) {
			tmpCnt++;
		
			if(tmpCnt == size || tmpCnt > size) {
				return true;
		
				}
			}
		}
		}
		return false;
	}
	public boolean emailIsValid(String email) {
		   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		   return email.matches(regex);
		}
	public boolean phoneIsValid(String phone) {
		if (phone.length() != 10 ){
			
			return false;
		}
		return !phone.matches("[a-zA-Z]+");		
	}
	 // validate last name
	   public  boolean nameIsValid( String name ) {
		   return name.matches("[a-zA-Z]+");	   }
	
	public static void main(String[] args) {
		launch(args);
	}
	public int idPlus() {
		System.out.println("idPlus was called");
		System.out.println("before incrementation:");
		System.out.println(this.id);
		// increment this member var
		this.id++;
		System.out.println("after incrementation:");
		System.out.println(this.id);
		
		return this.id;
	}
	
	
public void setRoomToBooked(ArrayList<Room> listOfRooms, String roomType, int noOfRooms) {
	int cntNoOfRoomsBooked = 0 ;
	
	for(int i = 0; i < listOfRooms.size(); i++) {
		if(listOfRooms.get(i).room_type.equals(roomType) && listOfRooms.get(i).booked == false) {
			listOfRooms.get(i).booked = true;
			cntNoOfRoomsBooked++;
		}
		if(cntNoOfRoomsBooked == noOfRooms) {
			break;
		}
	}
}
ObservableList<String> makeObservableOfRoomType(ArrayList<Room> arr) {
	ArrayList<String> roomArray = new ArrayList<String>();
	for (int i = 0; i < arr.size(); i++) {
		roomArray.add(arr.get(i).room_type);
	}
	ObservableList<String> newArr = FXCollections.observableArrayList(roomArray);
	return newArr;
}

public ArrayList<Room> getAllNonBookedRooms(ArrayList<Room> rooms){
	ArrayList<Room> tmp_roomList = new ArrayList<Room>();
	
	for(int i = 0; i < rooms.size(); i++) {
		if(rooms.get(i).booked == false ) {
			tmp_roomList.add(rooms.get(i));
	
		}
	
	}
	
	return tmp_roomList;
}
public String getNameNoNumbers(String str) {
	
	String tmpNum = str.substring(0, str.indexOf("_"));
	int tmpSliceAt = tmpNum.length() + 1;
	
	String tmpRoomType1 = str.substring(tmpSliceAt);
	int tmpSliceEndAt = tmpRoomType1.length();
	String tmpRoomType2  =  tmpRoomType1.substring(0,--tmpSliceEndAt );
	
	return tmpRoomType2 ;
}
}
