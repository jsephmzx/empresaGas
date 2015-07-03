/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectoDepto;

import artefacto.artefacto;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Joseph
 */
public class defectoDeptoDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void insert(defectoDepto reg) {
        PreparedStatement sentence = null;

        try {
            String sql = "insert into defecto_depto (id_depto, defecto_depto) values(?,?)";
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdDepto());
            sentence.setString(2, reg.getDefectoDepto());
            

            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en defetoDeptoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en defetoDeptoDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en defetoDeptoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en defetoDeptoDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en defetoDeptoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en defetoDeptoDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
    
    public void delete(int idDefectoDepto) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from defecto_depto where id_defecto_depto=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, idDefectoDepto);
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en defetoDeptoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en defetoDeptoDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en defetoDeptoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en defetoDeptoDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en defetoDeptoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en defetoDeptoDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
    
    public void update(defectoDepto defectoDepto) {

        PreparedStatement sentence = null;

        try {
            String sql = "update defecto_depto set id_depto= ?,defecto_depto = ? where id_defecto_depto=?";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, defectoDepto.getIdDepto());
            sentence.setString(2, defectoDepto.getDefectoDepto());
            sentence.setInt(3, defectoDepto.getIdDefectoDepto());
            
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en defetoDeptoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en defetoDeptoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en defetoDeptoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en defetoDeptoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en defetoDeptoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en defetoDeptoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
    
    public Collection<defectoDepto> getByidDepto(int idDepto) {
        Collection<defectoDepto> list = new ArrayList<defectoDepto>();
        PreparedStatement sentence = null;
        ResultSet result = null;
        try {
            String sql = "select * from defecto_depto where id_depto=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idDepto);
            result = sentence.executeQuery();
            while(result.next()){
               defectoDepto reg = new defectoDepto();
               reg.setIdDefectoDepto(result.getInt("id_defecto_depto"));
               reg.setIdDepto(result.getInt("id_depto"));
               reg.setDefectoDepto(result.getString("defecto_depto"));
               
               list.add(reg);
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en defetoDeptoDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en defetoDeptoDAO, getByidDepto() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en defetoDeptoDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en defetoDeptoDAO, getByidDepto() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en defetoDeptoDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en defetoDeptoDAO, getByidDepto() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

        return list;
    }
}
