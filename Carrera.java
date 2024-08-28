import java.util.*;

public class Carrera {
    private String nombre;
    private int duracion;
    private String coordinador;
    private double precioInscripcion;
    private double precioCuota;
    private List<Alumno> alumnos;
    private List<Materia> materias;

    public Carrera(String nombre, int duracion, String coordinador, double precioInscripcion, double precioCuota) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.coordinador = coordinador;
        this.precioInscripcion = precioInscripcion;
        this.precioCuota = precioCuota;
        this.alumnos = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public Alumno buscarAlumno(int legajo) {
        for (Alumno alumno : alumnos) {
            if (alumno.getLegajo() == legajo) {
                return alumno;
            }
        }
        return null;
    }

    public Materia buscarMateria(String nombreMateria) {
        for (Materia materia : materias) {
            if (materia.getNombre().equalsIgnoreCase(nombreMateria)) {
                return materia;
            }
        }
        return null;
    }
}
