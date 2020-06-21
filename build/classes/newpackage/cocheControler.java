/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import apphibernate.Clientes;
import apphibernate.Coches;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author anton
 */
public class cocheControler {
    
       public  void insertarCoche(Coches c) {
        
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session;
        session = factory.openSession();
        Transaction t = session.beginTransaction();
       
        session.save(c);
        t.commit();
        session.close();
        
    }
    public static void leerCoches(ArrayList<Coches>arrayCoches){
    
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        //FROM POJO 
        Query query = session.createQuery("FROM Coches");
        List<Coches> listDatos = query.list();
        
        listDatos.forEach((Coches datos) -> {   
            Coches d = new Coches();
            d = datos;
            arrayCoches.add(d);
         });
        session.close();
    
    
    }
    public static void eliminarCoches(Coches r){
        
       Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        List<Coches> p = session.createQuery("FROM Coches WHERE id='"+r.getId()+"'").list();
        Coches aux = p.get(0);
        aux.setId(r.getId());
        aux.setMarca(r.getMarca());
        aux.setModelo(r.getModelo());
        aux.setNumeroMatricula(r.getMatricula());
        aux.setKilometros(r.getKilometros());
 
        session.beginTransaction();
        session.delete(aux);

        session.getTransaction().commit();
            
        session.close();
    }
    
    public static void modificarCoche(Coches r){
        Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
            Session session;
            session = factory.openSession();
            Transaction t = session.beginTransaction();

             List<Coches> p = session.createQuery("FROM Coches WHERE id='"+r.getId()+"'").list();
             
            Coches aux = p.get(0);
            aux.setId(r.getId());
            aux.setMarca(r.getMarca());
            aux.setModelo(r.getModelo());
            aux.setNumeroMatricula(r.getNumeroMatricula());
            aux.setKilometros(r.getKilometros());
                   
            session.update(aux);
            t.commit();
            session.close();
    }
}
