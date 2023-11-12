package com.furu.hibernatefxexamen;

import entity.Zona;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

public class HelloController {
    @FXML
    private TextField txt;

    @FXML
    private Button btn;

    @FXML
    private void click(){
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = HibernateUtil.getSession();

        Zona z1 = new Zona(1,txt.getText());
        session.beginTransaction();
        session.save(z1);

        session.getTransaction().commit();
        session.close();
    }
}