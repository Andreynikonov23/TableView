package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Product;
import ru.sapteh.service.ProductServ;

import java.io.IOException;

public class TableController {
   SessionFactory factory = new Configuration().configure().buildSessionFactory();
   DAO<Product, Integer> productDAO = new ProductServ(factory);
   ObservableList<Product> observableList = FXCollections.observableArrayList();
   private Product product;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> costColumn;

    @FXML
    private TableColumn<Product, String> CategoryColumn;

    @FXML
    private TableColumn<Product, String> warehouseColumn;

    @FXML
    void initialize(){
        tableView.setItems(observableList);
        observableList.addAll(productDAO.findByAll());
        idColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getId()));
        nameColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getName()));
        costColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getCost()));
       CategoryColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getCategory_product().getName()));
        warehouseColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getWarehouse().getName()));
        tableView.getSelectionModel().selectedItemProperty().addListener((obj, oldValue, newValue) -> {
            product = newValue;
        });
    }

    @FXML
    void actionCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/buttons/create.fxml"));
        AnchorPane pane = loader.load();
        Stage stage = new Stage();
        stage.setTitle("CREATE");
        stage.setScene(new Scene(pane));
        CreateController createController = loader.getController();
        createController.setList(observableList);
        stage.show();
    }

    @FXML
    void actionDelete(ActionEvent event) {
        product = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(product);
        productDAO.delete(product);
    }

    @FXML
    void actionEdit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/buttons/edit.fxml"));
        AnchorPane pane = loader.load();
        Stage stage = new Stage();
        stage.setTitle("EDIT");
        stage.setScene(new Scene(pane));
        EditController editController = loader.getController();
        editController.setData(product, observableList);
        stage.show();
    }

}
