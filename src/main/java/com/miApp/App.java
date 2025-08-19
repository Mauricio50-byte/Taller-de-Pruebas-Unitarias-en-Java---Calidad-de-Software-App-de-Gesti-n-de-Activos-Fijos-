package com.miApp;
import java.io.*;
import java.util.Scanner;

public class App {

    private static final String FILENAME = "datos.txt"; // Nombre del archivo de texto

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        ListaCircularDobleDependencias dependencias = cargarDatosDesdeArchivo();

        int opcion;
        do {
            mostrarMenu(); // Mostramos el menu de cada interaccion del bucle
            opcion = lector.nextInt();
            lector.nextLine(); // Consumir el salto de línea después del número
            
            // Switch para manejar las diferentes opciones del menu
            switch (opcion) {
                case 1:
                    LimpiarPantalla();
                    agregarDependencia(dependencias, lector);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 2:
                    LimpiarPantalla();
                    agregarActivo(dependencias, lector);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 3:
                    LimpiarPantalla();
                    ordenarActivosPorNumero(dependencias);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 4:
                    LimpiarPantalla();
                    ordenarActivosPorIdentificacion(dependencias);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 5:
                    LimpiarPantalla();
                    ordenarActivosPorValorActual(dependencias);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 6:
                    LimpiarPantalla();
                    buscarActivoPorCodigo(dependencias, lector);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 7:
                    LimpiarPantalla();
                    buscarDependenciaConMenosValor(dependencias);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 8:
                    LimpiarPantalla();
                    buscarDependenciaConMayorValor(dependencias);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 9:
                    LimpiarPantalla();
                    buscarDependenciaPorActivo(dependencias, lector);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 10:
                    LimpiarPantalla();
                    buscarDependenciaPorFechaDeCompra(dependencias, lector);
                    Pausa();
                    LimpiarPantalla();
                    break;
                case 0:
                    LimpiarPantalla();
                    System.out.println("Saliendo..."); // mensaje de salida
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0); // Continuar hasta que la opción sea 0 (salir)
        
        // Cuando se cierra la aplicación, guardar los datos en el archivo
        guardarDatosEnArchivo(dependencias);

        lector.close(); // cerramos todas las operaciones del Scanner
    }
    
    // Metodo para guardar los datos en el archivo de texto
    public static void guardarDatosEnArchivo(ListaCircularDobleDependencias dependencias) {
        try (FileWriter dato = new FileWriter(FILENAME)) {
            NodoDependencia current = dependencias.getHead();
            if (current != null) {
                do {
                    Dependencias dependencia = current.dependencia;
                    dato.write("Dependencia: " + dependencia.nombre + "\n");
                    dato.write("Centro de costo: " + dependencia.centroDeCosto + "\n");

                    NodoActivo currentActivo = dependencia.getActivos().getHead();
                    while (currentActivo != null) {
                        Activo activo = currentActivo.activo;
                        dato.write("Activo: " + activo.numeroDeActivo + "\n");
                        dato.write("Descripcion: " + activo.descripcion + "\n");
                        dato.write("Fecha de compra: " + activo.fechaDeCompra.año + "/" + activo.fechaDeCompra.mes + "/" + activo.fechaDeCompra.dia + "\n");
                        dato.write("Documento del responsable: " + activo.documentoDelResponsable + "\n");
                        dato.write("Nombre del responsable: " + activo.nombreDelResponsable + "\n");
                        dato.write("Valor historico: " + activo.valorHistorico + "\n");
                        dato.write("Valor actual: " + activo.valorActual + "\n");
                        dato.write("Ubicacion especifica: " + activo.ubicacionEspecifica + "\n");
                        currentActivo = currentActivo.next;
                    }

                    dato.write("\n"); // Separador entre dependencias
                    current = current.next;
                } while (current != dependencias.getHead());
            }

            System.out.println("Datos guardados en el archivo: " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }

    // Metodo para cargar datos desde el archivo al iniciar del programa
    public static ListaCircularDobleDependencias cargarDatosDesdeArchivo() {
        ListaCircularDobleDependencias dependencias = new ListaCircularDobleDependencias();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            Dependencias dependencia = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Dependencia: ")) {
                    String nombre = line.substring("Dependencia: ".length());
                    String centroDeCosto = reader.readLine().substring("Centro de costo: ".length());
                    dependencia = new Dependencias(nombre, centroDeCosto);
                    dependencias.agregar(dependencia);
                } else if (line.startsWith("Activo: ") && dependencia != null) {
                    int numeroDeActivo = Integer.parseInt(line.substring("Activo: ".length()));
                    String descripcion = reader.readLine().substring("Descripcion: ".length());
                    String[] fechaParts = reader.readLine().substring("Fecha de compra: ".length()).split("/");
                    int año = Integer.parseInt(fechaParts[0]);
                    int mes = Integer.parseInt(fechaParts[1]);
                    int dia = Integer.parseInt(fechaParts[2]);
                    FechaDeCompra fechaDeCompra = new FechaDeCompra(año, mes, dia);
                    String documentoDelResponsable = reader.readLine().substring("Documento del responsable: ".length());
                    String nombreDelResponsable = reader.readLine().substring("Nombre del responsable: ".length());
                    double valorHistorico = Double.parseDouble(reader.readLine().substring("Valor historico: ".length()));
                    double valorActual = Double.parseDouble(reader.readLine().substring("Valor actual: ".length()));
                    String ubicacionEspecifica = reader.readLine().substring("Ubicacion especifica: ".length());

                    Activo activo = new Activo(numeroDeActivo, descripcion, fechaDeCompra, documentoDelResponsable, nombreDelResponsable, valorHistorico, valorActual, ubicacionEspecifica);
                    dependencia.agregarActivo(activo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos desde el archivo: " + e.getMessage());
        }

        return dependencias;
    }

    // Metodo para mostrar el menu
    public static void mostrarMenu() {
        System.out.println("Menu de opciones:");
        System.out.println("1. Agregar dependencia");
        System.out.println("2. Agregar activo a una dependencia");
        System.out.println("3. Ordenar activos por numero de activo");
        System.out.println("4. Ordenar activos por documento del responsable");
        System.out.println("5. Ordenar activos por valor actual");
        System.out.println("6. Buscar activo por codigo");
        System.out.println("7. Buscar dependencia con menor valor total de activos");
        System.out.println("8. Buscar dependencia con mayor valor total de activos");
        System.out.println("9. Buscar dependencia por activo");
        System.out.println("10. Buscar dependencia por fecha de compra");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    // Metodo para agregar una dependencia
    public static void agregarDependencia(ListaCircularDobleDependencias dependencias, Scanner lector) {
        System.out.print("Ingrese el nombre de la dependencia: ");
        String nombre = lector.nextLine();
        System.out.print("Ingrese el centro de costo: ");
        String centroDeCosto = lector.nextLine();
        Dependencias nuevaDependencia = new Dependencias(nombre, centroDeCosto);
        dependencias.agregar(nuevaDependencia);
        System.out.println("Dependencia agregada exitosamente.");
    }

    // Metodo para agregar un activo a una dependencia
    public static void agregarActivo(ListaCircularDobleDependencias dependencias, Scanner lector) {
        System.out.print("Ingrese el nombre de la dependencia: ");
        String nombreDependencia = lector.nextLine();
        NodoDependencia current = dependencias.getHead();
        if (current != null) {
            do {
                if (current.dependencia.nombre.equals(nombreDependencia)) {
                    System.out.print("Ingrese el numero de activo: ");
                    int numeroDeActivo = lector.nextInt();
                    lector.nextLine(); // Consumir salto de línea
                    System.out.print("Ingrese la descripcion: ");
                    String descripcion = lector.nextLine();
                    System.out.print("Ingrese el año de compra: ");
                    int año = lector.nextInt();
                    System.out.print("Ingrese el mes de compra: ");
                    int mes = lector.nextInt();
                    System.out.print("Ingrese el dia de compra: ");
                    int dia = lector.nextInt();
                    lector.nextLine(); // Consumir salto de línea
                    FechaDeCompra fechaDeCompra = new FechaDeCompra(año, mes, dia);
                    System.out.print("Ingrese el documento del responsable: ");
                    String documentoDelResponsable = lector.nextLine();
                    System.out.print("Ingrese el nombre del responsable: ");
                    String nombreDelResponsable = lector.nextLine();
                    System.out.print("Ingrese el valor historico: ");
                    double valorHistorico = lector.nextDouble();
                    System.out.print("Ingrese el valor actual: ");
                    double valorActual = lector.nextDouble();
                    lector.nextLine(); // Consumir salto de línea
                    System.out.print("Ingrese la ubicacion especifica: ");
                    String ubicacionEspecifica = lector.nextLine();

                    Activo nuevoActivo = new Activo(numeroDeActivo, descripcion, fechaDeCompra, documentoDelResponsable, nombreDelResponsable, valorHistorico, valorActual, ubicacionEspecifica);
                    current.dependencia.agregarActivo(nuevoActivo);
                    System.out.println("Activo agregado exitosamente.");
                    return;
                }
                current = current.next;
            } while (current != dependencias.getHead());
        }
        System.out.println("Dependencia no encontrada.");
    }

    // Metodo para ordenar activos por numero de activo
    public static void ordenarActivosPorNumero(ListaCircularDobleDependencias dependencias) {
        NodoDependencia current = dependencias.getHead();
        if (current != null) {
            do {
                ListaDobleActivos activos = current.dependencia.getActivos();
                activos.ordenarPorNumeroDeActivo();
                current = current.next;
            } while (current != dependencias.getHead());
        }
        System.out.println("Activos ordenados por numero de activo.");
    }

    // Metodo para ordenar activos por documento del responsable
    public static void ordenarActivosPorIdentificacion(ListaCircularDobleDependencias dependencias) {
        NodoDependencia current = dependencias.getHead();
        if (current != null) {
            do {
                ListaDobleActivos activos = current.dependencia.getActivos();
                activos.ordenarPorDocumentoDelResponsable();
                current = current.next;
            } while (current != dependencias.getHead());
        }
        System.out.println("Activos ordenados por documento del responsable.");
    }

    // Metodo para ordenar activos por valor actual
    public static void ordenarActivosPorValorActual(ListaCircularDobleDependencias dependencias) {
        NodoDependencia current = dependencias.getHead();
        if (current != null) {
            do {
                ListaDobleActivos activos = current.dependencia.getActivos();
                activos.ordenarPorValorActual();
                current = current.next;
            } while (current != dependencias.getHead());
        }
        System.out.println("Activos ordenados por valor actual.");
    }

    // Metodo para buscar un activo por codigo
    public static void buscarActivoPorCodigo(ListaCircularDobleDependencias dependencias, Scanner lector) {
        System.out.print("Ingrese el numero de activo: ");
        int numeroDeActivo = lector.nextInt();
        lector.nextLine(); // Consumir salto de línea

        NodoDependencia current = dependencias.getHead();
        if (current != null) {
            do {
                NodoActivo currentActivo = current.dependencia.getActivos().getHead();
                while (currentActivo != null) {
                    if (currentActivo.activo.numeroDeActivo == numeroDeActivo) {
                        System.out.println("Activo encontrado:");
                        System.out.println("Numero de activo: " + currentActivo.activo.numeroDeActivo);
                        System.out.println("Descripcion: " + currentActivo.activo.descripcion);
                        System.out.println("Fecha de compra: " + currentActivo.activo.fechaDeCompra.año + "/" + currentActivo.activo.fechaDeCompra.mes + "/" + currentActivo.activo.fechaDeCompra.dia);
                        System.out.println("Documento del responsable: " + currentActivo.activo.documentoDelResponsable);
                        System.out.println("Nombre del responsable: " + currentActivo.activo.nombreDelResponsable);
                        System.out.println("Valor historico: " + currentActivo.activo.valorHistorico);
                        System.out.println("Valor actual: " + currentActivo.activo.valorActual);
                        System.out.println("Ubicacion especifica: " + currentActivo.activo.ubicacionEspecifica);
                        return;
                    }
                    currentActivo = currentActivo.next;
                }
                current = current.next;
            } while (current != dependencias.getHead());
        }
        System.out.println("Activo no encontrado.");
    }

    // Metodo para buscar la dependencia con menor valor total de activos
    public static void buscarDependenciaConMenosValor(ListaCircularDobleDependencias dependencias) {
        NodoDependencia current = dependencias.getHead();
        Dependencias menorDependencia = null;
        double menorValor = Double.MAX_VALUE;

        if (current != null) {
            do {
                double valorTotal = current.dependencia.calcularTotal();
                if (valorTotal < menorValor) {
                    menorValor = valorTotal;
                    menorDependencia = current.dependencia;
                }
                current = current.next;
            } while (current != dependencias.getHead());
        }

        if (menorDependencia != null) {
            System.out.println("Dependencia con menor valor total de activos:");
            System.out.println("Nombre: " + menorDependencia.nombre);
            System.out.println("Centro de costo: " + menorDependencia.centroDeCosto);
            System.out.println("Valor total: " + menorValor);
        } else {
            System.out.println("No se encontraron dependencias.");
        }
    }

    // Metodo para buscar la dependencia con mayor valor total de activos
    public static void buscarDependenciaConMayorValor(ListaCircularDobleDependencias dependencias) {
        NodoDependencia current = dependencias.getHead();
        Dependencias mayorDependencia = null;
        double mayorValor = Double.MIN_VALUE;

        if (current != null) {
            do {
                double valorTotal = current.dependencia.calcularTotal();
                if (valorTotal > mayorValor) {
                    mayorValor = valorTotal;
                    mayorDependencia = current.dependencia;
                }
                current = current.next;
            } while (current != dependencias.getHead());
        }

        if (mayorDependencia != null) {
            System.out.println("Dependencia con mayor valor total de activos:");
            System.out.println("Nombre: " + mayorDependencia.nombre);
            System.out.println("Centro de costo: " + mayorDependencia.centroDeCosto);
            System.out.println("Valor total: " + mayorValor);
        } else {
            System.out.println("No se encontraron dependencias.");
        }
    }

    // Metodo para buscar la dependencia que contiene un activo por su numero de activo
    public static void buscarDependenciaPorActivo(ListaCircularDobleDependencias dependencias, Scanner lector) {
        System.out.print("Ingrese el numero de activo: ");
        int numeroDeActivo = lector.nextInt();
        lector.nextLine(); // Consumir salto de línea

        NodoDependencia current = dependencias.getHead();
        if (current != null) {
            do {
                NodoActivo currentActivo = current.dependencia.getActivos().getHead();
                while (currentActivo != null) {
                    if (currentActivo.activo.numeroDeActivo == numeroDeActivo) {
                        System.out.println("Dependencia encontrada:");
                        System.out.println("Nombre: " + current.dependencia.nombre);
                        System.out.println("Centro de costo: " + current.dependencia.centroDeCosto);
                        return;
                    }
                    currentActivo = currentActivo.next;
                }
                current = current.next;
            } while (current != dependencias.getHead());
        }
        System.out.println("Dependencia no encontrada.");
    }

    // Metodo para buscar dependencias por fecha de compra
    public static void buscarDependenciaPorFechaDeCompra(ListaCircularDobleDependencias dependencias, Scanner lector) {
        System.out.print("Ingrese el año de compra: ");
        int año = lector.nextInt();
        System.out.print("Ingrese el mes de compra: ");
        int mes = lector.nextInt();
        System.out.print("Ingrese el dia de compra: ");
        int dia = lector.nextInt();
        lector.nextLine(); // Consumir salto de línea

        FechaDeCompra fechaDeCompra = new FechaDeCompra(año, mes, dia);

        NodoDependencia current = dependencias.getHead();
        boolean encontrada = false;

        if (current != null) {
            do {
                NodoActivo currentActivo = current.dependencia.getActivos().getHead();
                while (currentActivo != null) {
                    if (currentActivo.activo.fechaDeCompra.equals(fechaDeCompra)) {
                        System.out.println("Dependencia encontrada:");
                        System.out.println("Nombre: " + current.dependencia.nombre);
                        System.out.println("Centro de costo: " + current.dependencia.centroDeCosto);
                        encontrada = true;
                    }
                    currentActivo = currentActivo.next;
                }
                current = current.next;
            } while (current != dependencias.getHead());
        }

        if (!encontrada) {
            System.out.println("No se encontraron dependencias con activos comprados en esa fecha.");
        }
    }

    // Metodo para limpiar la pantalla
    public static void LimpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla: " + e.getMessage());
        }
    }

    // Metodo para pausar la ejecucion del programa
    public static void Pausa() {
        System.out.println("Presione enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
