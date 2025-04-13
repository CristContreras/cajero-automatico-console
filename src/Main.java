import java.util.Scanner;

public class Main {
    private static double saldoCliente = 1599.99;
    private static Scanner teclado = new Scanner(System.in);
    private static final String NOMBRE_CLIENTE = "Tony Stark";
    private static final String TIPO_CUENTA = "Corriente";
    private static byte opcionCliente;
    private static String entradaUsuario;


    public static void main(String[] args) {
        mostrarMenu();
    }

    private static void mostrarMenu() {


        String menu = """
                *** Escriba el número de la opción deseada ***\n
                1 - Consultar saldo
                2 - Retirar
                3 - Depositar
                4 - Salir""";
        while (true) {
            println("\n********************************************");
            println("Nombre del Cliente: " + NOMBRE_CLIENTE);
            println("Tipo de cuenta: " + TIPO_CUENTA);
            println("Saldo disponible: $" + String.format("%.2f", saldoCliente));
            println("********************************************\n");
            println(menu);
            System.out.print("\nOpción: ");
            opcionCliente = teclado.nextByte();

            switch (opcionCliente) {
                case 1:
                    println("\n*** Opción Consultar Saldo ***\n");
                    println(consultarSaldo());
                    preguntarSiContinuar();
                    break;
                case 2:
                    println("\n*** Opción Retirar Dinero ***");
                    retirarDinero();
                    break;
                case 3:
                    println("\n*** Opción Depositar ***");
                    depositarDinero();
                    break;
                case 4:
                    salirDelPrograma();
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }

    }

    private static void salirDelPrograma() {
        println("\n********************************************");
        println("Gracias por utilizar nuestros servicios.\nSaliendo del programa...");
        teclado.close();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void depositarDinero() {
        double saldoAnterior = saldoCliente;
        double deposito = 0;
        do {
            println("\n¿Cuanto dinero quiere depositar?");
            System.out.print("$");
            entradaUsuario = teclado.next();
        } while (!validarNumero("double", entradaUsuario));
        deposito = Double.parseDouble(entradaUsuario);
        saldoCliente += deposito;
        println("\nTu dinero fue depositado, cantidad: $" + deposito);
        println("Saldo anterior: $" + saldoAnterior);
        println(consultarSaldo());

        preguntarSiContinuar();
    }

    private static boolean validarNumero(String tipoDato, String entradaUsuario) {

        String mensajeError = "Error: Debe ingresar un número valido.";

        if(tipoDato.equals("byte")){
            try {
                Byte.parseByte(entradaUsuario);
                return true;
            } catch (NumberFormatException e) {
                println(mensajeError);
                return false;
            }
        }else if(tipoDato.equals("int")){
            try {
                Integer.parseInt(entradaUsuario);
                return true;
            } catch (NumberFormatException e) {
                println(mensajeError);
                return false;
            }
        }else if(tipoDato.equals("double")){
            try {
                Double.parseDouble(entradaUsuario);
                return true;
            } catch (NumberFormatException e) {
                println(mensajeError);
                return false;
            }
        }else {
            return false;
        }
    }

    private static void preguntarSiContinuar() {
        boolean opcion = true;
        while (opcion) {
            println("""
                    \n1. Volver al Menú Principal.
                    2. Salir.
                    """);

            do {
                System.out.print("Opción: ");
                entradaUsuario = teclado.next();
            } while (!validarNumero("byte", entradaUsuario));

            opcionCliente = Byte.parseByte(entradaUsuario);

            switch (opcionCliente) {
                case 1:
                    opcion = false;
                    break;
                case 2:
                    salirDelPrograma();
                    break;
                default:
                    println("Error: Opción incorrecta, intentelo nuevamente");
            }
        }
    }

    private static String consultarSaldo() {
        return "Saldo disponible: $" + String.format("%.2f", saldoCliente);
    }

    private static void retirarDinero() {
        double cantidadRetiro = 0;
        println(consultarSaldo());
        do {
            println("Ingrese la cantidad de dinero a retirar");
            System.out.print("$");
            entradaUsuario = teclado.next();
        } while (!validarNumero("double", entradaUsuario));
        cantidadRetiro = Double.parseDouble(entradaUsuario);

        while (cantidadRetiro > saldoCliente) {
            println("ERROR: La cantidad solicitada excede el saldo disponible\n");
            println("Ingrese la cantidad de dinero a retirar");
            System.out.print("$");
            cantidadRetiro = teclado.nextDouble();
        }
        println("Procesando retiro de dinero, monto: $" + cantidadRetiro);
        saldoCliente -= cantidadRetiro;
        println(consultarSaldo());
        preguntarSiContinuar();

    }

    private static void println(String msj) {
        System.out.println(msj);
    }

}
