/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fecha;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class fecha implements Serializable{
    public int idEdificio;
    public String fechaInspeccion;
    public String fechaPrimera;
    public String fechaSegunda;
    public String fechaCierre;

    public fecha() {
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(String fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }

    public String getFechaPrimera() {
        return fechaPrimera;
    }

    public void setFechaPrimera(String fechaPrimera) {
        this.fechaPrimera = fechaPrimera;
    }

    public String getFechaSegunda() {
        return fechaSegunda;
    }

    public void setFechaSegunda(String fechaSegunda) {
        this.fechaSegunda = fechaSegunda;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
}
