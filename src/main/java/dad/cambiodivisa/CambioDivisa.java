package dad.cambiodivisa;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField texto1;
	private TextField texto2;
	private ComboBox<Divisa> miComboBox;
	private ComboBox<Divisa> miComboBox2;
	private Button boton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		

		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		
		Divisa[] monedas= {euro,libra,dolar,yen};
		
		texto1 = new TextField();
		texto1.setMaxWidth(50);
		
		texto2 = new TextField();
		texto2.setMaxWidth(50);
		texto2.setEditable(false);
		
		miComboBox = new ComboBox<Divisa>();
		miComboBox.getItems().addAll(monedas);
		miComboBox.getSelectionModel().select(euro);
		
		miComboBox2 = new ComboBox<Divisa>();
		miComboBox2.getItems().addAll(monedas);
		miComboBox2.getSelectionModel().select(yen);
		
		boton = new Button("Cambiar");
		boton.setOnAction(e -> onBontonAction(e));
		
		HBox primero = new HBox();
		primero.getChildren().addAll(texto1, miComboBox);
		primero.setAlignment(Pos.CENTER);
		
		HBox segundo = new HBox();
		segundo.getChildren().addAll(texto2, miComboBox2);
		segundo.setAlignment(Pos.CENTER);
		
		VBox root = new VBox();
		root.getChildren().addAll(primero,segundo,boton);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root,320,200);
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private void onBontonAction(ActionEvent e) {
		
		try {
			
			Double cantidad = Double.parseDouble(texto1.getText());
			
			Divisa primero = miComboBox.getSelectionModel().getSelectedItem();
			Divisa segundo = miComboBox2.getSelectionModel().getSelectedItem();
			
			texto2.setText(""+ segundo.fromEuro(primero.toEuro(cantidad)));
			
			
		} catch (NumberFormatException e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Escribe un número");
			alert.showAndWait();
		}
		
		
	}



	public static void main(String[] args) {
		launch(args);

	}

}
