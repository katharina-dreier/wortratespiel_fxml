module org.openjfx.wortratespiel_fxml {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens org.openjfx.wortratespiel_fxml to javafx.fxml;
    exports org.openjfx.wortratespiel_fxml;
}
