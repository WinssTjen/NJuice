package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DetailTransaction;
import model.Transaction;

public class ViewTrans implements EventHandler<ActionEvent>{

	Scene scene;
	BorderPane bp;	
	GridPane gp;
	ScrollPane sp;
	MenuBar mb;
	Menu menu1, menu2;
	MenuItem mi1, mi2, mi3;

	Label idTransactionLabel, paymentTypeLabel, usernameLabel, idTransaction2Label, idJuiceLabel, juiceNameLabel, qtyLabel;
	TableView<Transaction> transaction;
	TableColumn<Transaction, String> idTransactionColumn, paymentTypeColumn, usernameColumn;
	TableView<DetailTransaction> detail;
	TableColumn<DetailTransaction, String> idTransaction2Column, idJuiceColumn, juiceNameColumn;
	TableColumn<DetailTransaction, Integer> qtyColumn;

	Connect con;
	private Stage primaryStage;

	private ObservableList<Transaction> viewTrans = FXCollections.observableArrayList();
	private ObservableList<DetailTransaction> detailTrans = FXCollections.observableArrayList();

	void menu() {
		bp = new BorderPane();
		gp = new GridPane();
		sp = new ScrollPane();
		mb = new MenuBar();
		menu1 = new Menu("Admins' Dashboard");
		menu2 = new Menu("Logout");
		mi1 = new MenuItem("View Transaction");
		mi2 = new MenuItem("Manage Products");
		mi3 = new MenuItem("Logout From Admin");

		mb.getMenus().addAll(menu1, menu2);
		menu1.getItems().addAll(mi1, mi2);
		menu2.getItems().add(mi3);

		bp.setTop(mb);

		con = new Connect();

	}

	void display() {		
		scene = new Scene(bp, 1000, 750);
		Label titleLabel = new Label("View Transaction");
		titleLabel.setFont(Font.font(null, FontWeight.BOLD, 25));

		idTransactionLabel = new Label("Transaction ID");
		paymentTypeLabel = new Label("Payment Type");
		usernameLabel = new Label("Username");
		idTransaction2Label = new Label("Transaction ID");
		idJuiceLabel = new Label("Juice ID");
		juiceNameLabel = new Label("Juice Name");
		qtyLabel = new Label("Quantity");

		idTransactionColumn = new TableColumn<>("Transaction ID");
		paymentTypeColumn = new TableColumn<>("Payment Type");
		usernameColumn = new TableColumn<>("Username");

		// table 1
		idTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("idTransaction"));
		paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		idTransactionColumn.setPrefWidth(120);
		paymentTypeColumn.setPrefWidth(120);
		usernameColumn.setPrefWidth(120);

		transaction = new TableView<Transaction>();
		transaction.getColumns().addAll(idTransactionColumn, paymentTypeColumn, usernameColumn);

		// table 2
		idTransaction2Column = new TableColumn<>("Transaction ID");
		idJuiceColumn = new TableColumn<>("Juice ID");
		juiceNameColumn = new TableColumn<>("Juice Name");
		qtyColumn = new TableColumn<>("Quantity");

		idTransaction2Column.setCellValueFactory(new PropertyValueFactory<>("idTransaction2"));
		idJuiceColumn.setCellValueFactory(new PropertyValueFactory<>("idJuice"));
		juiceNameColumn.setCellValueFactory(new PropertyValueFactory<>("juiceName"));
		qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

		idTransaction2Column.setPrefWidth(120);
		idJuiceColumn.setPrefWidth(120);
		juiceNameColumn.setPrefWidth(120);
		qtyColumn.setPrefWidth(120);

		detail = new TableView<DetailTransaction>();
		detail.getColumns().addAll(idTransaction2Column, idJuiceColumn, juiceNameColumn, qtyColumn);

		Region reg1 = new Region();
		HBox.setHgrow(reg1, Priority.ALWAYS);

		Region reg2 = new Region();
		HBox.setHgrow(reg2, Priority.ALWAYS);

		ScrollPane transactionSP = new ScrollPane();
		transactionSP.setContent(transaction);

		HBox hBox1 = new HBox(reg1, transactionSP, reg2);
		hBox1.setAlignment(Pos.CENTER);

		HBox hBox2 = new HBox(reg1, detail, reg2);
		hBox2.setAlignment(Pos.CENTER);

		VBox vBox = new VBox(20);
		vBox.setPadding(new Insets(20, 0, 20, 0));
		vBox.getChildren().addAll(titleLabel, hBox1, hBox2);
		vBox.setAlignment(Pos.CENTER);

		bp.setCenter(vBox);
	}

	public void getData() {
		String query = "SELECT TransactionId, PaymentType, Username FROM transactionheader";
		ResultSet rs = con.runQuery(query);

		try {
			while (rs.next()) {
				String id = rs.getString("TransactionId");
				String pay = rs.getString("PaymentType");
				String username = rs.getString("Username");

				viewTrans.add(new Transaction(id, pay, username));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.setItems(viewTrans);
	}

	public void getData2(String selectedTrans) {
		String query = "SELECT th.TransactionId, mj.JuiceId, JuiceName, Quantity FROM transactionheader th JOIN transactiondetail td ON th.TransactionId = td.TransactionId JOIN MsJuice mj ON td.JuiceId = mj.JuiceId WHERE th.TransactionId = '" + selectedTrans + "'";
		ResultSet rs = con.runQuery(query);

		try {
			detailTrans.clear();
			while (rs.next()) {
				String id = rs.getString("TransactionId");
				String jID = rs.getString("JuiceId");
				String jName = rs.getString("JuiceName");
				Integer qty = rs.getInt("Quantity");

				detailTrans.add(new DetailTransaction(id, jID, jName, qty));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		detail.setItems(detailTrans);
	}


	public ViewTrans(Stage primaryStage) {
		menu();
		display();
		setEventHandler();
		getData();
		primaryStage.setScene(scene);
		this.primaryStage = primaryStage;
	}
	
	void show(){
		primaryStage.show();
	}

	void setEventHandler() {
		mi1.setOnAction(this);
		mi2.setOnAction(this);
		mi3.setOnAction(this);

		transaction.setOnMouseClicked(e-> {	
			Transaction selectedTrans = transaction.getSelectionModel().getSelectedItem();
			if (selectedTrans != null) {
				getData2(selectedTrans.getIdTransaction());
			}
		});
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == mi1) {
			return;
		}
		if(e.getSource() == mi2) {
			ManageProduct mp = new ManageProduct(primaryStage);
			mp.show();
		}
		if (e.getSource() == mi3) {
			Login l = new Login(primaryStage);
			l.show();
		}

	}
}