public class SituacionFinal {
    private final String estado;
    private final int inasistencias;

    public SituacionFinal(String estado, int inasistencias) {
        this.estado = estado;
        this.inasistencias = inasistencias;
    }

    public String getEstado() {
        return estado;
    }

    public int getInasistencias() {
        return inasistencias;
    }
}
