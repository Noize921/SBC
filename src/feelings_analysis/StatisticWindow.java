package feelings_analysis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class StatisticWindow {

    public static void displayStatistics(Map<String, List<String>> map) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Analysis's Statistic");
        window.setMinWidth(500);
        window.setMinHeight(500);

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
//
//        for (final PieChart.Data data : chart.getData()) {
//            data.getNode().addEventHandler(MouseEvent.);
//        }

        Group group = new Group();

        group.getChildren().add(chart);

        Scene scene = new Scene(group);
        window.setScene(scene);
        window.showAndWait();
    }
}
