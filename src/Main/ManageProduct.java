package Main;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Juice;

public class ManageProduct implements EventHandler<ActionEvent>{
	public Scene sc;
	public BorderPane bp;
	public VBox vb, vb2;
	public HBox hb, hb2;
	public GridPane gp;
	public Menu dashboard, logout;
	public MenuBar mn;
	public MenuItem viewTrans, manageProd, logoutAdmin;
	public Label title, productID, productName, productPrice, productDesc;
	public ComboBox<String> comboID;
	public TextField inputName;
	public Spinner<Integer> inputPrice;
	public TextArea inputDesc;
	public Button addButton, updateButton, deleteButton;
	public TableView<Juice> productTable;
	public TableColumn<Juice, String> idColumn, nameColumn, descColumn;
	public TableColumn<Juice, Integer> priceColumn;
	public Region space, space2, space3;
	public Alert addAlert;

	private ObservableList<Juice> juiceData = FXCollections.observableArrayList();

	Connect con;

	int r = 1;
	private Stage primaryStage;


	public void initialize() {
		// scene & pane
		bp = new BorderPane();
		vb = new VBox();
		vb2 = new VBox();
		hb = new HBox();
		hb2 = new HBox();
		gp = new GridPane();
		sc = new Scene(bp, 1000, 750);

		// label
		title = new Label("Manage Products");
		title.setFont(Font.font(null, FontWeight.BOLD, 20));
		title.setPadding(new Insets(20, 10, 20, 10));
		productID = new Label("Product ID" + "\n" + "to delete/remove: ");
		productName = new Label("Product Name: ");
		productPrice = new Label("Price: ");
		productDesc = new Label();

		// combo box
		comboID = new ComboBox<String>();
		comboID.getSelectionModel().selectFirst();

		// text field
		inputName = new TextField();
		inputName.setPromptText("Insert product name to be created");

		// spinner
		inputPrice = new Spinner<Integer>();

		// text area
		inputDesc = new TextArea();
		inputDesc.setPromptText("Insert the new product's text description, min. 10 & max. 100");

		// button
		addButton = new Button("Insert Juice");
		updateButton = new Button("Update Price");
		deleteButton = new Button("Remove Juice");

		// table view
		productTable = new TableView();

		//productTable.getColumns().addAll(idColumn, nameColumn, priceColumn, descColumn);

		// menu & menu bar & menu item
		mn = new MenuBar();
		dashboard = new Menu("Admin's Dashboard");
		logout = new Menu("Logout");
		viewTrans = new MenuItem("View Transaction");
		manageProd = new MenuItem("Manage Products");
		logoutAdmin = new MenuItem("Logout from admin");

		// region
		space = new Region();
		space2 = new Region();
		space3 = new Region();

		con = new Connect();

	}

	public void layout() {
		bp.setCenter(vb);
		// menu
		vb.getChildren().add(mn);
		// title
		vb.getChildren().add(title);
		// table
		hb.getChildren().addAll(space, productTable, space2);
		hb.setAlignment(Pos.CENTER);;
		vb.getChildren().add(hb);
		vb.setAlignment(Pos.TOP_CENTER);
		productTable.setPrefHeight(350);
		productTable.setPrefWidth(650);

		vb.setSpacing(10);

		vb.getChildren().add(hb2);
		gp.add(productID, 0, 0);
		productID.setPrefHeight(30);
		gp.add(productPrice, 0, 1);
		gp.add(productName, 0, 2);

		// product id
		gp.add(comboID, 1, 0);
		comboID.setPrefWidth(85);
		comboID.setPrefHeight(10);

		// product price
		gp.add(inputPrice, 1, 1);
		SpinnerValueFactory<Integer> spinnerFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 10000);
		inputPrice.setValueFactory(spinnerFact);
		inputPrice.setPrefWidth(150);
		inputPrice.setPrefHeight(25);

		// product name
		gp.add(inputName, 1, 2);
		inputName.setPrefWidth(540);

		// product desc
		gp.add(inputDesc, 1, 3);
		inputDesc.setPrefWidth(540);
		inputDesc.setPrefHeight(120);

		gp.setHgap(8);
		gp.setVgap(10);
		gp.setPadding(new Insets(0, 10, 0, 20));

		// button
		vb2.getChildren().addAll(addButton, updateButton, deleteButton);
		vb2.setSpacing(10);
		addButton.setPrefHeight(40);
		addButton.setPrefWidth(100);
		updateButton.setPrefHeight(38);
		updateButton.setPrefWidth(110);
		deleteButton.setPrefHeight(38);
		deleteButton.setPrefWidth(115);
		vb2.setAlignment(Pos.TOP_LEFT);

		hb2.getChildren().addAll(gp, vb2);
		hb2.setAlignment(Pos.CENTER);
	}

	void initMenu() {
		mn.getMenus().add(dashboard);
		mn.getMenus().add(logout);
		dashboard.getItems().add(viewTrans);
		dashboard.getItems().add(manageProd);
		logout.getItems().add(logoutAdmin);

		mn.setPrefHeight(20);
	}

	void initTable() {

		idColumn = new TableColumn<Juice, String>("Juice ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Juice, String>("juiceID"));
		idColumn.setMinWidth(bp.getWidth() / 8);

		nameColumn = new TableColumn<Juice, String>("Juice Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Juice, String>("juiceName"));
		nameColumn.setMinWidth(bp.getWidth() / 8);

		priceColumn = new TableColumn<Juice, Integer>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Juice, Integer>("juicePrice"));
		priceColumn.setMinWidth(bp.getWidth() / 6);

		descColumn = new TableColumn<Juice, String>("Juice Description");
		descColumn.setCellValueFactory(new PropertyValueFactory<Juice, String>("juiceDesc"));
		descColumn.setMinWidth(bp.getWidth() / 4);

		productTable.getColumns().addAll(idColumn, nameColumn, priceColumn, descColumn);
		productTable.setItems(juiceData);
	}

	void setEventHandler() {
		addButton.setOnAction(this);
		updateButton.setOnAction(this);
		deleteButton.setOnAction(this);
		manageProd.setOnAction(this);
		viewTrans.setOnAction(this);
		logoutAdmin.setOnAction(this);
	}

	void initAlert() {
		addAlert = new Alert(AlertType.ERROR);

		addAlert.setHeaderText("Error");
		addAlert.setContentText("Please fill all the field");
		addAlert.getDialogPane().getScene().getWindow();
	}

	public void addData() {
		String juiceName = inputName.getText();
		String juiceDescription = inputDesc.getText();
		int juicePrice = inputPrice.getValue();
		String maxIDQuery = "SELECT MAX(CAST(SUBSTRING(JuiceID, 3) AS SIGNED)) AS MaxId FROM msjuice";
		ResultSet maxIDResult = con.runQuery(maxIDQuery);
		int maxID = 0;
		try {
			if (maxIDResult.next()) {
				maxID = maxIDResult.getInt("MaxID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String juiceID = String.format("JU%03d", maxID+1);
		String query = String.format("INSERT INTO msjuice VALUES ('%s', '%s', %d, '%s')", juiceID, juiceName, juicePrice, juiceDescription);
		con.runUpdate(query);
		clearForm();

		// Refresh table
		productTable.getItems().clear();
		getData();
	}

	private void clearForm() {
		inputName.clear();
		inputDesc.clear();
		inputPrice.getValueFactory().setValue(10000);

	}

	public void getData() {
		String query = "SELECT * FROM msjuice";
		try {
			ResultSet res = con.runQuery(query);
			while (res.next()) {
				String juiceID = res.getString("JuiceID");
				String juiceName = res.getString("JuiceName");
				int juicePrice = res.getInt("Price");
				String juiceDescription = res.getString("JuiceDescription");

				juiceData.add(new Juice(juiceID, juiceName, juicePrice, juiceDescription));    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productTable.setItems(juiceData);
	}

	public void comboBox() {
		String query = "SELECT JuiceId FROM msjuice";
		try {
			ResultSet res = con.runQuery(query);
			comboID.getItems().clear();
			while (res.next()) {
				String juiceID = res.getString("JuiceID");
				comboID.getItems().add(juiceID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateData() {
		String selectedID = comboID.getValue();
		if (selectedID != null) {
			int newJuicePrice = inputPrice.getValue();
			if (newJuicePrice >= 10000) {
				String update = String.format("UPDATE msjuice SET Price = %d WHERE JuiceID = '%s'", newJuicePrice, selectedID);
				con.runUpdate(update);

				// Refresh table and ComboBox
				productTable.getItems().clear();
				getData();
				comboBox();
				clearForm();
			}
		} else {
			addAlert.show();
			return;
		}
	}

	public void deleteData() {

		String selectedJuiceID = comboID.getValue();
		if (selectedJuiceID != null) {
			String deleteQuery = String.format("DELETE FROM msjuice WHERE JuiceID = '%s'", selectedJuiceID);
			con.runUpdate(deleteQuery);

			// Refresh table and ComboBox
			productTable.getItems().clear();
			getData();
			comboBox();

			clearForm();
		} else {
			addAlert.show();
			return;
		}
	}


	public ManageProduct(Stage primaryStage) {
		initialize();
		initMenu();
		initTable();
		initAlert();
		setEventHandler();
		layout();
		comboBox();
		getData();
		clearForm();
		primaryStage.setScene(sc);
		this.primaryStage = primaryStage;
	}

	void show(){
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == manageProd) {
			return;
		}
		else if(e.getSource() == viewTrans) {
			ViewTrans vt = new ViewTrans(primaryStage);
			vt.show();
		} else if (e.getSource() == logoutAdmin){
			Login l = new Login(primaryStage, "");
			l.show();
		}

		if (e.getSource() == addButton) {
			String juiceID = String.format("JU%03d", r++);
			String juiceName = inputName.getText();
			int juicePrice = inputPrice.getValue();
			String juiceDesc = inputDesc.getText();

			// validasi 
			if (!juiceName.isBlank() && !juiceDesc.isBlank() && juiceDesc.length() >= 10 && juiceDesc.length() <= 100) {
				addData();
				comboBox();
			}else {
				addAlert.show();
				return;
			}

		}else if (e.getSource() == updateButton) {
			updateData();

		}else if (e.getSource() == deleteButton) {
			deleteData();
		}

	}

}
