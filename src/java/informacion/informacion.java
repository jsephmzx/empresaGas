/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package informacion;

/**
 *
 * @author Joseph
 */
public class informacion {
    public int idInformacion;
    public int idDepto;
    public String observacion;
    public String tipoArtefacto;
    public String nombreDueno;

    public informacion() {
    }

    
    
    public int getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(int idInformacion) {
        this.idInformacion = idInformacion;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoArtefacto() {
        return tipoArtefacto;
    }

    public void setTipoArtefacto(String tipoArtefacto) {
        this.tipoArtefacto = tipoArtefacto;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }
    
}
