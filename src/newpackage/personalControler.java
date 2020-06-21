/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import apphibernate.Clientes;
import apphibernate.Personal;
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
 * @author cuvil
 */
public class personalControler {
    
   public  void insertarPersonal(Personal c) {
        
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
    public static void leerPersonal(ArrayList<Personal>arrayPersonal){
    
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        //FROM POJO 
        Query query = session.createQuery("FROM Personal");
        List<Personal> listDatos = query.list();
        
        listDatos.forEach((Personal datos) -> {   
            Personal d = new Personal();
            d = datos;
            arrayPersonal.add(d);
         });
        session.close();
    
    
    }
    public static void eliminarPersonal(Personal r){
        
       Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        List<Personal> p = session.createQuery("FROM Personal WHERE id='"+r.getId()+"'").list();
        Personal aux = p.get(0);
        aux.setId(r.getId());
        aux.setNombre(r.getNombre());
        aux.setApellidos(r.getApellidos());
        aux.setTelefono(r.getTelefono());
        aux.setSeccion(r.getSeccion());
 
        session.beginTransaction();
        session.delete(aux);

        session.getTransaction().commit();
            
        session.close();
    }
    
    public static void modificarPersonal(Personal r){
        Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
            Session session;
            session = factory.openSession();
            Transaction t = session.beginTransaction();

             List<Personal> p = session.createQuery("FROM Personal WHERE id='"+r.getId()+"'").list();
             
            Personal aux = p.get(0);
            aux.setId(r.getId());
            aux.setNombre(r.getNombre());
            aux.setApellidos(r.getApellidos());
            aux.setTelefono(r.getTelefono());
            aux.setSeccion(r.getSeccion());
                   
            session.update(aux);
            t.commit();
            session.close();
    }
}
