/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresagas;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class empresagas implements Serializable{
    public int idEmpresa;
    public String empresaGas;

    public empresagas() {
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmpresaGas() {
        return empresaGas;
    }

    public void setEmpresaGas(String empresaGas) {
        this.empresaGas = empresaGas;
    }
    
}
