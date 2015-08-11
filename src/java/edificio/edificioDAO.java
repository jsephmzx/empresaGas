/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificio;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Natalia
 */
public class edificioDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  Agregar

    public void insert(edificio reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into edificio (id_edificio,id_gas,id_empresa,nombre_ejecutivo,rut_edificio,nombre_edificio,ano_edificio,direccion_edificio,telefono_edificio,sello_edificio,norma_aplicada,cant_departamentos,cant_casas,cant_locales,cant_areas,cant_lavanderias,cant_conductos,cant_calderas,cant_pisos,potencia_real,potencia_estimada,id_usuario,tipo_cliente,existencia_conductos,fecha_vencimiento,gas_local) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdEdificio());
            sentence.setInt(2, reg.getIdGas());
            sentence.setInt(3, reg.getIdEmpresa());
            sentence.setString(4, reg.getNombreEjecutivo());
            sentence.setString(5, reg.getRutEdificio());
            sentence.setString(6, reg.getNombreEdificio());
            sentence.setInt(7, reg.getAnoEdificio());
            sentence.setString(8, reg.getDireccionEdificio());
            sentence.setString(9, reg.getTelefonoEdificio());
            sentence.setString(10, reg.getSelloEdificio());
            sentence.setString(11, reg.getNormaAplicada());
            sentence.setInt(12, reg.getCantDepartamentos());
            sentence.setInt(13, reg.getCantCasas());
            sentence.setInt(14, reg.getCantLocales());
            sentence.setInt(15, reg.getCantAreas());
            sentence.setInt(16, reg.getCantLavanderias());
            sentence.setInt(17, reg.getCantConductos());
            sentence.setInt(18, reg.getCantCalderas());
            sentence.setInt(19, reg.getCantPisos());
            sentence.setInt(20, reg.getPotenciaReal());
            sentence.setInt(21, reg.getPotenciaEstimada());
            sentence.setInt(22, reg.getIdUsuario());
            sentence.setString(23, reg.getTipoCliente());
            sentence.setString(24, reg.getExistenciaConductos());
            sentence.setString(25, reg.getFechaVencimiento());
            sentence.setString(26, reg.getGasLocal());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  Consultar por edificio

    public Collection<edificio> getAll() {
        Statement sentence = null;
        ResultSet result = null;

        Collection<edificio> list = new ArrayList<edificio>();

        try {
            String sql = "select * from edificio";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                edificio reg = new edificio();
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setIdGas(result.getInt("id_gas"));
                reg.setIdEmpresa(result.getInt("id_empresa"));
                reg.setNombreEjecutivo(result.getString("nombre_ejecutivo"));
                reg.setRutEdificio(result.getString("rut_edificio"));
                reg.setNombreEdificio(result.getString("nombre_edificio"));
                reg.setAnoEdificio(result.getInt("ano_edificio"));
                reg.setDireccionEdificio(result.getString("direccion_edificio"));
                reg.setTelefonoEdificio(result.getString("telefono_edificio"));
                reg.setSelloEdificio(result.getString("sello_edificio"));
                reg.setNormaAplicada(result.getString("norma_aplicada"));
                reg.setCantDepartamentos(result.getInt("cant_departamentos"));
                reg.setCantCasas(result.getInt("cant_casas"));
                reg.setCantLocales(result.getInt("cant_locales"));
                reg.setCantAreas(result.getInt("cant_areas"));
                reg.setCantLavanderias(result.getInt("cant_lavanderias"));
                reg.setCantConductos(result.getInt("cant_conductos"));
                reg.setCantCalderas(result.getInt("cant_calderas"));
                reg.setCantPisos(result.getInt("cant_pisos"));
                reg.setPotenciaReal(result.getInt("potencia_real"));
                reg.setPotenciaEstimada(result.getInt("potencia_estimada"));
                reg.setIdUsuario(result.getInt("id_usuario"));
                reg.setTipoCliente(result.getString("tipo_cliente"));
                reg.setExistenciaConductos(result.getString("existencia_conductos"));
                reg.setFechaVencimiento(result.getString("fecha_vencimiento"));
                reg.setGasLocal(result.getString("gas_local"));
                list.add(reg);
               
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, getAll() : " + ex);
        } finally {
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return list;
    }
    

    // obtener por id del edificio
    public edificio findbyIdEdificio(int idEdificio) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        edificio reg = null;

        try {
            String sql = "select * from edificio where id_edificio=?";
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, idEdificio);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new edificio();
                /* obtener resultset */
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setIdGas(result.getInt("id_gas"));
                reg.setIdEmpresa(result.getInt("id_empresa"));
                reg.setNombreEjecutivo(result.getString("nombre_ejecutivo"));
                reg.setNombreEdificio(result.getString("nombre_edificio"));
                reg.setRutEdificio(result.getString("rut_edificio"));
                reg.setAnoEdificio(result.getInt("ano_edificio"));
                reg.setDireccionEdificio(result.getString("direccion_edificio"));
                reg.setTelefonoEdificio(result.getString("telefono_edificio"));
                reg.setSelloEdificio(result.getString("sello_edificio"));
                reg.setNormaAplicada(result.getString("norma_aplicada"));
                reg.setCantDepartamentos(result.getInt("cant_departamentos"));
                reg.setCantCasas(result.getInt("cant_casas"));
                reg.setCantLocales(result.getInt("cant_locales"));
                reg.setCantAreas(result.getInt("cant_areas"));
                reg.setCantLavanderias(result.getInt("cant_lavanderias"));
                reg.setCantConductos(result.getInt("cant_conductos"));
                reg.setCantCalderas(result.getInt("cant_calderas"));
                reg.setCantPisos(result.getInt("cant_pisos"));
                reg.setPotenciaReal(result.getInt("potencia_real"));
                reg.setPotenciaEstimada(result.getInt("potencia_estimada"));
                reg.setIdUsuario(result.getInt("id_usuario"));
                reg.setTipoCliente(result.getString("tipo_cliente"));
                reg.setExistenciaConductos(result.getString("existencia_conductos"));
                reg.setFechaVencimiento(result.getString("fecha_vencimiento"));
                reg.setGasLocal(result.getString("gas_local"));
                
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, findbyIdEdificio() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, findbyIdEdificio() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, findbyIdEdificio() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, findbyIdEdificio() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, findbyIdEdificio() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, findbyIdEdificio() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return reg;
    }

    //Buscar por id
    public edificio findById(edificio edi) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        edificio reg = null;

        try {
            String sql = "select * from edificio where id_edificio=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, edi.getIdEdificio());
            result = sentence.executeQuery();
            while (result.next()) {
                reg = new edificio();
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setIdGas(result.getInt("id_gas"));
                reg.setIdEmpresa(result.getInt("id_empresa"));
                reg.setNombreEjecutivo(result.getString("nombre_ejecutivo"));
                reg.setRutEdificio(result.getString("rut_edificio"));
                reg.setNombreEdificio(result.getString("nombre_edificio"));
                reg.setAnoEdificio(result.getInt("ano_edificio"));
                reg.setDireccionEdificio(result.getString("direccion_edificio"));
                reg.setTelefonoEdificio(result.getString("telefono_edificio"));
                reg.setSelloEdificio(result.getString("sello_edificio"));
                reg.setNormaAplicada(result.getString("norma_aplicada"));
                reg.setCantDepartamentos(result.getInt("cant_departamentos"));
                reg.setCantCasas(result.getInt("cant_casas"));
                reg.setCantLocales(result.getInt("cant_locales"));
                reg.setCantAreas(result.getInt("cant_areas"));
                reg.setCantLavanderias(result.getInt("cant_lavanderias"));
                reg.setCantConductos(result.getInt("cant_conductos"));
                reg.setCantCalderas(result.getInt("cant_calderas"));
                reg.setCantPisos(result.getInt("cant_pisos"));
                reg.setPotenciaReal(result.getInt("potencia_real"));
                reg.setPotenciaEstimada(result.getInt("potencia_estimada"));
                reg.setIdEdificio(result.getInt("id_usuario"));
                reg.setTipoCliente(result.getString("tipo_cliente"));
                reg.setExistenciaConductos(result.getString("existencia_conductos"));
                reg.setFechaVencimiento(result.getString("fecha_vencimiento"));
                reg.setGasLocal(result.getString("gas_local"));

            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return reg;
    }

    public int getLastId() {
        Statement sentence = null;
        ResultSet result = null;
        int lastId = 0;
        try {
            String sql = "select MAX(id_edificio) as id from edificio";
            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);
            while (result.next()) {
                lastId = result.getInt("id");
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, getLastId() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, getLastId() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, getLastId() : " + ex);
        } finally {
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return lastId;
    }

    public ArrayList<String> getNombreEdificio() {

        Statement sentence = null;
        ResultSet result = null;

        ArrayList<String> list = new ArrayList<String>();

        try {
            String sql = "select nombre_edificio,id_edificio from edificio";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                list.add(result.getString("nombre_edificio"));
                list.add(result.getString("id_edificio"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, getAll() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return list;
    }

    public ArrayList<String> getRutEdificio() {

        Statement sentence = null;
        ResultSet result = null;

        ArrayList<String> list = new ArrayList<String>();

        try {
            String sql = "select rut_edificio from edificio";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                list.add(result.getString("rut_edificio"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, getAll() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return list;
    }
//  Eliminar

    public void delete(int id_edificio) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from edificio where id_edificio=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_edificio);
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar

    public void update(edificio edificio) {

        PreparedStatement sentence = null;

        try {
            String sql = "update edificio set id_gas=?,id_empresa = ?,nombre_ejecutivo=?,rut_edificio=?,nombre_edificio=?,ano_edificio=?,direccion_edificio=?,telefono_edificio=?,sello_edificio=?,norma_aplicada=?,cant_departamentos=?,cant_locales=?,cant_casas=?,cant_lavanderias=?,cant_areas=?,cant_conductos=?,cant_calderas=?,cant_pisos=?,potencia_real=?,potencia_estimada=?, id_usuario=?, tipo_cliente =?, existencia_conductos =?, fecha_vencimiento=?,gas_local=? where id_edificio=?";
            System.out.println("en update edificio");
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, edificio.getIdGas());
            sentence.setInt(2, edificio.getIdEmpresa());
            sentence.setString(3, edificio.getNombreEjecutivo());
            sentence.setString(4, edificio.getRutEdificio());
            sentence.setString(5, edificio.getNombreEdificio());
            sentence.setInt(6, edificio.getAnoEdificio());
            sentence.setString(7, edificio.getDireccionEdificio());
            sentence.setString(8, edificio.getTelefonoEdificio());
            sentence.setString(9, edificio.getSelloEdificio());
            sentence.setString(10, edificio.getNormaAplicada());
            sentence.setInt(11, edificio.getCantDepartamentos());
            sentence.setInt(12, edificio.getCantLocales());
            sentence.setInt(13, edificio.getCantCasas());
            sentence.setInt(15, edificio.getCantLavanderias());
            sentence.setInt(15, edificio.getCantAreas());
            sentence.setInt(16, edificio.getCantConductos());
            sentence.setInt(17, edificio.getCantCalderas());
            sentence.setInt(18, edificio.getCantPisos());
            sentence.setInt(19, edificio.getPotenciaReal());
            sentence.setInt(20, edificio.getPotenciaEstimada());
            sentence.setInt(21, edificio.getIdUsuario());
            sentence.setString(22, edificio.getTipoCliente());
            sentence.setString(23, edificio.getExistenciaConductos());
            sentence.setString(24, edificio.getFechaVencimiento());
            sentence.setInt(25, edificio.getIdEdificio());
            sentence.setString(26, edificio.getGasLocal());
            sentence.executeUpdate();
            System.out.println("despues de ejecutar update");
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en edificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en edificioDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en edificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en edificioDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en edificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en edificioDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
