package Main;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;
import model.Cart;

public class CustHome implements EventHandler<ActionEvent>{
	Scene sc;
	BorderPane bp, bp2;
	GridPane gp;
	StackPane sp, sp2;
	VBox vb, vb2;
	HBox hb, hb2, hb3;
	Button logoutBT, addItem, deleteItem, checkout;
	Label yourCartLabel, yourCartDescLabel, greetLabel, cartName, cartPrice, price;
	ToolBar tb;
	Region space, space2, space3, space4, space5, space6, space7;
	Alert deleteAlert, checkAlert;
	Window addWindow;
	ListView<Cart> juiceList;

	// =========================
	// window
	Label juiceLabel, juicePrice, juiceDesc, juiceQty, juiceTotal;
	Spinner<Integer> qtySpinner;
	ComboBox<String> juiceTypeName;
	Button addItemButton;
	Background background;
	private Stage primaryStage;

	Connect con;
	private ObservableList<Cart> juiceData = FXCollections.observableArrayList();
	String usernameHome;



	void initialize() {
		// scene & layout & window
		bp = new BorderPane();
		gp = new GridPane();
		sp = new StackPane();
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
		space5 = new Region();

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
		greetLabel = new Label();
		greetLabel.setFont(Font.font(null, FontWeight.BOLD, 10));
		yourCartLabel = new Label("Your Cart");
		yourCartLabel.setFont(Font.font(null, FontWeight.BOLD, 50));
		yourCartDescLabel = new Label("Your cart is empty, try adding items!");
		price = new Label("Total Price: 190000");
		price.setFont(Font.font(null, FontWeight.SEMI_BOLD, 18));

		// toolbar
		tb = new ToolBar();

		// list view
		juiceList = new ListView<Cart>();
		juiceList.setPrefHeight(300);
		juiceList.setPrefWidth(600);
		juiceList.setPadding(new Insets(20, 20, 20, 20));

		// window
		addWindow = new Window("Add new item");
		addWindow.setMaxHeight(400);
		addWindow.setMaxWidth(800);

		// region/spacing for window
		space6 = new Region();
		space7 = new Region();

		// label window
		juiceLabel = new Label("Juice: ");
		juiceLabel.setFont(Font.font(null, FontWeight.NORMAL, 15));
		juicePrice = new Label();
		juicePrice.setFont(Font.font(null, FontWeight.NORMAL, 15));
		juicePrice.setPadding(new Insets(10, 10, 10, 10));
		juiceDesc = new Label();
		juiceDesc.setFont(Font.font(null, FontWeight.SEMI_BOLD, 15));
		juiceQty = new Label("Quantity: ");
		juiceQty.setFont(Font.font(null, FontWeight.NORMAL, 15));
		juiceTotal = new Label("Total Price: ");
		juiceTotal.setFont(Font.font(null, FontWeight.NORMAL, 15));

		// spinner
		qtySpinner = new Spinner<Integer>();
		qtySpinner.setPrefHeight(30);
		qtySpinner.setPrefWidth(230);

		// combo box
		juiceTypeName = new ComboBox<String>();
		juiceTypeName.setPrefHeight(25);
		juiceTypeName.setPrefWidth(240);

		// button window
		addItemButton = new Button("Add Item");
		addItemButton.setPrefHeight(3);
		addItemButton.setPrefWidth(98);

		// connect db
		con = new Connect();
	}

	void layout() {
		vb.getChildren().addAll(yourCartLabel);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);

		if (juiceList.getItems().isEmpty()) {
			vb.getChildren().addAll(yourCartDescLabel);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(14);
		}else {
			hb2.getChildren().addAll(space4, juiceList, space5);
			hb2.setAlignment(Pos.CENTER);

			vb.getChildren().add(hb2);

			vb.getChildren().add(price);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(15);	
		}

		hb.getChildren().addAll(addItem, deleteItem, checkout);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(12);
		vb.getChildren().add(hb);

		bp.setTop(tb);
		bp.setCenter(vb);
	}

	void windowLayout() {
		// nama juice
		vb2.getChildren().add(juiceLabel);
		vb2.setSpacing(8);

		// combo box + harga juice
		hb3.getChildren().addAll(space6, juiceTypeName, space7, juicePrice);
		hb3.setAlignment(Pos.CENTER);
		vb2.getChildren().add(hb3);
		vb2.setSpacing(8);

		// desc juice
		vb2.getChildren().add(juiceDesc);
		vb2.setSpacing(8);

		// quantity juice
		vb2.getChildren().add(juiceQty);
		vb2.setSpacing(8);

		// spiner
		vb2.getChildren().add(qtySpinner);
		SpinnerValueFactory<Integer> spinnerFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
		qtySpinner.setValueFactory(spinnerFact);

		vb2.getChildren().add(juiceTotal);
		vb2.setSpacing(8);
		vb2.getChildren().add(addItemButton);

		vb2.setAlignment(Pos.CENTER);
	}

	void initTool() {
		tb.getItems().addAll(space2, logoutBT, space, greetLabel, space3);
		tb.setPrefHeight(22);
	}

	void initAlert() {
		deleteAlert = new Alert(AlertType.ERROR);
		checkAlert = new Alert(AlertType.ERROR);

		deleteAlert.setHeaderText("Error");
		deleteAlert.setContentText("Please choose which juice to delete");
		deleteAlert.getDialogPane().getScene().getWindow();

		checkAlert.setHeaderText("Error");
		checkAlert.setContentText("Your cart is empty");
	}

	void setEventHandler() {
		addItem.setOnAction(this);
		deleteItem.setOnAction(this);
		checkout.setOnAction(this);
		logoutBT.setOnAction(this);
		addItemButton.setOnAction(this);
	}

	//	public void getUsername(String usernameHome) {
	//		String query = "SELECT Username FROM msuser";
	//		ResultSet rs = con.runQuery(query);
	//		
	//		try {
	//			if (rs.next()) {
	//				usernameHome = rs.getString("Username");
	//				greetLabel.setText("Hi, " + usernameHome);
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		}
	//		getUsername(usernameHome);
	//	}

	public void getData() {
		String query = "SELECT Quantity, JuiceName, Price FROM MsJuice mj JOIN CartDetail cd ON mj.JuiceId = cd.JuiceId JOIN MsUser mu ON cd.Username = mu.Username";
		ResultSet rs = con.runQuery(query);

		try {
			while (rs.next()) {
				Integer qty = rs.getInt("Quantity");
				String jName = rs.getString("JuiceName");
				Integer price = rs.getInt("Price");

				String formattedData = qty + "x" + jName + " - [Rp. " + price + "]";

				juiceData.add(new Cart(qty, jName, price, formattedData));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		juiceList.setItems(juiceData);
	}

	public void getJuice() {
		String query = "SELECT JuiceName FROM msjuice";
		ResultSet rs = con.runQuery(query);
		juiceTypeName.getItems().clear();

		try {
			while (rs.next()) {
				String name = rs.getString("JuiceName");
				
				String query2 = "SELECT Price, JuiceDescription FROM msjuice WHERE JuiceName = ?";
				
				juiceTypeName.getItems().add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getPrice() {
		String selectedJuice = juiceTypeName.getValue();
		String query = "SELECT Price, JuiceDescription FROM msjuice WHERE JuiceName = ?";
		try {
			con.setPreparedStatement(query);
			con.preparedStatement.setString(1, selectedJuice);
			System.out.println("said");
			ResultSet rs = con.executeQuery();
			
			if (rs.next()) {
				int Jprice = rs.getInt("Price");
				String Jdesc = rs.getString("JuiceDescription");
				System.out.println("");
				juicePrice.setText("Juice Price: " + Jprice);
				juiceDesc.setText(Jdesc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public CustHome(Stage primaryStage, String usernameHome) {
		initialize();
		initAlert();
		initTool();
		layout();
		setEventHandler();
		getData();
		getJuice();
		getPrice();
		primaryStage.setScene(sc);
		this.primaryStage = primaryStage;
		this.usernameHome = usernameHome;
	}

	void show() {
		primaryStage.show();

	}

	public void openSecondaryWindow() {
		background = new Background(new BackgroundFill(Color.WHITE, null, null));
		addWindow = new Window("Add new item");
		addWindow.setPrefHeight(500);
		addWindow.setPrefWidth(1000);
		addWindow.setBackground(background);
		bp2 = new BorderPane();
		sp = new StackPane();
		vb2 = new VBox();
		hb3= new HBox();

		windowLayout();

		addWindow.getContentPane().getChildren().add(vb2);

		bp2.setCenter(addWindow);
		bp2.setAlignment(vb2, Pos.TOP_CENTER);


		Stage secondStage = new Stage();
		secondStage.setScene(new Scene(bp2, 600, 700));
		secondStage.showAndWait();
	}


	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == addItem) {
			getJuice();
			openSecondaryWindow();
		}else if (event.getSource() == deleteItem) {
			if (juiceList.getSelectionModel().getSelectedItem() != null) {
				juiceList.getItems().remove(juiceList.getSelectionModel().getSelectedItem());
			}else {
				deleteAlert.show();
				return;
			}
		}else if (event.getSource() == checkout) {
			if (juiceList.getItems().isEmpty()) {
				checkAlert.show();
				return;
			}else {
				Checkout ck = new Checkout(primaryStage);
				ck.show();
			}
		}else if (event.getSource() == logoutBT) {
			Login login = new Login(primaryStage, usernameHome);
			login.show();
		}

		if (event.getSource() == addItemButton) {
			String selectedJuice = juiceTypeName.getValue();
			int selectedQty = qtySpinner.getValue();
			
			if (selectedJuice != null) {
				getPrice();
			}else {
				juicePrice.setText("Juice Price: -");
				juiceDesc.setText("Description: -");
			}
			
//			if (selectedJuice != null || selectedQty > 1) {
//				if (!juiceList.getItems().equals(selectedJuice)) {
//					// masukin data ke list view
//					//					juiceList.getItems().add(selectedJuice);
//				}else if (juiceList.getItems().equals(selectedJuice)) {
//
//				}
//			}else {
//				return;
//			}
		}
	}

}
