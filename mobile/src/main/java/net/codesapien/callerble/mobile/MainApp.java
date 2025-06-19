package net.codesapien.callerble.mobile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import net.codesapien.callerble.common.CallerInfo;
import net.codesapien.callerble.service.BleServiceFactory;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("CallerBLE Mobile");
        Button btn = new Button("Broadcast Caller Info");
        btn.setOnAction(e -> {
            CallerInfo info = new CallerInfo("Test Caller", "123-456-7890", System.currentTimeMillis());
            BleServiceFactory.getBroadcaster().broadcast(info);
            label.setText("Broadcasted: " + info.getName());
        });
        VBox root = new VBox(10, label, btn);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("CallerBLE Mobile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 