package feelings_analysis;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main extends Application {
    private static final String SPLITER = "[\\s.,:;!?()\"]+";
    private static String windowTitle = "Feelings Analysis";
    private static Path path = Paths.get(new File(Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "feelings_analysis/WordNet-AffectRuRomVer2").toURI());

    private Button chooseFilesButton;
    private Text sceneTitle;
    private Text infoText;

    private Set<String> sourceText;
    private List<String> anger;
    private List<String> disgust;
    private List<String> fear;
    private List<String> joy;
    private List<String> sadness;
    private List<String> surprise;
    private List<String> feelings;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        window.setTitle(windowTitle);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        sceneTitle = new Text("Choose files for analysis");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 1,1);

        chooseFilesButton = new Button("Choose files...");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.getChildren().add(chooseFilesButton);
        grid.add(hBox, 0, 1);

        chooseFilesButton.setOnAction(e -> {
            chooseMultipleFiles();
            analyse(this.sourceText, path);
        });

        infoText = new Text();
        infoText.setFill(Color.FIREBRICK);
        HBox infoTextHBox = new HBox(10);
        infoTextHBox.setAlignment(Pos.CENTER);
        infoTextHBox.setPadding(new Insets(10, 0, 0, 0));
        infoTextHBox.getChildren().add(infoText);
        grid.add(infoTextHBox, 0, 2);

        Scene scene = new Scene(grid, 300, 275);
        window.setScene(scene);
        window.show();
    }

    public void chooseMultipleFiles() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose TXT documents");
        fileChooser.setInitialDirectory(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/feelings_analysis/texte_rom"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Documents", "*.txt"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if (selectedFiles != null) {
            selectedFiles.stream()
                        .forEach(f -> {
                            Path path = Paths.get(f.toURI());
                            Charset charset = Charset.forName("Cp1252");

                            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                                System.out.println("Here");

                                sourceText = reader.lines()
                                        .map(line -> line.split(SPLITER))
                                        .flatMap(Arrays::stream)
                                        .map(String::toLowerCase)
                                        .filter(word -> word.matches("[A-Za-z?-_]+"))
                                        .collect(Collectors.toCollection(HashSet::new));

                            } catch (Exception e) {
                                System.out.println("Error in file choosing method");
                                e.printStackTrace();
                            }
                            System.out.println(f.length());
                        });
        } else {
            infoText.setText("Selection Canceled");
        }
    }

    public void analyse(Set<String> hashSet, Path sourcePath) {
        try(Stream<Path> paths = Files.walk(sourcePath)) {
            paths.filter(Files::isRegularFile)
                    .forEach(f -> {
                        System.out.println(f.getFileName().toString().replace(".txt", ""));
                        System.out.println();

                        Path path = Paths.get(f.toUri());
                        Charset charset = Charset.forName("UTF8");

                        try(BufferedReader reader = Files.newBufferedReader(path, charset)) {
                            StringBuffer tempStringBuffer = new StringBuffer();

                            feelings = reader.lines()
                                    .map(line -> line.split(SPLITER))
                                    .flatMap(Arrays::stream)
                                    .map(String::toLowerCase)
                                    .filter(word -> word.matches("[A-Za-z?-_]+"))
                                    .collect(Collectors.toList());

                            switch (f.getFileName().toString().replace(".txt", "")) {
                                case "anger": {
                                    anger = hashSet.stream()
                                            .filter(word -> feelings.contains(word))
                                            .collect(Collectors.toList());
                                }

                                case "disgust": {
                                    disgust = hashSet.stream()
                                            .filter(word -> feelings.contains(word))
                                            .collect(Collectors.toList());

                                }

                                case "fear": {
                                    fear = hashSet.stream()
                                            .filter(word -> feelings.contains(word))
                                            .collect(Collectors.toList());
                                }

                                case "joy": {
                                    joy = hashSet.stream()
                                            .filter(word -> feelings.contains(word))
                                            .collect(Collectors.toList());
                                }

                                case "sadness": {
                                    sadness = hashSet.stream()
                                            .filter(word -> feelings.contains(word))
                                            .collect(Collectors.toList());
                                }

                                case "surprise": {
                                    surprise = hashSet.stream()
                                            .filter(word -> feelings.contains(word))
                                            .collect(Collectors.toList());
                                }
                            }

                        } catch (IOException e) {
                            System.out.println("Error in analysis method");
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Text getInfoText() { return this.infoText; }
    public Set<String> getSourceText() { return this.sourceText; }
    public List getStatistics() { return this.feelings; }

}
