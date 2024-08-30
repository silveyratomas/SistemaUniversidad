import java.util.HashMap;
import java.util.Map;

public class Materia {
    private String nombre;
    private int curso;
    private int cuatrimestre;
    private Profesor profesor;
    private Map<Alumno, Integer> asistenciaAlumnos;  // Guarda las inasistencias de cada alumno

    public Materia(String nombre, int curso, int cuatrimestre, Profesor profesor) {
        this.nombre = nombre;
        this.curso = curso;
        this.cuatrimestre = cuatrimestre;
        this.profesor = profesor;
        this.asistenciaAlumnos = new HashMap<>();
    }

    public void registrarAsistencia(Alumno alumno) {
        asistenciaAlumnos.put(alumno, asistenciaAlumnos.getOrDefault(alumno, 0) + 1);
    }

    public void cargarSituacionFinal(Alumno alumno, SituacionFinal situacion) {
        alumno.getMaterias().put(this, situacion);
    }

    public int obtenerInasistencias(Alumno alumno) {
        return asistenciaAlumnos.getOrDefault(alumno, 0);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCurso() {
        return curso;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}
