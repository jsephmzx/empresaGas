/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package administrador;

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
public class administradorDAO implements Serializable{
     private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar
public void insert(administrador reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into administrador (id_edificio,nombre_admin,telefono_admin,rut_admin,email_admin) values (?,?,?,?,?)";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, reg.getIdEdificio());
            sentence.setString(2, reg.getNombreAdmin());
            sentence.setString(3, reg.getTelefonoAdmin());
            sentence.setString(4, reg.getRutAdmin());
            sentence.setString(5, reg.getEmailAdmin());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en administradorDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en administradorDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en administradorDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en administradorDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en administradorDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en administradorDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar
    public Collection<administrador> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<administrador> list = new ArrayList<administrador>();

        try {
            String sql = "select * from administrador";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                administrador reg = new administrador();
                reg.setIdAdmin(result.getInt("id_admin"));
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setNombreAdmin(result.getString("nombre_admin"));
                reg.setTelefonoAdmin(result.getString("telefono_admin"));
                reg.setRutAdmin(result.getString("rut_admin"));
                reg.setEmailAdmin(result.getString("email_admin"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en administradorDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en administradorDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en administradorDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en administradorDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en administradorDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en administradorDAO, getAll() : " + ex);
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
    // obtener por id del edificio en administrador
    public administrador findbyIdEdificio(int idEdificio) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        administrador reg = null;

        try {
            String sql = "select * from administrador where id_edificio=?";
            sentence = conexion.prepareStatement(sql);
             
            sentence.setInt(1, idEdificio);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new administrador();
                /* obtener resultset */
                reg.setIdEdificio(result.getInt("id_edificio"));
                reg.setIdAdmin(result.getInt("id_admin"));
                reg.setNombreAdmin(result.getString("nombre_admin"));
                reg.setTelefonoAdmin(result.getString("telefono_admin"));
                reg.setRutAdmin(result.getString("rut_admin"));
                reg.setEmailAdmin(result.getString("email_admin"));

            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en AdministradorDAO, findbyIdAdmin() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en AdministradorDAO, findbyIdAdmin() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en AdministradorDAO, findbyIdAdmin() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en AdministradorDAO, findbyIdAdmin() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en AdministradorDAO, findbyIdAdmin() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en AdministradorDAO, findbyIdAdmin() : " + ex);
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
    public void delete(int id_admin) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from administrador id_admin=?";
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_admin);


            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en administradorDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en administradorDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en administradorDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en administradorDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en administradorDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en administradorDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar
    public void update(administrador administrador) {

        PreparedStatement sentence = null;

        try {
            String sql = "update administrador set id_edificio=?,nombre_admin=?,telefono_admin=?,rut_admin=?,email_admin=? where id_admin=?";

            sentence = conexion.prepareStatement(sql);
            
            sentence.setInt(1, administrador.getIdEdificio());
            sentence.setString(2, administrador.getNombreAdmin());
            sentence.setString(3, administrador.getTelefonoAdmin());
            sentence.setString(4, administrador.getRutAdmin());
            sentence.setString(5, administrador.getEmailAdmin());
            sentence.setInt(6, administrador.getIdAdmin());
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en administradorDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en administradorDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en administradorDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en administradorDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en administradorDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en administradorDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
