/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fecha;

import java.io.Serializable;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
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
public class fechaDAO implements Serializable{
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar
    public void insert(fecha reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into fecha (id_edificio,fecha_inspeccion,fecha_primera,fecha_segunda,fecha_cierre) values (?,?,?,?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdEdificio());
            sentence.setString(2, reg.getFechaInspeccion());
            sentence.setString(3, reg.getFechaPrimera());
            sentence.setString(4, reg.getFechaSegunda());
            sentence.setString(5, reg.getFechaCierre());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en fechaDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en fechaDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en fechaDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en fechaDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en fechaDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en fechaDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar
    public Collection<fecha> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<fecha> list = new ArrayList<fecha>();

        try {
            String sql = "select * from fecha";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                fecha reg = new fecha();
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setFechaInspeccion(result.getString("fecha_inspeccion"));
                reg.setFechaPrimera(result.getString("fecha_primera"));
                reg.setFechaSegunda(result.getString("fecha_segunda"));
                reg.setFechaCierre(result.getString("fecha_cierre"));


                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en fechaDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en fechaDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en fechaDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en fechaDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en fechaDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en fechaDAO, getAll() : " + ex);
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

    // obtener por id de edificio en fecha
    public fecha findbyIdEdificio(int idEdificio) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        fecha reg = null;

        try {
            String sql = "select * from fecha where id_edificio=?";
            sentence = conexion.prepareStatement(sql);
             
            sentence.setInt(1, idEdificio);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new fecha();
                /* obtener resultset */
                reg.setIdEdificio(result.getInt("id_edificio"));                
                reg.setFechaInspeccion(result.getString("fecha_inspeccion"));
                reg.setFechaPrimera(result.getString("fecha_primera"));
                reg.setFechaSegunda(result.getString("fecha_segunda"));
                reg.setFechaCierre(result.getString("fecha_cierre"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en FechaDAO, findbyIdEdificio() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en FechaDAO, findbyIdEdificio() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en FechaDAO, findbyIdEdificio() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en FechaDAO, findbyIdEdificio() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en FechaDAO, findbyIdEdificio() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en FechaDAO, findbyIdEdificio() : " + ex);
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
            String sql = "delete from fecha where id_edificio=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_edificio);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en fechaDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en fechaDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en fechaDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en fechaDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en fechaDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en fechaDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar
    public void update(fecha fecha) {

        PreparedStatement sentence = null;

        try {
            String sql = "update fecha set fecha_inspeccion=?,fecha_primera=?,fecha_segunda=?,fecha_cierre=? where id_edificio=?";

            sentence = conexion.prepareStatement(sql);

           
            sentence.setString(1, fecha.getFechaInspeccion());
            sentence.setString(2, fecha.getFechaPrimera());
            sentence.setString(3, fecha.getFechaSegunda());
            sentence.setString(4, fecha.getFechaCierre());
             sentence.setInt(5, fecha.getIdEdificio());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en fechaDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en fechaDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en fechaDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en fechaDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en fechaDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en fechaDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
