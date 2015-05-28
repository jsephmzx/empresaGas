/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package detalleEdificio;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class detalleEdificio implements Serializable{
    public int idEdificio;
    public int tipoInstalacion;
    public String tipoConstruccion;
    public String ciigeAnterior;
    public String ubicacionMedidores;
    public String fechaModificacion;
    public String despiche;

    public detalleEdificio() {
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }


    public int getTipoInstalacion() {
        return tipoInstalacion;
    }

    public void setTipoInstalacion(int tipoInstalacion) {
        this.tipoInstalacion = tipoInstalacion;
    }

   

    public String getTipoConstruccion() {
        return tipoConstruccion;
    }

    public void setTipoConstruccion(String tipoConstruccion) {
        this.tipoConstruccion = tipoConstruccion;
    }

    public String getCiigeAnterior() {
        return ciigeAnterior;
    }

    public void setCiigeAnterior(String ciigeAnterior) {
        this.ciigeAnterior = ciigeAnterior;
    }

    public String getUbicacionMedidores() {
        return ubicacionMedidores;
    }

    public void setUbicacionMedidores(String ubicacionMedidores) {
        this.ubicacionMedidores = ubicacionMedidores;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getDespiche() {
        return despiche;
    }

    public void setDespiche(String despiche) {
        this.despiche = despiche;
    }

}
