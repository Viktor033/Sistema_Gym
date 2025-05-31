package Sistema_Gym.datos;

import Sistema_Gym.conexion.Conexion;
import Sistema_Gym.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    //LISTAR CLIENTES
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente ORDER BY dni";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getInt("telefono"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("ERROR al listtar Clientes" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("ERROR al cerrar conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    // BUSCAR CLIENTE POR DNI
    @Override
    public boolean buscarClientePorDni(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente WHERE dni = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getDni());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getInt("telefono"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR al recuperar cliente por dni: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("ERROR al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    //AGREGAR CLIENTE
    @Override
    public boolean agregarCliente(Cliente cliente) {

        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO cliente(dni, nombre, apellido, telefono, membresia) "
                + " VALUES(?, ?, ?, ?, ?)"; // ? son parametros posicionales van en el orden de los datos que spñicitamos
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setInt(4, cliente.getTelefono());
            ps.setInt(5, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR al agregar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("ERROR al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    //MODIFICAR CLIENTE
    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, telefono=?, membresia=? " +
                " WHERE dni = ?";
        try{
           ps = con.prepareStatement(sql);
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setInt(4, cliente.getTelefono());
            ps.setInt(5, cliente.getMembresia());
            ps.setInt(6, cliente, getDni());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ERROR al modificar el cliente: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("ERROR al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();



        //BUSCAR POR DNI
//        var cliente1 = new Cliente(35189384);
//        System.out.println("clientes antes de la busqeda: " + cliente1);
//        var encontrado = clienteDao.buscarClientePorDni(cliente1);
//        if(encontrado)
//            System.out.println("* Cliente encontrado: " + cliente1);
//        else
//            System.out.println("No se encontro cliente: " + cliente1.getDni());

        //AGREGAR CLIENTE
        var nuevoCliente = new Cliente(
                Integer.parseInt("35147216"),
                "Carlos",
                "Figueroa",
                Integer.parseInt("123456789"),
                Integer.parseInt("250")
        );

        var agregado = clienteDao.agregarCliente(nuevoCliente);
        if (agregado)
            System.out.println("Cliente agregado: " + nuevoCliente);
        else
            System.out.println("No se agregó el cliente: " + nuevoCliente);

        //Listar Clientes
        System.out.println("*** Listar Clientes ***");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);
    }
}
