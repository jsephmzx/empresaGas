/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diagnostico;

/**
 *
 * @author Joseph
 */
public class diagnostico {
    public int idDiagnostico;
    public int idDepartamento;
    public String detalle;
    public String obsDetalle;

    public diagnostico() {
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getObsDetalle() {
        return obsDetalle;
    }

    public void setObsDetalle(String obsDetalle) {
        this.obsDetalle = obsDetalle;
    }
    
}
