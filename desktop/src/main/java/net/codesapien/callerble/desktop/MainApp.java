package net.codesapien.callerble.desktop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import net.codesapien.callerble.common.CallerInfo;
import net.codesapien.callerble.service.BleServiceFactory;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Waiting for caller...");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("CallerBLE Desktop");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Start BLE listening
        BleServiceFactory.getListener().startListening((CallerInfo info) -> {
            Platform.runLater(() -> 
                label.setText("Caller: " + info.getName() + " (" + info.getPhoneNumber() + ")")
            );
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
} 