import java.util.*;

public class Universidad {
    private static final List<Carrera> carreras = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarSistema();

        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea
            switch (opcion) {
                case 1:
                    matricularAlumno(scanner);
                    break;
                case 2:
                    inscribirMateria(scanner);
                    break;
                case 3:
                    registrarSituacionFinal(scanner);
                    break;
                case 4:
                    mostrarAlumnosPorCarreraYMateria(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void inicializarSistema() {
        Carrera carrera1 = new Carrera("Ingeniería en Sistemas", 5, "Dr. Perez", 1500.0, 800.0);
        Carrera carrera2 = new Carrera("Licenciatura en Matemáticas", 4, "Dra. Gomez", 1200.0, 600.0);

        Profesor profesor1 = new Profesor("Ing. Juan Rodriguez", "12345678");
        Profesor profesor2 = new Profesor("Lic. Maria Lopez", "87654321");

        Materia materia1 = new Materia("Programación", 1, 1, profesor1);
        Materia materia2 = new Materia("Base de Datos", 2, 2, profesor1);

        Materia materia3 = new Materia("Álgebra", 1, 1, profesor2);
        Materia materia4 = new Materia("Cálculo", 2, 2, profesor2);

        carrera1.agregarMateria(materia1);
        carrera1.agregarMateria(materia2);

        carrera2.agregarMateria(materia3);
        carrera2.agregarMateria(materia4);

        carreras.add(carrera1);
        carreras.add(carrera2);
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú de Opciones:");
        System.out.println("1. Matricular Alumno a una Carrera");
        System.out.println("2. Inscribir Alumno a una Materia");
        System.out.println("3. Registrar Situación Final de una Materia");
        System.out.println("4. Mostrar Alumnos por Carrera y Materia");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void matricularAlumno(Scanner scanner) {
        System.out.println("Ingrese el nombre del alumno:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el DNI del alumno:");
        String dni = scanner.nextLine();
        int legajo = leerEntero(scanner, "Ingrese el número de legajo: ");

        // Verificar que el DNI y el número de legajo no estén ya en uso
        if (dniExistente(dni) || legajoExistente(legajo)) {
            System.out.println("Ya existe un alumno con ese DNI o número de legajo.");
            return;
        }

        System.out.println("Seleccione la carrera:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
        int seleccionCarrera = leerEntero(scanner, "Seleccione una opción: ");

        Carrera carreraSeleccionada = carreras.get(seleccionCarrera - 1);
        Alumno nuevoAlumno = new Alumno(nombre, dni, legajo);
        carreraSeleccionada.agregarAlumno(nuevoAlumno);

        System.out.println("Alumno matriculado exitosamente en " + carreraSeleccionada.getNombre());
    }

    // Métodos adicionales para verificar si el DNI o el número de legajo ya existen
    private static boolean dniExistente(String dni) {
        for (Carrera carrera : carreras) {
            for (Alumno alumno : carrera.getAlumnos()) {
                if (alumno.getDni().equals(dni)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean legajoExistente(int legajo) {
        for (Carrera carrera : carreras) {
            for (Alumno alumno : carrera.getAlumnos()) {
                if (alumno.getLegajo() == legajo) {
                    return true;
                }
            }
        }
        return false;
    }


    private static int leerEntero(Scanner scanner, String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea restante
                break;
            } else {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.next();  // Consumir la entrada no válida
            }
        }
        return numero;
    }

    private static void inscribirMateria(Scanner scanner) {
        int legajo = leerEntero(scanner, "Ingrese el número de legajo del alumno: ");

        System.out.println("Seleccione la carrera:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
        int seleccionCarrera = leerEntero(scanner, "Seleccione una opción: ");

        Carrera carreraSeleccionada = carreras.get(seleccionCarrera - 1);
        Alumno alumno = carreraSeleccionada.buscarAlumno(legajo);

        if (alumno != null) {
            System.out.println("Seleccione la materia:");
            for (int i = 0; i < carreraSeleccionada.getMaterias().size(); i++) {
                System.out.println((i + 1) + ". " + carreraSeleccionada.getMaterias().get(i).getNombre());
            }
            int seleccionMateria = leerEntero(scanner, "Seleccione una opción: ");

            Materia materiaSeleccionada = carreraSeleccionada.getMaterias().get(seleccionMateria - 1);

            if (alumno.estaInscritoEn(materiaSeleccionada)) {
                System.out.println("El alumno ya está inscrito en " + materiaSeleccionada.getNombre());
            } else {
                alumno.inscribirMateria(materiaSeleccionada);
                System.out.println("Alumno inscrito exitosamente en " + materiaSeleccionada.getNombre());
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void registrarSituacionFinal(Scanner scanner) {
        System.out.println("Ingrese el número de legajo del alumno:");
        int legajo = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        System.out.println("Seleccione la carrera:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
        int seleccionCarrera = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        Carrera carreraSeleccionada = carreras.get(seleccionCarrera - 1);
        Alumno alumno = carreraSeleccionada.buscarAlumno(legajo);

        if (alumno != null) {
            System.out.println("Seleccione la materia:");
            for (int i = 0; i < carreraSeleccionada.getMaterias().size(); i++) {
                System.out.println((i + 1) + ". " + carreraSeleccionada.getMaterias().get(i).getNombre());
            }
            int seleccionMateria = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            Materia materiaSeleccionada = carreraSeleccionada.getMaterias().get(seleccionMateria - 1);

            System.out.println("Ingrese la situación final (Aprobado/Desaprobado):");
            String estado = scanner.nextLine();

            System.out.println("Ingrese la cantidad de inasistencias:");
            int inasistencias = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            SituacionFinal situacionFinal = new SituacionFinal(estado, inasistencias);
            alumno.registrarSituacionFinal(materiaSeleccionada, situacionFinal);

            System.out.println("Situación final registrada exitosamente para " + materiaSeleccionada.getNombre());
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void mostrarAlumnosPorCarreraYMateria(Scanner scanner) {
        System.out.println("Seleccione la carrera:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
        int seleccionCarrera = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        Carrera carreraSeleccionada = carreras.get(seleccionCarrera - 1);

        System.out.println("Seleccione la materia:");
        for (int i = 0; i < carreraSeleccionada.getMaterias().size(); i++) {
            System.out.println((i + 1) + ". " + carreraSeleccionada.getMaterias().get(i).getNombre());
        }
        int seleccionMateria = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        Materia materiaSeleccionada = carreraSeleccionada.getMaterias().get(seleccionMateria - 1);

        System.out.println("Alumnos inscritos en " + materiaSeleccionada.getNombre() + ":");
        for (Alumno alumno : carreraSeleccionada.getAlumnos()) {
            if (alumno != null) {
                alumno.mostrarMaterias();
            }
        }
    }
}
