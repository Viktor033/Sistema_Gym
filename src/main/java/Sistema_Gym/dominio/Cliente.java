package Sistema_Gym.dominio;

import java.util.Objects;

public class Cliente {
    private int dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private int membresia;


    public Cliente() {
    }

    public Cliente(int dni) {
        this.dni = dni;
    }

    public Cliente(String nombre, String apellido, String telefono, int membresia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.membresia = membresia;
    }

    public Cliente(int dni, String nombre, String apellido, String telefono, int membresia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.membresia = membresia;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getMembresia() {
        return membresia;
    }

    public void setMembresia(int membresia) {
        this.membresia = membresia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return dni == cliente.dni && membresia == cliente.membresia && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido, telefono, membresia);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", membresia=" + membresia +
                '}';
    }
}