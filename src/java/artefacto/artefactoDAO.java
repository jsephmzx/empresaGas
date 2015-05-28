/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package artefacto;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import informacion.informacion;
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
public class artefactoDAO implements Serializable{
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar
    public void insert(artefacto reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into artefacto (id_depto,artefacto,potencia) values (?,?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdDepartamento());
            sentence.setString(2, reg.getTipoArtefacto());
            sentence.setInt(3, reg.getPotenciaArtefacto());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en artefactoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en artefactoDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en artefactoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en artefactoDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en artefactoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en artefactoDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar
    public Collection<artefacto> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<artefacto> list = new ArrayList<artefacto>();

        try {
            String sql = "select * from artefacto";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                artefacto reg = new artefacto();
                reg.setIdArtefacto(result.getInt("id_artefacto"));
                reg.setIdDepartamento(result.getInt("id_depto"));
                reg.setTipoArtefacto(result.getString("artefacto"));
                reg.setPotenciaArtefacto(result.getInt("potencia"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en artefactoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en artefactoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en artefactoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en artefactoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en artefactoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en artefactoDAO, getAll() : " + ex);
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
//  eliminar
    public void delete(int id_artefacto) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from artefacto where id_artefacto=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_artefacto);
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en artefactoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en artefactoDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en artefactoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en artefactoDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en artefactoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en artefactoDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar
    public void update(artefacto artefacto) {

        PreparedStatement sentence = null;

        try {
            String sql = "update artefacto set id_depto = ?,artefacto = ?,potencia = ? where id_artefacto=?";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, artefacto.getIdDepartamento());
            sentence.setString(2, artefacto.getTipoArtefacto());
            sentence.setInt(3, artefacto.getPotenciaArtefacto());
            sentence.setInt(4, artefacto.getIdArtefacto());
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en artefactoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en artefactoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en artefactoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en artefactoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en artefactoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en artefactoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
    
     public Collection<artefacto> getByidDepto(int idDepto) {
        Collection<artefacto> list = new ArrayList<artefacto>();
        PreparedStatement sentence = null;
        ResultSet result = null;
        try {
            String sql = "select * from artefacto where id_depto=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idDepto);
            result = sentence.executeQuery();
            while(result.next()){
               artefacto reg = new artefacto();
               reg.setIdArtefacto(result.getInt("id_artefacto"));
               reg.setIdDepartamento(result.getInt("id_depto"));
               reg.setTipoArtefacto(result.getString("artefacto"));
               reg.setPotenciaArtefacto(result.getInt("potencia"));
               list.add(reg);
            }
        } catch (MySQLSyntaxErrorException ex) {
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

        return list;
    }
}
