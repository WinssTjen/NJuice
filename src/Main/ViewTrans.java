import javafx.application.Application;
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

public class ViewTrans extends Application implements EventHandler<ActionEvent>{

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
	TableView<Detail> detail;
	TableColumn<Detail, String> idTransaction2Column, idJuiceColumn, juiceNameColumn;
	TableColumn<Detail, Integer> qtyColumn;
	
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
	}
	
	void display() {		
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
        
        idTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("idTransaction"));
        paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        idTransactionColumn.setPrefWidth(120);
        paymentTypeColumn.setPrefWidth(120);
        usernameColumn.setPrefWidth(120);
    	
        transaction = new TableView<>();
        transaction.getColumns().addAll(idTransactionColumn, paymentTypeColumn, usernameColumn);
        
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
        
        detail = new TableView<>();
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

	@Override
	public void start(Stage stage) throws Exception {
		menu();
		display();
		scene = new Scene(bp, 1000, 500);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
	}
}