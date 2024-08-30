import java.util.HashMap;
import java.util.Map;

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

    public void inscribirMateria(Materia materia) {
        materias.put(materia, null);
    }

    public boolean estaInscritoEn(Materia materia) {
        return materias.containsKey(materia);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public int getLegajo() {
        return legajo;
    }

    public Map<Materia, SituacionFinal> getMaterias() {
        return materias;
    }
}
