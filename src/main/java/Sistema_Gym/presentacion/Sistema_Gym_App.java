package Sistema_Gym.presentacion;

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
                salir = ejecutarOpciones(consola, opcion, clienteDAO);
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
        }
        return salir;
    }
}
