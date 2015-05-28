/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diagnostico;

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
 * @author Joseph
 */
public class diagnosticoDAO {
    public Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void insert(diagnostico reg){
        PreparedStatement sentence = null;
        try{
           String sql ="insert into diagnostico (id_departamento,detalle,obs_detalle) values (?,?,?)";
           sentence = conexion.prepareStatement(sql);
           sentence.setInt(1, reg.getIdDepartamento());
           sentence.setString(2, reg.getDetalle());
           sentence.setString(3, reg.getObsDetalle());
           
           sentence.executeUpdate();
        }catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en diagnosticoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en diagnosticoDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en diagnosticoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en diagnosticoDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en diagnosticoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en diagnosticoDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
    
    public Collection<diagnostico> getAll(){
        Statement sentence = null;
        ResultSet result = null;
        Collection<diagnostico> list = new ArrayList<diagnostico>();
        try{
            String sql = "select * from diagnostico";
            sentence = conexion.createStatement();
            result = sentence.executeQuery(sql);
            while(result.next()){
                diagnostico reg = new diagnostico();
                reg.setIdDiagnostico(result.getInt("id_diagnostico"));
                reg.setIdDepartamento(result.getInt("id_departamento"));
                reg.setDetalle(result.getString("detalle"));
                reg.setObsDetalle(result.getString("obs_detalle"));
                
                list.add(reg);
            }
        }catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en diagnosticoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en diagnosticoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en diagnosticoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en diagnosticoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en diagnosticoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en diagnosticoDAO, getAll() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return list;
    }
    
    public diagnostico getByidDepto(int idDepto){
        PreparedStatement sentence = null;
        ResultSet result = null;
        diagnostico reg = null;
         try{
             String sql = "select * from diagnostico where  id_departamento=?";
             sentence = conexion.prepareStatement(sql);
             sentence.setInt(1, idDepto);
             result = sentence.executeQuery();
             while(result.next()){
                reg = new diagnostico();
                reg.setIdDiagnostico(result.getInt("id_diagnostico"));
                reg.setIdDepartamento(result.getInt("id_departamento"));
                reg.setDetalle(result.getString("detalle"));
                reg.setObsDetalle(result.getString("obs_detalle"));
             }
         }catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en diagnosticoDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en diagnosticoDAO, getByidDepto() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en diagnosticoDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en diagnosticoDAO, getByidDepto() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en diagnosticoDAO, getByidDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en diagnosticoDAO, getByidDepto() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return reg;
    }
}
