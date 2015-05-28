/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edificio;

import java.io.Serializable;

/**
 *
 * @author Natalia
 */
public class edificio implements Serializable{
 public int idEdificio;
 public int idGas;
 public int idEmpresa;
 public String nombreEjecutivo;
 public String rutEdificio;
 public String nombreEdificio;
 public int anoEdificio;
 public String direccionEdificio;
 public String telefonoEdificio;
 public String selloEdificio;
 public String normaAplicada;
 public int cantDepartamentos;
 public int cantCasas;
 public int cantLocales;
 public int cantAreas;
 public int cantLavanderias;
 public int cantConductos;
 public int cantCalderas;
 public int cantPisos;
 public int potenciaReal;
 public int potenciaEstimada;
    public edificio() {
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public int getIdGas() {
        return idGas;
    }

    public void setIdGas(int idGas) {
        this.idGas = idGas;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEjecutivo() {
        return nombreEjecutivo;
    }

    public void setNombreEjecutivo(String nombreEjecutivo) {
        this.nombreEjecutivo = nombreEjecutivo;
    }

    public String getRutEdificio() {
        return rutEdificio;
    }

    public void setRutEdificio(String rutEdificio) {
        this.rutEdificio = rutEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public int getAnoEdificio() {
        return anoEdificio;
    }

    public void setAnoEdificio(int anoEdificio) {
        this.anoEdificio = anoEdificio;
    }

    public String getDireccionEdificio() {
        return direccionEdificio;
    }

    public void setDireccionEdificio(String direccionEdificio) {
        this.direccionEdificio = direccionEdificio;
    }

    public String getTelefonoEdificio() {
        return telefonoEdificio;
    }

    public void setTelefonoEdificio(String telefonoEdificio) {
        this.telefonoEdificio = telefonoEdificio;
    }

    public String getSelloEdificio() {
        return selloEdificio;
    }

    public void setSelloEdificio(String selloEdificio) {
        this.selloEdificio = selloEdificio;
    }

    public String getNormaAplicada() {
        return normaAplicada;
    }

    public void setNormaAplicada(String normaAplicada) {
        this.normaAplicada = normaAplicada;
    }

    public int getCantDepartamentos() {
        return cantDepartamentos;
    }

    public void setCantDepartamentos(int cantDepartamentos) {
        this.cantDepartamentos = cantDepartamentos;
    }

    public int getCantCasas() {
        return cantCasas;
    }

    public void setCantCasas(int cantCasas) {
        this.cantCasas = cantCasas;
    }

    public int getCantLocales() {
        return cantLocales;
    }

    public void setCantLocales(int cantLocales) {
        this.cantLocales = cantLocales;
    }

    public int getCantAreas() {
        return cantAreas;
    }

    public void setCantAreas(int cantAreas) {
        this.cantAreas = cantAreas;
    }

    public int getCantLavanderias() {
        return cantLavanderias;
    }

    public void setCantLavanderias(int cantLavanderias) {
        this.cantLavanderias = cantLavanderias;
    }

    public int getCantConductos() {
        return cantConductos;
    }

    public void setCantConductos(int cantConductos) {
        this.cantConductos = cantConductos;
    }

    public int getCantCalderas() {
        return cantCalderas;
    }

    public void setCantCalderas(int cantCalderas) {
        this.cantCalderas = cantCalderas;
    }

    public int getCantPisos() {
        return cantPisos;
    }

    public void setCantPisos(int cantPisos) {
        this.cantPisos = cantPisos;
    }

    public int getPotenciaReal() {
        return potenciaReal;
    }

    public void setPotenciaReal(int potenciaReal) {
        this.potenciaReal = potenciaReal;
    }

    public int getPotenciaEstimada() {
        return potenciaEstimada;
    }

    public void setPotenciaEstimada(int potenciaEstimada) {
        this.potenciaEstimada = potenciaEstimada;
    }
 
}
