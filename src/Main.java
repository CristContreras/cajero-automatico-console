import java.util.Scanner;

public class Main {
    public static double saldoCliente = 1599.99;
    public static Scanner teclado=new Scanner(System.in);
    public static final String NOMBRE_CLIENTE = "Tony Stark";
    public static final String TIPO_CUENTA = "Corriente";
    public static byte opcionCliente;

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {


        String menu = """
                *** Escriba el número de la opción deseada ***\n
                1 - Consultar saldo
                2 - Retirar
                3 - Depositar
                4 - Salir""";
        while (true) {
            mostrarAUsuario("\n********************************************");
            mostrarAUsuario("Nombre del Cliente: " + NOMBRE_CLIENTE);
            mostrarAUsuario("Tipo de cuenta: " + TIPO_CUENTA);
            mostrarAUsuario("Saldo disponible: $" + String.format("%.2f", saldoCliente));
            mostrarAUsuario("********************************************\n");
            mostrarAUsuario(menu);
            System.out.print("\nOpción: ");
            opcionCliente = teclado.nextByte();

            switch (opcionCliente) {
                case 1:
                    mostrarAUsuario("\n*** Opción Consultar Saldo ***\n");
                    mostrarAUsuario(consultarSaldo());
                    preguntarSiContinuar();
                    break;
                case 2:
                    mostrarAUsuario("\n*** Opción Retirar Dinero ***");
                    retirarDinero();
                    break;
                case 3:
                    mostrarAUsuario("\n*** Opción Depositar ***");
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
    public static void salirDelPrograma(){
        mostrarAUsuario("\n********************************************");
        mostrarAUsuario("Gracias por utilizar nuestros servicios.\nSaliendo del programa...");
        teclado.close();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void depositarDinero() {
        double saldoAnterior = saldoCliente;
        double deposito = 0;
        mostrarAUsuario("\n¿Cuanto dinero quiere depositar?");
        System.out.print("$");
        deposito = teclado.nextDouble();
        saldoCliente = saldoCliente + deposito;
        mostrarAUsuario("\nTu dinero fue depositado, cantidad: $" + deposito);
        mostrarAUsuario("Saldo anterior: $"+saldoAnterior);
        mostrarAUsuario(consultarSaldo());
        preguntarSiContinuar();
    }

    public static void preguntarSiContinuar() {
        boolean opcion = true;
        while(opcion){
            mostrarAUsuario("""
                \n1. Volver al Menú Principal.
                2. Salir.
                """);
            System.out.print("Opción: ");

            opcionCliente = teclado.nextByte();
            switch (opcionCliente){
                case 1:
                    opcion=false;
                    break;
                case 2:
                    salirDelPrograma();
                    break;
                default: mostrarAUsuario("Opción incorrecta, intentelo nuevamente");
            }
        }
    }

    public static String consultarSaldo() {
        return "Saldo disponible: $"+String.format("%.2f", saldoCliente);
    }

    public static void retirarDinero() {
        double cantidadRetiro=0;
        mostrarAUsuario(consultarSaldo());
        mostrarAUsuario("Ingrese la cantidad de dinero a retirar");
        System.out.print("$");
        cantidadRetiro = teclado.nextDouble();

        while (cantidadRetiro > saldoCliente) {
            mostrarAUsuario("ERROR: La cantidad solicitada excede el saldo disponible\n");
            mostrarAUsuario("Ingrese la cantidad de dinero a retirar");
            System.out.print("$");
            cantidadRetiro = teclado.nextDouble();
        }
        mostrarAUsuario("Procesando retiro de dinero, monto: $" + cantidadRetiro);
        saldoCliente = saldoCliente - cantidadRetiro;
        mostrarAUsuario(consultarSaldo());
        preguntarSiContinuar();

    }

    public static void mostrarAUsuario(String msj) {
        System.out.println(msj);
    }

}
