package Sistema_Gym.datos;

import Sistema_Gym.dominio.Cliente;

import java.util.List;

public interface IClienteDAO {
    List<Cliente> listarClientes();
    boolean buscarClientePorDni(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
