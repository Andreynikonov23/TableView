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

public class EditController {
    private SessionFactory factory;
    private DAO<Product, Integer> productIntegerDAO;
    private DAO<Category_product, Integer> category_productIntegerDAO;
    private DAO<Warehouse, Integer> warehouseIntegerDAO;
    private ObservableList<Product> products;
    private ObservableList<Category_product> category_products;
    private ObservableList<Warehouse> warehouses;
    private Product product;

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
        categoryBox.setItems(category_products);
        warehouseBox.setItems(warehouses);
    }

    @FXML
    void actionOk(ActionEvent event) {
        product.setName(nameTxt.getText());
        product.setCost(costTxt.getText());
        product.setCategory_product(categoryBox.getValue());
        product.setWarehouse(warehouseBox.getValue());
        productIntegerDAO.update(product);
        products.removeAll(products);
        products.addAll(productIntegerDAO.findByAll());
    }
    public void initData(){
        factory = new Configuration().configure().buildSessionFactory();
        productIntegerDAO = new ProductServ(factory);
        category_productIntegerDAO = new Category_productServ(factory);
        warehouseIntegerDAO = new WarehouseServ(factory);
        products = FXCollections.observableArrayList();
        category_products = FXCollections.observableArrayList();
        warehouses = FXCollections.observableArrayList();
        products.addAll(productIntegerDAO.findByAll());
        category_products.addAll(category_productIntegerDAO.findByAll());
        warehouses.addAll(warehouseIntegerDAO.findByAll());
    }
    public void setData(Product product, ObservableList observableList){
        this.product = product;
        this.products = observableList;
        nameTxt.setText(product.getName());
        costTxt.setText(product.getCost());
        categoryBox.setValue(product.getCategory_product());
        warehouseBox.setValue(product.getWarehouse());
    }
}
