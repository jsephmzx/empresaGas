/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detalleDepto;

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
public class detalleDeptoDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void insert(detalleDepto reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into detalledepto (id_departamento,detalle) values (?,?)";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, reg.getIdDepto());
            sentence.setString(2, reg.getDetalle());
            

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleDeptoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleDeptoDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleDeptoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleDeptoDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleDeptoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleDeptoDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }

    public Collection<detalleDepto> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<detalleDepto> list = new ArrayList<detalleDepto>();

        try {
            String sql = "select * from detalledepto";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                detalleDepto reg = new detalleDepto();
                reg.setIdDetalle(result.getInt("id_detalledepto"));
                reg.setIdDepto(result.getInt("id_departamento"));
                reg.setDetalle(result.getString("detalle"));
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleDeptoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleDeptoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleDeptoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleDeptoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleDeptoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleDeptoDAO, getAll() : " + ex);
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

    public Collection<detalleDepto> getAllById(int idDepto) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<detalleDepto> list = new ArrayList<detalleDepto>();

        try {
            String sql = "select * from detalledepto where id_departamento=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idDepto);
            result = sentence.executeQuery();

            while (result.next()) {
                detalleDepto reg = new detalleDepto();
                reg.setIdDetalle(result.getInt("id_detalledepto"));
                reg.setIdDepto(result.getInt("id_departamento"));
                reg.setDetalle(result.getString("detalle"));
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleDeptoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleDeptoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleDeptoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleDeptoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleDeptoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleDeptoDAO, getAll() : " + ex);
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
    public void delete(int idDetalle) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from detalledepto where id_detalledepto=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, idDetalle);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleDeptoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleDeptoDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleDeptoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleDeptoDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleDeptoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleDeptoDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void update(detalleDepto detalleDepto) {

        PreparedStatement sentence = null;

        try {
            String sql = "update detalledepto set id_departamento = ?, detalle = ? where id_detalledepto=?";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, detalleDepto.getIdDepto());
            sentence.setString(2, detalleDepto.getDetalle());
            sentence.setInt(3, detalleDepto.getIdDetalle());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleDeptoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleDeptoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleDeptoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleDeptoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleDeptoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleDeptoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
