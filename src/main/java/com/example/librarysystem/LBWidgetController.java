package com.example.librarysystem;

import Entities.PhysicalBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LBWidgetController {

    @FXML
    public Button edit, remove;

    @FXML
    public Label title, ISBN, authors, publisher, volume, rackNumber, releaseDate, language;

    private PhysicalBook book;

    private ApplicationController controller = new ApplicationController();
    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    public void edit(ActionEvent event) throws IOException {
        ApplicationController.setBook(book);
        controller.editBook(event);
    }

    public void remove(ActionEvent event) throws IOException, SQLException {// done
        // Delete book sql query.
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
                "Yaarob201852160.");
        PreparedStatement st = myConn.prepareStatement("DELETE FROM `library`.`book` WHERE (`ISBN` = ?)");
        st.setString(1, book.getISBN() + "");
        st.executeUpdate();
        Pane pane = (Pane) ((Node) event.getSource()).getParent();
        ((FlowPane) pane.getParent()).getChildren().remove(pane);
    }

    public void initialize() {
        book = ApplicationController.getBookData();
        setBookDetails();
    }

    private void setBookDetails() {
        setBookTitle();
        setBookISBN();
        setBookAuthors();
        setBookPublisher();
        setBookVolume();
        setBookRackNumber();
        setBookReleaseDate();
        setBookLanguage();
    }

    private void setBookTitle() {
        title.setText(book.getTitle());
    }

    private void setBookISBN() {
        ISBN.setText(book.getISBN() + "");
    }

    private void setBookAuthors() {
        authors.setText(book.getAuthors());
    }

    private void setBookPublisher() {
        publisher.setText(book.getPublisherName());
    }

    private void setBookVolume() {
        volume.setText(book.getVolume() + "");
    }

    private void setBookRackNumber() {
        rackNumber.setText(book.getRackNum() + "");
    }

    private void setBookReleaseDate() {
        releaseDate.setText(book.getReleaseDate());
    }

    private void setBookLanguage() {
        language.setText(book.getLanguage());
    }

}
