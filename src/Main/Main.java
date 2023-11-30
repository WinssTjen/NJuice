package Main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	Scene sc;
	BorderPane bp;
	GridPane gp;
	VBox vb;
	HBox hb, hb2;
	Button logoutBT, addItem, deleteItem, checkout;
	Label yourCartLabel, yourCartDescLabel, greetLabel, cartName, cartPrice, price;
	ToolBar tb;
	Region space, space2, space3, space4, space5;

	Juice j = new Juice(null, null, null, 0);
	ListView<String> juiceList;


	void initialize() {
		// scene & layout
		bp = new BorderPane();
		gp = new GridPane();
		vb = new VBox();
		hb = new HBox();
		hb2 = new HBox();
		sc = new Scene(bp, 1000, 750);
		
		// spacing
		space = new Region();
		HBox.setHgrow(space, Priority.ALWAYS);
		space2 = new Region();
		space2.setMinWidth(12);
		space3 = new Region();
		space3.setMinWidth(12);
		space4 = new Region();
		space4.setMaxWidth(5);
		space5 = new Region();
		space5.setMinWidth(5);

		// button
		logoutBT = new Button("Logout");
		logoutBT.setPadding(new Insets(5));
		addItem = new Button("Add new Item to Cart");
		addItem.setPrefHeight(45);
		addItem.setPrefWidth(140);
		deleteItem = new Button("Delete Item From Cart");
		deleteItem.setPrefHeight(45);
		deleteItem.setPrefWidth(140);
		checkout = new Button("Checkout");
		checkout.setPrefHeight(45);
		checkout.setPrefWidth(80);

		// label
		greetLabel = new Label("Hi, " + "Winsen"/* username */);
		yourCartLabel = new Label("Your Cart");
		yourCartLabel.setFont(Font.font(null, FontWeight.BOLD, 50));
		yourCartDescLabel = new Label("Your cart is empty, try adding items!");
		price = new Label("Total Price: 190000");
		price.setFont(Font.font(null, FontWeight.SEMI_BOLD, 18));
		
		// toolbar
		tb = new ToolBar();
		
		// list view
		juiceList = new ListView<String>();
	}

	void layout() {
//		bp.setCenter(gp);
//		gp.add(hb2, 0, 0);
		
		vb.getChildren().addAll(yourCartLabel);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);
		
		juiceList.getItems().add("1x Avocado Avalanches - [Rp. 23500]");
		juiceList.getItems().add("3x Berry Blast - [Rp. 73500]");
		juiceList.getItems().add("2x Citrus Crush - [Rp. 43800]");
		
		if (juiceList.getItems().isEmpty()) {
			vb.getChildren().addAll(yourCartDescLabel);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(14);
		}else {
			vb.getChildren().add(juiceList);
			
			vb.getChildren().add(price);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(15);
		}
		
		hb.getChildren().addAll(addItem, deleteItem, checkout);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(12);
		vb.getChildren().add(hb);
		
		hb2.getChildren().addAll(vb);
		hb2.setAlignment(Pos.CENTER);;
		
		bp.setTop(tb);
		bp.setCenter(hb2);
	}

	void initTool() {
		tb.getItems().addAll(space2, logoutBT, space, greetLabel, space3);
		tb.setPrefHeight(22);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		initTool();
		layout();
		primaryStage.setScene(sc);
		primaryStage.setTitle("NJuice");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
