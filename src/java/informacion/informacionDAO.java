/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informacion;

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
 * @author Joseph
 */
public class informacionDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void insert(informacion reg) {
        PreparedStatement sentence = null;
        try {
            String sql = "insert into informacion (id_departamento,observacion,tipo_artefacto,nombre_dueno) values(?,?,?,?)";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, reg.getIdDepto());
            sentence.setString(2, reg.getObservacion());
            sentence.setString(3, reg.getTipoArtefacto());
            sentence.setString(4, reg.getNombreDueno());

            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en informacionDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en informacionDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en informacionDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en informacionDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en informacionDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en informacionDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public Collection<informacion> getAll() {
        Statement sentence = null;
        ResultSet result = null;
        Collection<informacion> list = new ArrayList<informacion>();

        try {
            String sql = "select * from informacion";
            sentence = conexion.createStatement();
            result = sentence.executeQuery(sql);
            while (result.next()) {
                informacion reg = new informacion();
                reg.setIdInformacion(result.getInt("id_informacion"));
                reg.setIdDepto(result.getInt("id_departamento"));
                reg.setObservacion(result.getString("observacion"));
                reg.setTipoArtefacto(result.getString("tipo_artefacto"));
                reg.setNombreDueno(result.getString("nombre_dueno"));

                list.add(reg);
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en informacionDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en informacionDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en informacionDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en informacionDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en informacionDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en informacionDAO, getAll() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return list;
    }

    public void delete(int idInformacion) {
        PreparedStatement sentence = null;

        try {
            String sql = "delete from informacion where id_informacion=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idInformacion);
            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en informacionDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en informacionDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en informacionDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en informacionDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en informacionDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en informacionDAO, delete() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void update(informacion reg) {
        PreparedStatement sentence = null;

        try {
            String sql = "update informacionDAO set id_departamento=?, observacion = ?, tipo_artefacto = ?, nombre_dueno = ?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, reg.getIdDepto());
            sentence.setString(2, reg.getObservacion());
            sentence.setString(3, reg.getTipoArtefacto());
            sentence.setString(4, reg.getNombreDueno());

            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en informacionDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en informacionDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en informacionDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en informacionDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en informacionDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en informacionDAO, update() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public informacion getByidDepto(int idDepto) {
        informacion reg = null;
        PreparedStatement sentence = null;
        ResultSet result = null;
        try {
            String sql = "select * from informacion where id_departamento=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idDepto);
            result = sentence.executeQuery();
            while(result.next()){
               reg = new informacion();
               reg.setIdInformacion(result.getInt("id_informacion"));
               reg.setIdDepto(result.getInt("id_departamento"));
               reg.setObservacion(result.getString("observacion"));
               reg.setTipoArtefacto(result.getString("tipo_artefacto"));
               reg.setNombreDueno(result.getString("nombre_dueno"));
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en informacionDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en informacionDAO, getByidDepto() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en informacionDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en informacionDAO, getByidDepto() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en informacionDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en informacionDAO, getByidDepto() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

        return reg;
    }
    
    public boolean getExitenciaByid(int idDepto) {
        boolean existencia = false;
        informacion reg = null;
        PreparedStatement sentence = null;
        ResultSet result = null;
        try {
            String sql = "select * from informacion where id_departamento=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idDepto);
            result = sentence.executeQuery();
            while(result.next()){
               existencia = true; 
               reg = new informacion();
               reg.setIdInformacion(result.getInt("id_informacion"));
               reg.setIdDepto(result.getInt("id_departamento"));
               reg.setObservacion(result.getString("observacion"));
               reg.setTipoArtefacto(result.getString("tipo_artefacto"));
               reg.setNombreDueno(result.getString("nombre_dueno"));
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en informacionDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en informacionDAO, getByidDepto() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en informacionDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en informacionDAO, getByidDepto() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en informacionDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en informacionDAO, getByidDepto() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

        return existencia;
    }
}
