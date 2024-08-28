import java.util.*;

public class Alumno {
    private String nombre;
    private String dni;
    private int legajo;
    private Map<Materia, SituacionFinal> materias;

    public Alumno(String nombre, String dni, int legajo) {
        this.nombre = nombre;
        this.dni = dni;
        this.legajo = legajo;
        this.materias = new HashMap<>();
    }

    public boolean estaInscritoEn(Materia materia) {
        return materias.containsKey(materia);
    }

    public String getDni(){
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public void inscribirMateria(Materia materia) {
        materias.put(materia, null);
    }

    public void registrarSituacionFinal(Materia materia, SituacionFinal situacion) {
        if (materias.containsKey(materia)) {
            materias.put(materia, situacion);
        } else {
            System.out.println("El alumno no está inscrito en la materia " + materia.getNombre());
        }
    }

    public void mostrarMaterias() {
        for (Map.Entry<Materia, SituacionFinal> entry : materias.entrySet()) {
            Materia materia = entry.getKey();
            SituacionFinal situacion = entry.getValue();
            System.out.println("Materia: " + materia.getNombre() + " - Curso: " + materia.getCurso() +
                    " - Cuatrimestre: " + materia.getCuatrimestre() +
                    " - Profesor: " + materia.getProfesor().getNombre());
            if (situacion != null) {
                System.out.println("Situación final: " + situacion.getEstado() +
                        " - Inasistencias: " + situacion.getInasistencias());
            } else {
                System.out.println("El alumno está cursando esta materia.");
            }
        }
    }
}
