/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conducto;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class conducto implements Serializable{
    public int idConducto;
    public int idEdificio;
    public int cantDeptoConducto;
    public String selloConducto;

    public String condSombrete;
    public String secciones;
    public String conInterior;
    public String relacionLados;
    public String pruebaTiro;
    public String tomaAire;
    public String materialidad;
    public String observaciones;
    public String sello;
    
    public conducto() {
    }

    public int getIdConducto() {
        return idConducto;
    }

    public void setIdConducto(int idConducto) {
        this.idConducto = idConducto;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    
    
    public int getCantDeptoConducto() {
        return cantDeptoConducto;
    }

    public void setCantDeptoConducto(int cantDeptoConducto) {
        this.cantDeptoConducto = cantDeptoConducto;
    }

    public String getSelloConducto() {
        return selloConducto;
    }

    public void setSelloConducto(String selloConducto) {
        this.selloConducto = selloConducto;
    }

    public String getCondSombrete() {
        return condSombrete;
    }

    public void setCondSombrete(String condSombrete) {
        this.condSombrete = condSombrete;
    }

    public String getSecciones() {
        return secciones;
    }

    public void setSecciones(String secciones) {
        this.secciones = secciones;
    }

    public String getConInterior() {
        return conInterior;
    }

    public void setConInterior(String conInterior) {
        this.conInterior = conInterior;
    }

    public String getRelacionLados() {
        return relacionLados;
    }

    public void setRelacionLados(String relacionLados) {
        this.relacionLados = relacionLados;
    }

    public String getPruebaTiro() {
        return pruebaTiro;
    }

    public void setPruebaTiro(String pruebaTiro) {
        this.pruebaTiro = pruebaTiro;
    }

    public String getTomaAire() {
        return tomaAire;
    }

    public void setTomaAire(String tomaAire) {
        this.tomaAire = tomaAire;
    }

    public String getMaterialidad() {
        return materialidad;
    }

    public void setMaterialidad(String materialidad) {
        this.materialidad = materialidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }
    
    
}

