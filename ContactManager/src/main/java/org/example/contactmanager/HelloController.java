package org.example.contactmanager;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TableView<Contact> contactTable;

    private List<Contact> contacts = new ArrayList<>();

    public void initialize() {
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Contact, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        contactTable.getColumns().addAll(nameColumn, phoneColumn, emailColumn);
    }

    @FXML
    public void addContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        Contact contact = new Contact(name, phone, email);
        contacts.add(contact);


        clearFields();


        refreshTable();


        showAlert(Alert.AlertType.INFORMATION, "Contact Added", "Contact has been added successfully!");
    }

    @FXML
    public void deleteContact() {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            contacts.remove(selectedIndex);
            refreshTable();
            showAlert(Alert.AlertType.INFORMATION, "Contact Deleted", "Contact has been deleted successfully!");
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a contact to delete.");
        }
    }

    @FXML
    public void editContact() {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Contact selectedContact = contacts.get(selectedIndex);

            String newName = nameField.getText();
            String newPhone = phoneField.getText();
            String newEmail = emailField.getText();

            if (!newName.isEmpty() && !newPhone.isEmpty() && !newEmail.isEmpty()) {
                selectedContact.setName(newName);
                selectedContact.setPhone(newPhone);
                selectedContact.setEmail(newEmail);

                contactTable.refresh();

                clearFields();

                showAlert(Alert.AlertType.INFORMATION, "Contact Updated", "Contact has been updated successfully!");
            } else {
                showAlert(Alert.AlertType.WARNING, "Incomplete Data", "Please fill in all fields to update the contact.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a contact to edit.");
        }
    }

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }

    private void refreshTable() {
        contactTable.getItems().clear();
        contactTable.getItems().addAll(contacts);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
