package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Category_product;
import ru.sapteh.model.Product;
import ru.sapteh.model.Warehouse;
import ru.sapteh.service.Category_productServ;
import ru.sapteh.service.ProductServ;
import ru.sapteh.service.WarehouseServ;

public class CreateController {
    private SessionFactory factory;
    private ObservableList<Product> observableList;
    private DAO<Product, Integer> dao;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField costTxt;

    @FXML
    private ComboBox<Category_product> categoryBox;

    @FXML
    private ComboBox<Warehouse> warehouseBox;

    @FXML
    private Button buttonOk;

    @FXML
    void initialize(){
        initData();
        DAO<Category_product, Integer> category_productIntegerDAO = new Category_productServ(factory);
        ObservableList<Category_product> list = FXCollections.observableArrayList();
        list.addAll(category_productIntegerDAO.findByAll());
        categoryBox.setItems(list);
        DAO<Warehouse, Integer> warehouseIntegerDAO = new WarehouseServ(factory);
        ObservableList<Warehouse> warehouses = FXCollections.observableArrayList();
        warehouses.addAll(warehouseIntegerDAO.findByAll());
        warehouseBox.setItems(warehouses);
    }

    @FXML
    void actionOk(ActionEvent event) {
        Product product = new Product();
        product.setName(nameTxt.getText());
        product.setCost(costTxt.getText());
        product.setCategory_product(categoryBox.getValue());
        product.setWarehouse(warehouseBox.getValue());
        dao.create(product);
        observableList.addAll(product);
    }

    public void initData(){
        factory = new Configuration().configure().buildSessionFactory();
        observableList = FXCollections.observableArrayList();
        dao = new ProductServ(factory);
        observableList.addAll(dao.findByAll());
        for (Product product: observableList) {
            System.out.println(observableList);
        }
    }
    public void setList(ObservableList observableList){
        this.observableList = observableList;
    }

}
