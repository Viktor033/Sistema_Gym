package Sistema_Gym.presentacion;

import Sistema_Gym.dominio.Cliente;
import Sistema_Gym.datos.ClienteDAO;
import Sistema_Gym.datos.IClienteDAO;

import java.util.Scanner;

public class Sistema_Gym_App {
    public static void main(String[] args) {
        Sistema_Gym_App();
    }

    private static void Sistema_Gym_App(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos un objeto de la clase clienteDao
        IClienteDAO clienteDao = new ClienteDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            }catch(Exception e){
                System.out.println("ERROR al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Sistema (Gym)
                    1- Listar Clientes
                    2- Buscar Cliente
                    3- Agregar Cliente
                    4- Modificar Cliente
                    5- Eliminar Cliente
                    6- Salir
                    Elige una Opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion,
                                            IClienteDAO clienteDao){
        var salir = false;
        switch(opcion){
            //Listar Clientes
            case 1 -> {
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDao.listarClientes();
                clientes.forEach(System.out::println);
            }
            //Buscar Cliente por DNI
            case 2 -> {
                System.out.println("INgrese el DNI del cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDao.buscarClientePorDni(cliente);
                if(encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente no encontrado: " + cliente);
            }
            //Agregar Cliente
            case 3 -> {
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Dni: ");
                var dni = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(dni, nombre, apellido, telefono, membresia);
                var agregado = clienteDao.agregarCliente(cliente);
                    if(agregado)
                        System.out.println("Cliente agregado: " + cliente);
                    else
                        System.out.println("No se pudo agregar el Cliente: " + cliente);
            }
            //Modificar Cliente
            case 4 ->{
                System.out.println("--- Modificar Cliente ---");
                System.out.print("DNI Cliente: ");
                    var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                    var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                    var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                    var telefono = consola.nextLine();
                System.out.print("Membresia: ");
                    var membresia = Integer.parseInt(consola.nextLine());
                //Creamos el Objerto a Modificar
                var cliente = new Cliente(idCliente, nombre, apellido, telefono, membresia);
                var modificado = clienteDao.modificarCliente(cliente);
                if(modificado)
                    System.out.println("Cliente modificado_ " + cliente);
                else
                    System.out.println("Cliente NO modificado: " + cliente);
            }
            //Eliminar Cliente
            case 5 ->{
                System.out.println("--- Eliminar Cliente ---");
                System.out.println("DNI del Cliente: ");
                    var idCliente = Integer.parseInt(consola.nextLine());
                    var cliente = new Cliente(idCliente);
                    var eliminado= clienteDao.eliminarCliente(cliente);
                        if (eliminado)
                            System.out.println("Cliente Eliminado: " + cliente);
                        else
                            System.out.println("Cliente NO eliminado: " + cliente);
            }

            //Salir
            case 6 -> {
                System.out.println("--- HASTA PRONTO! ---");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida: " + opcion);
        }
        return salir;
    }
}
