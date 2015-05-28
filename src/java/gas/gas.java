/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gas;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class gas implements Serializable {
    public int idGas;
    public String tipoGas;


    public gas() {
    }

    public int getIdGas() {
        return idGas;
    }

    public void setIdGas(int idGas) {
        this.idGas = idGas;
    }

    public String getTipoGas() {
        return tipoGas;
    }

    public void setTipoGas(String tipoGas) {
        this.tipoGas = tipoGas;
    }

    
}
