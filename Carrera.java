import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private int duracion;
    private double precioInscripcion;
    private double precioCuota;
    private Profesor coordinador;
    private List<Alumno> alumnos;
    private List<Materia> materias;

    public Carrera(String nombre, int duracion, double precioInscripcion, double precioCuota, Profesor coordinador) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.precioInscripcion = precioInscripcion;
        this.precioCuota = precioCuota;
        this.coordinador = coordinador;
        this.alumnos = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public Alumno buscarAlumno(int legajo) {
        for (Alumno alumno : alumnos) {
            if (alumno.getLegajo() == legajo) {
                return alumno;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public double getPrecioCuota() {
        return precioCuota;
    }

    
    public Profesor getCoordinador() {
        return coordinador;
    }
}
