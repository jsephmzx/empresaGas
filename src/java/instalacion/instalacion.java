/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instalacion;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class instalacion implements Serializable {

    public int tipoInstalacion;
    public String nombreInstalacion;

    public instalacion() {
    }

    public int getTipoInstalacion() {
        return tipoInstalacion;
    }

    public void setTipoInstalacion(int tipoInstalacion) {
        this.tipoInstalacion = tipoInstalacion;
    }

    public String getNombreInstalacion() {
        return nombreInstalacion;
    }

    public void setNombreInstalacion(String nombreInstalacion) {
        this.nombreInstalacion = nombreInstalacion;
    }

    

}
