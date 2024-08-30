import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Universidad {
    private List<Carrera> carreras;

    public Universidad() {
        carreras = new ArrayList<>();
        inicializarSistema();
    }

    public void inicializarSistema() {
        Profesor coordinador1 = new Profesor("Jose Luis", "12345678");
        Profesor profesor1 = new Profesor("Hector", "87654321");
        Carrera carrera1 = new Carrera("Ingeniería", 5, 400000.0, 189000.0, coordinador1);
        Materia materia1 = new Materia("ParadigmasYLenguajesDeProgramacionII", 2, 2, profesor1);
        Materia materia2 = new Materia("FísicaII", 2, 2, profesor1);
        carrera1.agregarMateria(materia1);
        carrera1.agregarMateria(materia2);
        carreras.add(carrera1);

        Profesor coordinador2 = new Profesor("Coordinador 2", "87654322");
        Profesor profesor2 = new Profesor("Profesor 2", "12345679");
        Carrera carrera2 = new Carrera("Medicina", 6, 400000.0, 189000.0, coordinador2);
        Materia materia3 = new Materia("Anatomía", 1, 1, profesor2);
        Materia materia4 = new Materia("Biología", 1, 1, profesor2);
        carrera2.agregarMateria(materia3);
        carrera2.agregarMateria(materia4);
        carreras.add(carrera2);
    }

    public void matricularAlumno(Scanner scanner) {
        System.out.println("Ingrese nombre del alumno:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese DNI del alumno:");
        String dni = scanner.nextLine();
        System.out.println("Ingrese legajo del alumno:");
        int legajo = Integer.parseInt(scanner.nextLine());

        System.out.println("Seleccione la carrera:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println(i + ". " + carreras.get(i).getNombre());
        }
        int opcionCarrera = Integer.parseInt(scanner.nextLine());

        Carrera carrera = carreras.get(opcionCarrera);
        Alumno alumno = new Alumno(nombre, dni, legajo);

        if (carrera.getAlumnos().stream().anyMatch(a -> a.getDni().equals(dni) || a.getLegajo() == legajo)) {
            System.out.println("Ya existe un alumno con el mismo DNI o número de legajo en esta carrera.");
        } else {
            carrera.agregarAlumno(alumno);
            System.out.println("Alumno matriculado con éxito.");
        }
    }

    public void inscribirMateria(Scanner scanner) {
        System.out.println("Ingrese número de legajo del alumno:");
        int legajo = Integer.parseInt(scanner.nextLine());

        for (Carrera carrera : carreras) {
            Alumno alumno = carrera.buscarAlumno(legajo);
            if (alumno != null) {
                System.out.println("Seleccione la materia para inscribir al alumno:");
                List<Materia> materias = carrera.getMaterias();
                for (int i = 0; i < materias.size(); i++) {
                    System.out.println(i + ". " + materias.get(i).getNombre());
                }
                int opcionMateria = Integer.parseInt(scanner.nextLine());

                Materia materia = materias.get(opcionMateria);
                if (alumno.estaInscritoEn(materia)) {
                    System.out.println("El alumno ya está inscrito en esta materia.");
                } else {
                    alumno.inscribirMateria(materia);
                    System.out.println("Alumno inscrito en la materia con éxito.");
                }
                return;
            }
        }

        System.out.println("Alumno no encontrado.");
    }

    public void registrarSituacionFinal(Scanner scanner) {
        System.out.println("Ingrese número de legajo del alumno: ");
        int legajo = Integer.parseInt(scanner.nextLine());

        for (Carrera carrera : carreras) {
            Alumno alumno = carrera.buscarAlumno(legajo);
            if (alumno != null) {
                System.out.println("Seleccione la materia: ");
                List<Materia> materias = carrera.getMaterias();
                for (int i = 0; i < materias.size(); i++) {
                    System.out.println(i + ". " + materias.get(i).getNombre());
                }
                int opcionMateria = Integer.parseInt(scanner.nextLine());

                Materia materia = materias.get(opcionMateria);

                if (alumno.estaInscritoEn(materia)) {
                    System.out.println("Ingrese situación final (Regular/Libre/Promocionado): ");
                    String estado = scanner.nextLine();
                    System.out.println("Ingrese cantidad de inasistencias: ");
                    int inasistencias = Integer.parseInt(scanner.nextLine());

                    SituacionFinal situacion = new SituacionFinal(estado, inasistencias);
                    materia.cargarSituacionFinal(alumno, situacion);
                    System.out.println("Situación final registrada con éxito.");
                } else {
                    System.out.println("El alumno no está inscrito en esta materia.");
                }
                return;
            }
        }

        System.out.println("Alumno no encontrado.");
    }

    public void mostrarAlumnosPorCarreraYMateria(Scanner scanner) {
        System.out.println("Seleccione la carrera:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println(i + ". " + carreras.get(i).getNombre());
        }
        int opcionCarrera = Integer.parseInt(scanner.nextLine());

        Carrera carrera = carreras.get(opcionCarrera);
        System.out.println("Seleccione la materia:");
        List<Materia> materias = carrera.getMaterias();
        for (int i = 0; i < materias.size(); i++) {
            System.out.println(i + ". " + materias.get(i).getNombre());
        }
        int opcionMateria = Integer.parseInt(scanner.nextLine());

        Materia materia = materias.get(opcionMateria);

        System.out.println("¿Qué desea visualizar?");
        System.out.println("1. Alumnos inscritos");
        System.out.println("2. Alumnos que han finalizado la materia");

        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1) {
            for (Alumno alumno : carrera.getAlumnos()) {
                if (alumno.estaInscritoEn(materia)) {
                    System.out.println("Alumno: " + alumno.getNombre() + ", Legajo: " + alumno.getLegajo());
                }
            }
        } else if (opcion == 2) {
            for (Alumno alumno : carrera.getAlumnos()) {
                if (alumno.estaInscritoEn(materia)) {
                    SituacionFinal situacion = alumno.getMaterias().get(materia);
                    if (situacion != null) {
                        System.out.println("Alumno: " + alumno.getNombre() + ", Legajo: " + alumno.getLegajo() +
                                ", Situación: " + situacion.getEstado() + ", Inasistencias: " + situacion.getInasistencias());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Universidad universidad = new Universidad();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menú de opciones:");
            System.out.println("1. Matricular Alumno");
            System.out.println("2. Inscribir Alumno en Materia");
            System.out.println("3. Registrar Situación Final");
            System.out.println("4. Mostrar Alumnos por Carrera y Materia");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    universidad.matricularAlumno(scanner);
                    break;
                case 2:
                    universidad.inscribirMateria(scanner);
                    break;
                case 3:
                    universidad.registrarSituacionFinal(scanner);
                    break;
                case 4:
                    universidad.mostrarAlumnosPorCarreraYMateria(scanner);
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
