package feelings_analysis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticWindow {

    public static void displayStatistics(Map<String, List<String>> map) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Analysed Data");
        window.setMinWidth(500);
        window.setMinHeight(600);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                  new PieChart.Data("Anger", map.get("Anger").size()),
                  new PieChart.Data("Disgust", map.get("Disgust").size()),
                  new PieChart.Data("Fear", map.get("Fear").size()),
                  new PieChart.Data("Joy", map.get("Joy").size()),
                  new PieChart.Data("Sadness", map.get("Sadness").size()),
                  new PieChart.Data("Surprise", map.get("Surprise").size())
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Analysed Data");

//        final Label caption = new Label("");
//        caption.setTextFill(Color.WHITE);
//        caption.setStyle("-fx-font: 20 arial;");

//        for (final PieChart.Data data : chart.getData()) {
//            data.getNode().setOnMouseEntered(e -> {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(data.getPieValue()) + "%");
//
//                System.out.println("Mouse entered " +  data.getPieValue());
//            });
//        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setPadding(new Insets(5, 5, 5, 5));

        grid.add(chart, 0, 0, 6, 1);

        grid.add(new Label("Anger:"), 0, 1);
        grid.add(new Label("Disgust:"), 1, 1);
        grid.add(new Label("Fear:"), 2, 1);
        grid.add(new Label("Joy:"), 3, 1);
        grid.add(new Label("Sadness:"), 4, 1);
        grid.add(new Label("Surprise:"), 5, 1);

        grid.add(new Label(map.get("Anger").stream().collect(Collectors.joining("\n"))), 0, 2);
        grid.add(new Label(map.get("Disgust").stream().collect(Collectors.joining("\n"))), 1, 2);
        grid.add(new Label(map.get("Fear").stream().collect(Collectors.joining("\n"))), 2, 2);
        grid.add(new Label(map.get("Joy").stream().collect(Collectors.joining("\n"))), 3, 2);
        grid.add(new Label(map.get("Sadness").stream().collect(Collectors.joining("\n"))), 4, 2);
        grid.add(new Label(map.get("Surprise").stream().collect(Collectors.joining("\n"))), 5, 2);


        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }
}
