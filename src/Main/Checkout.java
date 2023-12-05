package Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;

public class Checkout extends Application implements EventHandler<ActionEvent> {
	
	Scene scene;
	BorderPane bp;
	HBox radio, cc;
	VBox vb, isi;
	Button logOut, cancel, checkOut;
	Label check, hi, item1, item2, item3, item4, totalPrice, payment;
	ToggleGroup toggle = new ToggleGroup();
	RadioButton cash, debit, credit;
	ToolBar tb;
	Region space, space1, space2, space3;
	Alert success, fail;
//	Window success, fail;
	
	public void init() {

		// Pane
		bp = new BorderPane();
		scene = new Scene(bp, 750, 500);
		isi = new VBox();
		vb = new VBox();
		radio = new HBox();
		cc = new HBox();
		tb = new ToolBar();
		
		// Label
		
		check = new Label("Checkout");
		check.setStyle("-fx-font-size: 28pt; -fx-font-weight: bold;");
		hi = new Label("Hi, Evan");
		hi.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold;");
//		hi.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		item1 = new Label("1x Avocado Avalanches	[1 x Rp.23500,- = Rp.23500,-]");
		item2 = new Label("3x Berry Blast	[3 x Rp.24500,- = Rp.73500,-]");
		item3 = new Label("2x Avocado Avalanches	[2 x Rp.21900,- = Rp.43800,-]");
		item4 = new Label("3x Avocado Avalanches	[3 x Rp.15400,- = Rp.46200,-]");
		
		totalPrice = new Label("Total Price: Rp.187000");
//		totalPrice.setFont(Font.font("Arial" , FontWeight.BOLD, 14));
		isi.setMargin(totalPrice, new Insets(0, 0, 10, 0));
		payment = new Label("Payment Type:");
		payment.setStyle("-fx-font-size: 10pt;");
//		payment.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
	
		
		// Button
		
		logOut = new Button("Logout");
		cancel = new Button("Cancel");
		cancel.setMinHeight(40);
		cancel.setMinWidth(70);
		checkOut = new Button("Checkout");
		checkOut.setMinHeight(40);
		checkOut.setMinWidth(70);
		
		// RadioButton
		
		cash = new RadioButton("Cash");
		debit = new RadioButton("Debit");
		credit = new RadioButton("Credit");
		cash.setToggleGroup(toggle);
		debit.setToggleGroup(toggle);
		credit.setToggleGroup(toggle);
		
		// Region
		 space = new Region();
		 space.setMinWidth(590);
		 HBox.setHgrow(space, Priority.ALWAYS);
		 space1 = new Region();
		 space1.setMinWidth(5);
		 space2 = new Region();
		 space2.setMinWidth(5);
		 space3 = new Region();
		 space3.setMinWidth(200);
		 
		 // Window
//		 success = new Window();
//		 fail = new Window();
		
	}

	public void addComp() {
		bp.setTop(tb);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);
		isi.setMaxWidth(350);
		isi.getChildren().addAll(item1, item2, item3, item4, totalPrice, payment, radio, cc);
		isi.setSpacing(5);
		radio.getChildren().addAll(cash, debit, credit);
		radio.setPadding(new Insets(5, 0, 0, 0));
		radio.setAlignment(Pos.CENTER_LEFT);
		radio.setSpacing(40);
		cc.getChildren().addAll(cancel, checkOut);
		cc.setPadding(new Insets(10, 10, 0, 10));
		cc.setSpacing(10);
		cc.setAlignment(Pos.CENTER);
		tb.getItems().addAll(space1, logOut, space, hi, space2);
		tb.setPrefHeight(22);
		
//		hb.getChildren().addAll(logOut, hi);
//		hb.setSpacing(610);
//		hb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(check, isi);
		vb.setMargin(check, new Insets(0, 0, 10, 0));
	}
	
	public void initAlert() {
		success = new Alert(AlertType.INFORMATION);
		success.setContentText("All items checked out successfully, please proceed your...");
		fail = new Alert(AlertType.ERROR);
		fail.setContentText("Please select payment type");
		
	}
	
	public void setEventHandler() {
		
		checkOut.setOnAction(this);
		cancel.setOnAction(this);
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		init();
		addComp();
		initAlert();
		setEventHandler();

		primaryStage.setScene(scene);
		primaryStage.setTitle("Tes");
		primaryStage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == checkOut) {
			
			success.show();
			return;			
			
		} else if (event.getSource() == cancel) {
			
			fail.show();
			return;
			
		}
		
	}


}