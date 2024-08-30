public class SituacionFinal {
    private String estado;
    private int inasistencias;

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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setInasistencias(int inasistencias) {
        this.inasistencias = inasistencias;
    }
}
