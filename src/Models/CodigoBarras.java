package Models;

import java.time.LocalDate;

public class CodigoBarras extends Base {

    private String valor;
    private LocalDate fechaAsignacion;
    private String observaciones;
    private int tipoId;

    public CodigoBarras(String valor, LocalDate fechaAsignacion, String observaciones, int id, boolean eliminado, int tipoId) {
        super(id, eliminado);
        this.valor = valor;
        this.fechaAsignacion = fechaAsignacion;
        this.observaciones = observaciones;
        this.tipoId = tipoId;
    }

    public CodigoBarras(String valor, int tipoId) {
        super();
        this.valor = valor;
        this.fechaAsignacion = LocalDate.now();
        this.observaciones = "";
        this.tipoId = tipoId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public CodigoBarras() {
        super();
    }

    public String getValor() {
        return valor;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public String toString() {
        return String.format(
                "%-12s: %s%n"
                + "%-12s: %s%n"
                + "%-12s: %s%n"
                + "%-12s: %s%n"
                + "%-12s: %s",
                "Id Codigo", getId(),
                "Valor", valor,
                "Fecha", fechaAsignacion,
                "Observaciones", observaciones,
                "Tipo", TipoCodigoBarras.getTipo(tipoId)
        );
    }

}
