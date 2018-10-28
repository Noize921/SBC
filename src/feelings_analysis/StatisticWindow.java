package feelings_analysis;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class StatisticWindow {

    public static void displayStatistics() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Analysis's Statistic");
        window.setMinWidth(500);
        window.setMinHeight(500);


    }
}
