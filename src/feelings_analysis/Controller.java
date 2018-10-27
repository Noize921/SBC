package feelings_analysis;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private static Path path = Paths.get(new File(Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "feelings_analysis/WordNet-AffectRuRomVer2").toURI());
    private StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
//        Controller.analyse(path);

        Controller controller = new Controller();
        controller.setStringBuilder("Jaja");
        System.out.println(controller.getStringBuilder().toString());
    }

    public static void analyse( Path path) {
        try(Stream<Path> paths = Files.walk(path)) {
            paths.filter(Files::isRegularFile)
                    .forEach(f -> {
                        System.out.println(f.getFileName().toString().replace(".txt", ""));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStringBuilder(String str) {
        this.stringBuilder.append(str);
    }

    public StringBuilder getStringBuilder() {
        return this.stringBuilder;
    }
}
