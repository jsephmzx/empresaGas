/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package departamento;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class departamento implements Serializable{
    public int idDepartamento;
    public int idConductos;
    public int cantConductos;
    public String descripcion;
    public String selloDepartamento;
    public int numDepartamento;
    public int info;
    public String observacion;
    public String Propietario;
    public int idEdificio;

    

    public departamento() {
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }


    public int getCantConductos() {
        return cantConductos;
    }

    public void setCantConductos(int cantConductos) {
        this.cantConductos = cantConductos;
    }

    public int getIdConductos() {
        return idConductos;
    }

    public void setIdConductos(int idConductos) {
        this.idConductos = idConductos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSelloDepartamento() {
        return selloDepartamento;
    }

    public void setSelloDepartamento(String selloDepartamento) {
        this.selloDepartamento = selloDepartamento;
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }
    
    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }
    
    
}
