package com.example.librarysystem;

import Entities.Book;
import Entities.PhysicalBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class EditBookController {

    @FXML
    public TextField title, ISBN, volume, rDate, pName, categories, language, barcode, authors, rackNum;

    private ApplicationController controller = new ApplicationController();

    private ControllerActionHandler actionHandler = new ControllerActionHandler(controller);


    private PhysicalBook book;

    public void edit(ActionEvent event) throws IOException, SQLException {// done
        // take data from text fields above then assign it to queries.
        // update book sql query
        String ISBN2 = book.getISBN() + "";
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
                "Yaarob201852160.");
        PreparedStatement myStmt = myConn.prepareStatement(
                "UPDATE `library`.`book` SET `ISBN` = ?, `Title` = ?, `Release_date` = ?, `Volume` = ?, `Publisher_name` = ?, `Languge` = ? WHERE (`ISBN` = ?)");
        myStmt.setString(1, ISBN.getText());
        myStmt.setString(2, title.getText());
        myStmt.setString(3, rDate.getText());
        myStmt.setString(4, volume.getText());
        myStmt.setString(5, pName.getText());
        myStmt.setString(6, language.getText());
        myStmt.setString(7, ISBN2);
        myStmt.execute();
        ResultSet rs = myStmt.executeQuery("select * from book");
        controller.eBCScreen(event);
    }


    private void switchScreen(ActionEvent event, String screenType) throws IOException, SQLException {
        switch (screenType) {
            case "ML":
            actionHandler.switchToIMLScreen(event);
                break;
            case "Reports":
            actionHandler.switchToReportsScreen(event);
                break;
            case "LibrarianHome":
                actionHandler.switchToLibrarianHomeScreen(event);
                break;
            case "BL":
                actionHandler.switchToBLScreen(event);
                break;
            default:
                throw new IllegalArgumentException("Unknown screen type: " + screenType);
        }
    }

    public void initialize() {
        book = ApplicationController.getBookData();
        title.setText(book.getTitle());
        ISBN.setText(book.getISBN() + "");
        authors.setText(book.getAuthors());
        pName.setText(book.getPublisherName());
        volume.setText(book.getVolume() + "");
        rackNum.setText(book.getRackNum() + "");
        rDate.setText(book.getReleaseDate());
        language.setText(book.getLanguage());
        barcode.setText(book.getBarcode() + "");
    }
}
