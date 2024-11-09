package com.example.librarysystem;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface interface ControllerAction {
    void execute(ActionEvent event) throws IOException, SQLException;
}

public class ControllerActionHandler {

    private final ApplicationController controller;

    public ControllerActionHandler(ApplicationController controller) {
        this.controller = controller;
    }

    private void handleControllerAction(ActionEvent event, ControllerAction action) throws IOException, SQLException {
        action.execute(event);
    }

    public void switchToIMLScreen(ActionEvent event) throws IOException, SQLException {
        handleControllerAction(event, controller::switchToMLScreen);
    }

    public void switchToReportsScreen(ActionEvent event) throws IOException, SQLException {
        handleControllerAction(event, controller::switchToReportsScreen1);
    }

    public void switchToLibrarianHomeScreen(ActionEvent event) throws IOException, SQLException {
        handleControllerAction(event, controller::switchToLibrarianHomeScreen);
    }

    public void switchToBLScreen(ActionEvent event) throws IOException, SQLException {
        handleControllerAction(event, controller::switchToBLScreen);
    }

    public void logOut(ActionEvent event) throws IOException {
        try {
            handleControllerAction(event, controller::logOut);
        } catch (SQLException e) {
            // Handle or rethrow if necessary
        }
    }
}

