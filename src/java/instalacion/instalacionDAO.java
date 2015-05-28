/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package instalacion;

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
public class instalacionDAO implements Serializable{
     private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar
    public void insert(instalacion reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into instalacion (tipo_instalacion,nombre_instalacion) values (?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getTipoInstalacion());
            sentence.setString(2, reg.getNombreInstalacion());



            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en instalacionDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en  instalacionDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en  instalacionDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en  instalacionDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en instalacionDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en  instalacionDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar
    public Collection<instalacion> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<instalacion> list = new ArrayList<instalacion>();

        try {
            String sql = "select * from instalacion";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                instalacion reg = new  instalacion();
                reg.setTipoInstalacion(result.getInt("tipo_instalacion"));
                reg.setNombreInstalacion(result.getString("nombre_instalacion"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en instalacionDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en instalacionDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en instalacionDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en instalacionDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en instalacionDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en instalacionDAO, getAll() : " + ex);
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
     // obtener por id del gas
    public instalacion findbyTipoInst(int tipoInst) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        instalacion reg = null;

        try {
            String sql = "select * from instalacion where tipo_instalacion=?";
            sentence = conexion.prepareStatement(sql);
             
            sentence.setInt(1, tipoInst);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */

                reg = new instalacion();
                /* obtener resultset */
                reg.setTipoInstalacion(result.getInt("tipo_instalacion"));
                reg.setNombreInstalacion(result.getString("nombre_instalacion"));

            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en instalacionDAO, findbyIdinstalacion() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en instalacionDAO, findbyIdinstalacion() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en instalacionDAO, findbyIdinstalacion() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en instalacionDAO, findbyIdinstalacion() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en instalacionDAO, findbyIdinstalacion() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en instalacionDAO, findbyIdinstalacion() : " + ex);
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
    public void delete(int tipoInstalacion) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from instalacion where tipo_instalacion=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, tipoInstalacion);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en instalacionDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en instalacionDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en instalacionDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en instalacionDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en instalacionDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en instalacionDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar
    public void update(instalacion inst) {

        PreparedStatement sentence = null;

        try {
            String sql = "update instalacion set nombre_instalacion=? where tipo_instalacion=?";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setString(1, inst.getNombreInstalacion());
            sentence.setInt(2, inst.getTipoInstalacion());

 

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en instalacionDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en instalacionDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en instalacionDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en instalacionDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en instalacionDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en instalacionDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
