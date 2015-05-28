/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package artefacto;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class artefacto implements Serializable {
    public int idArtefacto;
    public int idDepartamento;
    public String tipoArtefacto;
    public int potenciaArtefacto;

    public artefacto() {
    }

    public int getIdArtefacto() {
        return idArtefacto;
    }

    public void setIdArtefacto(int idArtefacto) {
        this.idArtefacto = idArtefacto;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    

    public String getTipoArtefacto() {
        return tipoArtefacto;
    }

    public void setTipoArtefacto(String tipoArtefacto) {
        this.tipoArtefacto = tipoArtefacto;
    }

    public int getPotenciaArtefacto() {
        return potenciaArtefacto;
    }

    public void setPotenciaArtefacto(int potenciaArtefacto) {
        this.potenciaArtefacto = potenciaArtefacto;
    }
    
}
