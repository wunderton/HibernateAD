package apphibernate;
// Generated 28-ene-2020 20:22:33 by Hibernate Tools 4.3.1



/**
 * Concesionario generated by hbm2java
 */
public class Concesionario  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String direccion;
     private Integer telefono;
     private String localidad;

    public Concesionario() {
    }

    public Concesionario(String nombre, String direccion, Integer telefono, String localidad) {
       this.nombre = nombre;
       this.direccion = direccion;
       this.telefono = telefono;
       this.localidad = localidad;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Integer getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    public String getLocalidad() {
        return this.localidad;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }




}


