/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import apphibernate.Clientes;
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
public class clienteControler {
    
   public  void insertarCliente(Clientes c) {
        
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
    public static void leerClientes(ArrayList<Clientes>arrayClientes){
    
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        //FROM POJO 
        Query query = session.createQuery("FROM Clientes");
        List<Clientes> listDatos = query.list();
        
        listDatos.forEach((Clientes datos) -> {   
            Clientes d = new Clientes();
            d = datos;
            arrayClientes.add(d);
         });
        session.close();
    
    
    }
    public static void eliminarCliente(Clientes r){
        
       Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        session = factory.openSession();
        List<Clientes> p = session.createQuery("FROM Clientes WHERE id='"+r.getId()+"'").list();
        Clientes aux = p.get(0);
        aux.setId(r.getId());
        aux.setNombre(r.getNombre());
        aux.setApellidos(r.getApellidos());
        aux.setTelefono(r.getTelefono());
        aux.setDni(r.getDni());
 
        session.beginTransaction();
        session.delete(aux);

        session.getTransaction().commit();
            
        session.close();
    }
    
    public static void modificarCliente(Clientes r){
        Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
            Session session;
            session = factory.openSession();
            Transaction t = session.beginTransaction();

             List<Clientes> p = session.createQuery("FROM Clientes WHERE id='"+r.getId()+"'").list();
             
            Clientes aux = p.get(0);
            aux.setId(r.getId());
            aux.setNombre(r.getNombre());
            aux.setApellidos(r.getApellidos());
            aux.setTelefono(r.getTelefono());
            aux.setDni(r.getDni());
                   
            session.update(aux);
            t.commit();
            session.close();
    }
}
