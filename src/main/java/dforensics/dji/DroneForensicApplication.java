package dforensics.dji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("dforensics.dji.repository")
public class DroneForensicApplication extends Application {

    protected ConfigurableApplicationContext springContext;


    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DroneForensicApplication.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxmls/DjiMain.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        primaryStage.setMaximized(true);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        root.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        primaryStage.setTitle("Digital Drone Forensics");
        primaryStage.setScene(scene);
        primaryStage.show();

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Information");
        infoAlert.setHeaderText("Upload the csv log file");
        infoAlert.setContentText("In order to avoid disabling or not working of some of the features of this application, " +
                "please upload the csv log file at first point. \n Use the 'Upload File' button (at the top) to upload the log file.");
        infoAlert.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
