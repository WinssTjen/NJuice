package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Checkout implements EventHandler<ActionEvent> {

	Scene scene;
	BorderPane bp;
	HBox radio, cc;
	VBox vb, isi, cart;
	Button logOut, cancel, checkOut;
	Label check, hi, item1, item2, item3, item4, totalPrice, payment, price, tes;
	ToggleGroup toggle = new ToggleGroup();
	RadioButton cash, debit, credit;
	ToolBar tb;
	Region space, space1, space2, space3;
	Alert success, fail;
	Stage primaryStage;
	String usernameHome;
	List<String> cartList = new ArrayList<>();
	Connect con = new Connect();
	ObservableList<String> juiceData = FXCollections.observableArrayList();
	int r = 1;

	public void init() {
		// Pane
		bp = new BorderPane();
		scene = new Scene(bp, 1000, 750);
		isi = new VBox();
		vb = new VBox();
		radio = new HBox();
		cc = new HBox();
		tb = new ToolBar();
		cart = new VBox();

		// Label

		check = new Label("Checkout");
		check.setFont(Font.font(null, FontWeight.BOLD, 50));
		hi = new Label();
		hi.setFont(Font.font(null, FontWeight.BOLD, 10));
		price = new Label();
		price.setFont(Font.font(null, 16));

		totalPrice = new Label();
		totalPrice.setFont(Font.font(null , 14));
		isi.setMargin(totalPrice, new Insets(0, 0, 20, 0));
		payment = new Label("Payment Type:");
		payment.setPadding(new Insets(15, 0, 0, 0));
		payment.setFont(Font.font(null, 16));

		// Button

		logOut = new Button("Logout");
		logOut.setPadding(new Insets(5));
		cancel = new Button("Cancel");
		cancel.setMinHeight(50);
		cancel.setMinWidth(80);
		checkOut = new Button("Checkout");
		checkOut.setMinHeight(50);
		checkOut.setMinWidth(80);

		// RadioButton

		cash = new RadioButton("Cash");
		cash.setFont(Font.font(null, FontWeight.NORMAL, 14));
		debit = new RadioButton("Debit");
		debit.setFont(Font.font(null, FontWeight.NORMAL, 14));
		credit = new RadioButton("Credit");
		credit.setFont(Font.font(null, FontWeight.NORMAL, 14));
		cash.setToggleGroup(toggle);
		debit.setToggleGroup(toggle);
		credit.setToggleGroup(toggle);

		// Region
		space = new Region();
//		space.setMinWidth(590);
		HBox.setHgrow(space, Priority.ALWAYS);
		space1 = new Region();
		space1.setMinWidth(12);
		space2 = new Region();
		space2.setMinWidth(12);
		space3 = new Region();
		space3.setMinWidth(200);
	}

	public void addComp() {
		bp.setTop(tb);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);
		isi.setMaxWidth(350);  
		isi.getChildren().addAll( payment, radio, cc);
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
		logOut.setOnAction(this);
	}

	void show() {
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void getData(String usernameHome) {
		String query = "select Quantity, JuiceName, Price from msjuice mj join cartdetail cd on mj.JuiceId = cd.JuiceId where username = ?";
		try {
			con.setPreparedStatement(query);
			con.preparedStatement.setString(1, usernameHome);
			ResultSet rs = con.executeQuery();

			int total = 0;
			int grandTotal = 0;
			while (rs.next()) {
				Integer qty = rs.getInt("Quantity");
				String JName = rs.getString("JuiceName");
				Integer Jprice = rs.getInt("Price");
				
				total = Jprice * qty;
				String CustCart = String.format("%dx %s [%d x Rp. %d,- = Rp. %d,-]", qty, JName, qty, Jprice, total);
				grandTotal += total;
				price.setText("Total Price: Rp. " + grandTotal +",-");
				cartList.add(CustCart);
				Label lable = new Label(CustCart);
				lable.setFont(Font.font(null, FontWeight.NORMAL, 14));
				cart.getChildren().add(lable);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isi.getChildren().clear();
		hi.setText("Hi, "+ usernameHome);
		isi.getChildren().addAll(cart, price, payment, radio ,cc);


	}

	public void deleteCart(String Username) {

		String deleteQuery = "delete from cartdetail where Username = ?";
		try {
			con.setPreparedStatement(deleteQuery);
			con.preparedStatement.setString(1, Username);
			con.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}

	public int getID() {
		String countQuery = "select count (*) as total from transactionheader";

		try {
			con.setPreparedStatement(countQuery);
			ResultSet rs = con.executeQuery();

			if (rs.next()) {
				return rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public String generateID() {
		int totalID = getID();
		int attempt = 0;

		while (true) {
			String TransactionId = String.format("TR%03d", totalID + attempt + 1);
			if (!exist(TransactionId)) {
				return TransactionId;
			}
			attempt++;
		}

	}

	public boolean exist(String TransactionId) {
		String checkQuery = "select count(*) as count from transactionheader where TransactionId = ?";
		try {
			con.setPreparedStatement(checkQuery);
			con.preparedStatement.setString(1, TransactionId);
			ResultSet rs = con.executeQuery();

			if (rs.next()) {
				return rs.getInt("count") > 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void insertDetail(String TransactionId, String Username) {

		String detailQuery = "insert into transactiondetail (TransactionId, JuiceId, Quantity) " +
				"select ?, JuiceId, Quantity from cartdetail where Username = ?";
		try {
			con.setPreparedStatement(detailQuery);
			con.preparedStatement.setString(1, TransactionId);
			con.preparedStatement.setString(2, Username);
			con.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Checkout(Stage primaryStage, String usernameHome) {
		init();
		addComp();
		initAlert();
		setEventHandler();
		getData(usernameHome);

		this.primaryStage = primaryStage;
		this.usernameHome = usernameHome;
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == checkOut) {

			if (toggle.getSelectedToggle() == null) {
				fail.show();
				return;
			} else {
				success.show();
				String TransactionId = generateID();
				String Username = usernameHome;
				RadioButton select = (RadioButton) toggle.getSelectedToggle();
				String PaymentType = select.getText();

				String query = String.format("insert into transactionheader values (?, ?, ?)");
				try {
					con.setPreparedStatement(query);
					con.preparedStatement.setString(1, TransactionId);
					con.preparedStatement.setString(2, Username);
					con.preparedStatement.setString(3, PaymentType);
					con.preparedStatement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				insertDetail(TransactionId, Username); 
				deleteCart(Username);
				cartList.clear();
				
				CustHome ch = new CustHome(primaryStage, usernameHome);
				ch.show();
			}  

		} else if (event.getSource() == cancel) {
			CustHome ch = new CustHome(primaryStage, usernameHome);
			ch.show();

		}else if (event.getSource() == logOut) {
			Login login = new Login(primaryStage);
			login.show();
		}

	}


}
