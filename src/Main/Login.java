package Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Login extends Application implements EventHandler<ActionEvent>{

	//	Scene & Layout
	Scene loginScene;
	BorderPane loginBp;

	//	Menu Bar
	MenuBar menuBar;
	Menu menu;
	MenuItem menuItem1;
	MenuItem menuItem2;

	// VBox
	VBox vboxTitleLabel;
	VBox vboxUsername;
	VBox vboxPassword;
	VBox vboxLoginButton;

	//	Login

	GridPane loginContainer;
	Label titleLabel, appLabel, usernameLabel, passwordLabel, errorLabel;

	//	- Field
	TextField usernameField;
	PasswordField passwordField;

	//	- Button
	Button loginButton;

	private Stage primaryStage;

	void initialize() {

		//		Login
		loginBp = new BorderPane();
		loginScene = new Scene(loginBp, 1000, 750);
		loginContainer = new GridPane();

		menuBar = new MenuBar();
		menu = new Menu("Dashboard");
		menuItem1 = new MenuItem("Login");
		menuItem2 = new MenuItem("Register");

		vboxUsername = new VBox(10);
		vboxPassword = new VBox(10);
		vboxTitleLabel = new VBox(10);
		vboxLoginButton = new VBox(50);

		titleLabel = new Label("Login");
		titleLabel.setFont(Font.font(null,FontWeight.BOLD,25));
		appLabel = new Label("NJuice");
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");

		usernameField = new TextField();
		usernameField.setPromptText("Enter Username...");
		usernameField.setPrefWidth(250);
		passwordField = new PasswordField();
		passwordField.setPromptText("Enter Password...");
		passwordField.setPrefWidth(250);
		
		errorLabel = new Label();

		loginButton = new Button ("Login");
	}

	void initMenu() {
		menuBar.getMenus().add(menu);
		menu.getItems().add(menuItem1);
		menu.getItems().add(menuItem2);

		loginBp.setTop(menuBar);
	}


	void login() {

		titleLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		appLabel.setFont(Font.font(null,FontWeight.SEMI_BOLD,15));

		errorLabel.setStyle("-fx-text-fill: red");

		vboxTitleLabel.getChildren().addAll(titleLabel, appLabel);
		vboxUsername.getChildren().addAll(usernameLabel, usernameField);
		vboxPassword.getChildren().addAll(passwordLabel, passwordField);
		
		
		vboxLoginButton.getChildren().addAll(loginButton);

		loginContainer.add(vboxTitleLabel, 0, 0);
		loginContainer.add(vboxUsername, 0, 2);
		loginContainer.add(vboxPassword, 0, 4);
		loginContainer.add(errorLabel, 0, 6);
		loginContainer.add(vboxLoginButton, 0, 7);


		loginBp.setCenter(loginContainer);
	}

	void setArrangements() {

		loginContainer.setAlignment(Pos.CENTER);
		loginContainer.setVgap(5);
		loginContainer.setHgap(5);

		vboxTitleLabel.setAlignment(Pos.CENTER);
		vboxLoginButton.setAlignment(Pos.CENTER);
	}

	void setEvent() {
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
		loginButton.setOnAction(this);
	}


	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		initMenu();
		login();
		setArrangements();
		setEvent();

		this.primaryStage = primaryStage;

		primaryStage.setScene(loginScene);
		primaryStage.show();

	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == menuItem2) {
			Regist regist = new Regist(primaryStage);
		} else if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
			errorLabel.setText("Credentials Failed!");
		}
		
		if (usernameField.getText().equals("customer") && passwordField.getText().equals("user123")) {
			CustHome ch = new CustHome(primaryStage);
		}else if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin123")) {
//			lanjut ke dashboard kalo admin ke dashboard admin
		}


	}



}
