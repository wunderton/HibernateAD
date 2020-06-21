/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import apphibernate.Clientes;
import apphibernate.Concesionario;
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
public class concesionarioControler {
    
 public  void concesionarioControler(Concesionario c) {
        
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
    public static void leerConcesionario(ArrayList<Concesionario>arrayConcesionario){
    
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        //FROM POJO 
        Query query = session.createQuery("FROM Concesionario");
        List<Concesionario> listDatos = query.list();
        
        listDatos.forEach((Concesionario datos) -> {   
            Concesionario d = new Concesionario();
            d = datos;
            arrayConcesionario.add(d);
         });
        session.close();
    
    
    }
    public static void eliminarConcesionario(Concesionario r){
        
       Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        List<Concesionario> p = session.createQuery("FROM Concesionario WHERE id='"+r.getId()+"'").list();
        Concesionario aux = p.get(0);
        aux.setId(r.getId());
        aux.setNombre(r.getNombre());
        aux.setDireccion(r.getDireccion());
        aux.setTelefono(r.getTelefono());
        aux.setLocalidad(r.getLocalidad());
 
        session.beginTransaction();
        session.delete(aux);

        session.getTransaction().commit();
            
        session.close();
    }
    
    public static void modificarConcesionario(Concesionario r){
        Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
            Session session;
            session = factory.openSession();
            Transaction t = session.beginTransaction();

             List<Concesionario> p = session.createQuery("FROM Concesionario WHERE id='"+r.getId()+"'").list();
             
            Concesionario aux = p.get(0);
            aux.setId(r.getId());
            aux.setNombre(r.getNombre());
            aux.setDireccion(r.getDireccion());
            aux.setTelefono(r.getTelefono());
            aux.setLocalidad(r.getLocalidad());
                   
            session.update(aux);
            t.commit();
            session.close();
    }
}
