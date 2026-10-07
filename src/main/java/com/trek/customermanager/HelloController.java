package com.trek.customermanager;

import com.trek.customermanager.service.LocationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    // =========================
    // FORM FIELDS
    // =========================

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> provinceComboBox;

    @FXML
    private ComboBox<String> districtComboBox;


    // =========================
    // TABLE
    // =========================

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> provinceColumn;

    @FXML
    private TableColumn<Customer, String> districtColumn;


    // =========================
    // STATUS
    // =========================

    @FXML
    private Label statusLabel;


    // =========================
    // CUSTOMER DATA
    // =========================

    private final ObservableList<Customer> customers =
            FXCollections.observableArrayList();


    // =========================
    // INITIALIZE
    // =========================

    @FXML
    public void initialize() {

        // Load all provinces
        provinceComboBox.setItems(
                FXCollections.observableArrayList(
                        LocationService.getProvinces()
                )
        );

        // District is disabled until
        // a province is selected
        districtComboBox.setDisable(true);


        // When province changes,
        // update the district list
        provinceComboBox.setOnAction(
                event -> updateDistricts()
        );


        // =========================
        // TABLE COLUMNS
        // =========================

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        provinceColumn.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );

        districtColumn.setCellValueFactory(
                new PropertyValueFactory<>("district")
        );


        // Connect customers list to table
        customerTable.setItems(customers);


        statusLabel.setText(
                "Enter customer details."
        );
    }


    // =========================
    // UPDATE DISTRICTS
    // =========================

    private void updateDistricts() {

        String selectedProvince =
                provinceComboBox.getValue();


        // Clear old districts
        districtComboBox.getItems().clear();


        // No province selected
        if (selectedProvince == null) {

            districtComboBox.setDisable(true);

            return;
        }


        // Load districts for selected province
        districtComboBox.setItems(
                FXCollections.observableArrayList(
                        LocationService.getDistricts(
                                selectedProvince
                        )
                )
        );


        districtComboBox.setDisable(false);


        statusLabel.setText(
                "Select a district in "
                        + selectedProvince
                        + "."
        );
    }


    // =========================
    // SAVE CUSTOMER
    // =========================

    @FXML
    private void handleSave() {

        String name =
                nameField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String email =
                emailField.getText().trim();

        String province =
                provinceComboBox.getValue();

        String district =
                districtComboBox.getValue();


        // =========================
        // NAME VALIDATION
        // =========================

        if (name.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Name",
                    "Please enter the customer's name."
            );

            nameField.requestFocus();

            return;
        }


        // =========================
        // PHONE REQUIRED
        // =========================

        if (phone.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Phone Number Required",
                    "Please enter the customer's phone number."
            );

            phoneField.requestFocus();

            return;
        }


        // =========================
        // ZAMBIAN PHONE VALIDATION
        // =========================
        //
        // Format:
        // 260 + 9 digits
        //
        // Example:
        // 260977123456
        //

        if (!phone.matches("260\\d{9}")) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Zambian Phone Number",
                    "Phone number must start with 260 and contain exactly 12 digits."
            );

            phoneField.requestFocus();

            return;
        }


        // =========================
        // EMAIL VALIDATION
        // =========================
        //
        // Email is optional.
        //

        if (!email.isEmpty()) {


            // Must be lowercase
            if (!email.equals(email.toLowerCase())) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Email",
                        "Email must contain lowercase letters only."
                );

                emailField.requestFocus();

                return;
            }


            // Email format
            if (!email.matches(
                    "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Email",
                        "Please enter a valid lowercase email address."
                );

                emailField.requestFocus();

                return;
            }
        }


        // =========================
        // PROVINCE VALIDATION
        // =========================

        if (province == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Province Required",
                    "Please select a province."
            );

            provinceComboBox.requestFocus();

            return;
        }


        // =========================
        // DISTRICT VALIDATION
        // =========================

        if (district == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "District Required",
                    "Please select a district."
            );

            districtComboBox.requestFocus();

            return;
        }


        // =========================
        // CREATE CUSTOMER
        // =========================

        Customer customer =
                new Customer(
                        name,
                        phone,
                        email,
                        province,
                        district
                );


        // Add customer to list
        customers.add(customer);


        // =========================
        // SUCCESS MESSAGE
        // =========================

        statusLabel.setText(
                "Customer registered successfully."
        );


        // =========================
        // CLEAR FORM
        // =========================

        nameField.clear();

        phoneField.clear();

        emailField.clear();

        provinceComboBox
                .getSelectionModel()
                .clearSelection();

        districtComboBox
                .getItems()
                .clear();

        districtComboBox.setDisable(true);


        // Return cursor to name
        nameField.requestFocus();
    }


    // =========================
    // DELETE CUSTOMER
    // =========================

    @FXML
    private void handleDelete() {

        Customer selectedCustomer =
                customerTable
                        .getSelectionModel()
                        .getSelectedItem();


        // No customer selected
        if (selectedCustomer == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Customer Selected",
                    "Please select a customer before deleting."
            );

            return;
        }


        // Confirmation dialog
        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to delete "
                                + selectedCustomer.getName()
                                + "?",
                        ButtonType.YES,
                        ButtonType.CANCEL
                );


        confirmation.setTitle(
                "Confirm Deletion"
        );

        confirmation.setHeaderText(
                "Delete Customer"
        );


        // Delete only when YES is selected
        if (confirmation.showAndWait()
                .orElse(ButtonType.CANCEL)
                == ButtonType.YES) {

            customers.remove(selectedCustomer);

            statusLabel.setText(
                    "Customer deleted successfully."
            );
        }
    }


    // =========================
    // ALERT METHOD
    // =========================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}