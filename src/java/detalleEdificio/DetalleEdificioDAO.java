/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detalleEdificio;

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
public class DetalleEdificioDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar

    public void insert(detalleEdificio reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into detalleEdificio (id_edificio,tipo_instalacion,tipo_construccion,ciige_anterior,ubicacion_medidores,fecha_modificacion,despiche) values (?,?,?,?,?,?,?)";

            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, reg.getIdEdificio());
            sentence.setInt(2, reg.getTipoInstalacion());
            sentence.setString(3, reg.getTipoConstruccion());
            sentence.setString(4, reg.getCiigeAnterior());
            sentence.setString(5, reg.getUbicacionMedidores());
            sentence.setString(6, reg.getFechaModificacion());
            sentence.setString(7, reg.getDespiche());
            

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleEdificioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleEdificioDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleEdificioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleEdificioDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleEdificioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleEdificioDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar detalle del edificio

    public Collection<detalleEdificio> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<detalleEdificio> list = new ArrayList<detalleEdificio>();

        try {
            String sql = "select * from detalleEdificio";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                detalleEdificio reg = new detalleEdificio();
                reg.setIdEdificio(result.getInt("id_edificio"));            
                reg.setTipoInstalacion(result.getInt("tipo_instalacion"));
                reg.setTipoConstruccion(result.getString("tipo_construccion"));
                reg.setCiigeAnterior(result.getString("ciige_anterior"));
                reg.setUbicacionMedidores(result.getString("ubicacion_medidores"));
                reg.setFechaModificacion(result.getString("fecha_modificacion"));
                reg.setDespiche(result.getString("despiche"));
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleEdificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleEdificioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleEdificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleEdificioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleEdificioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleEdificioDAO, getAll() : " + ex);
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

    // obtener por id del edificio en detalle edificio
    public detalleEdificio findbyIdEdificio(int idEdificio) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        detalleEdificio reg = null;

        try {
            String sql = "select * from detalleEdificio where id_edificio=?";
            sentence = conexion.prepareStatement(sql);
             
            sentence.setInt(1, idEdificio);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new detalleEdificio();
                /* obtener resultset */
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setTipoInstalacion(result.getInt("tipo_instalacion"));
                reg.setTipoConstruccion(result.getString("tipo_construccion"));
                reg.setCiigeAnterior(result.getString("ciige_anterior"));
                reg.setUbicacionMedidores(result.getString("ubicacion_medidores"));
                reg.setFechaModificacion(result.getString("fecha_modificacion"));
                reg.setDespiche(result.getString("despiche"));

            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleEdificioDAO, findbyIdDetalle() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleEdificioDAO, findbyIdDetalle() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleEdificioDAO, findbyIdDetalle() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleEdificioDAO, findbyIdDetalle() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleEdificioDAO, findbyIdDetalle() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleEdificioDAO, findbyIdDetalle() : " + ex);
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
//  eliminar

    public void delete(int id_edificio) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from detalleEdificio where id_edificio=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_edificio);


            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleEdificioDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleEdificioDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleEdificioDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleEdificioDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleEdificioDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleEdificioDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  Actualizar

    public void update(detalleEdificio detalleEd) {

        PreparedStatement sentence = null;

        try {
            String sql = "update detalleEdificio set tipo_instalacion=?,tipo_construccion=?,ciige_anterior=?,ubicacion_medidores=?,fecha_modificacion=?, despiche=? where id_edificio=?";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, detalleEd.getTipoInstalacion());
            sentence.setString(2, detalleEd.getTipoConstruccion());
            sentence.setString(3, detalleEd.getCiigeAnterior());
            sentence.setString(4, detalleEd.getUbicacionMedidores());
            sentence.setString(5, detalleEd.getFechaModificacion());
            sentence.setString(6, detalleEd.getDespiche());
            sentence.setInt(7, detalleEd.getIdEdificio());
            
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleEdificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleEdificioDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleEdificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleEdificioDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleEdificioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleEdificioDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
    
}

