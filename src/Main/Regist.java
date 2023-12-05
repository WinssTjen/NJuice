package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Regist implements EventHandler<ActionEvent>{

	// Scene & Layout
	Scene registScene;
	BorderPane regisBp;

	//	Menu Bar
	MenuBar menuBar;
	Menu menu;
	MenuItem menuItem1;
	MenuItem menuItem2;

	
	// VBox
	VBox vboxRegisterLabel;
	VBox vboxUsername;
	VBox vboxPassword;
	VBox vboxRegisterButton;
	VBox vboxAgreement;

	//	Register

	GridPane regisContainer;
	Label regisTitleLabel, regisAppLabel, regisUsernameLabel, regisPasswordLabel, errorLabel;

	//	- Field
	TextField regisUsernameField;
	PasswordField regisPasswordField;

	//	- Button
	Button regisButton;
	
	// - Checklist
	CheckBox agreement;
	
	private Stage primaryStage;
	

	void initialize() {

		regisBp = new BorderPane();
		registScene = new Scene(regisBp, 1000, 500);
		regisContainer = new GridPane();

		menuBar = new MenuBar();
		menu = new Menu("Dashboard");
		menuItem1 = new MenuItem("Login");
		menuItem2 = new MenuItem("Register");

		vboxUsername = new VBox(10);
		vboxPassword = new VBox(10);
		vboxRegisterLabel = new VBox(10);
		vboxRegisterButton = new VBox(50);
		vboxAgreement = new VBox(50);
		
		regisTitleLabel = new Label("Register");
		regisTitleLabel.setFont(Font.font(null,FontWeight.BOLD,25));
		regisAppLabel = new Label("NJuice");
		regisUsernameLabel = new Label("Username");
		regisPasswordLabel = new Label("Password");

		regisUsernameField = new TextField();
		regisUsernameField.setPromptText("Enter new unique username...");
		regisUsernameField.setPrefWidth(250);
		regisPasswordField = new PasswordField();
		regisPasswordField.setPromptText("Enter new Password...");
		regisPasswordField.setPrefWidth(250);

		agreement = new CheckBox();
		
		errorLabel = new Label();
		
		regisButton = new Button ("Register");

	}
	
	void initMenu() {
		menuBar.getMenus().add(menu);
		menu.getItems().add(menuItem1);
		menu.getItems().add(menuItem2);

		regisBp.setTop(menuBar);
	}


	void regis() {
		
		regisTitleLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		regisAppLabel.setFont(Font.font(null,FontWeight.SEMI_BOLD,15));
		
		
		vboxRegisterLabel.getChildren().addAll(regisTitleLabel,regisAppLabel);
		vboxUsername.getChildren().addAll(regisUsernameLabel, regisUsernameField);
		vboxPassword.getChildren().addAll(regisPasswordLabel, regisPasswordField);
		vboxAgreement.getChildren().addAll(agreement);
		errorLabel.setStyle("-fx-text-fill: red");
		vboxRegisterButton.getChildren().addAll(regisButton);
		
		
		agreement.setText("I agree to the terms and conditions of NJuice!");
		
		regisContainer.add(vboxRegisterLabel, 0, 0);
		regisContainer.add(vboxUsername, 0, 2);
		regisContainer.add(vboxPassword, 0, 4);
		regisContainer.add(vboxAgreement, 0, 6);
		regisContainer.add(errorLabel, 0, 7);
		regisContainer.add(vboxRegisterButton, 0, 8);
		
		regisBp.setCenter(regisContainer);
		

	}

	void setArrangements() {

		regisContainer.setAlignment(Pos.CENTER);
		regisContainer.setVgap(5);
		regisContainer.setHgap(5);


		vboxRegisterLabel.setAlignment(Pos.CENTER);
		vboxRegisterButton.setAlignment(Pos.CENTER);

	}
	
	void setEvent() {
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
		regisButton.setOnAction(this);
	}
	
	public Regist(Stage primaryStage) {
		initialize();
		initMenu();
		regis();
		setArrangements();
		setEvent();
		
		this.primaryStage = primaryStage;
		
		primaryStage.setScene(registScene);
		primaryStage.show();
	}

	

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == menuItem1) {
			Login login = new Login();
			try {
				login.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (regisUsernameField.getText().isEmpty() || regisPasswordField.getText().isEmpty()) {
			errorLabel.setText("Please input all the field");
		}
		
	}




}

