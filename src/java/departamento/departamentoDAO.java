/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamento;

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
public class departamentoDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  Agregar

    public void insert(departamento reg) {
        System.out.println("en insert");
        System.out.println("id conducto :" + reg.getIdConductos());
        System.out.println("cantidad ductos:" + reg.getCantConductos());
        System.out.println("descripcion :" + reg.getDescripcion());
        System.out.println("sello :" + reg.getSelloDepartamento());
        System.out.println("numero :" + reg.getNumDepartamento());
        PreparedStatement sentence = null;

        try {
            String sql = "insert into departamento (id_conducto,cant_conductos,descripcion,sello_departamento,num_departamento,info) values (?,?,?,?,?,?)";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, reg.getIdConductos());
            sentence.setInt(2, reg.getCantConductos());
            sentence.setString(3, reg.getDescripcion());
            sentence.setString(4, reg.getSelloDepartamento());
            sentence.setInt(5, reg.getNumDepartamento());
            sentence.setInt(6, reg.getInfo());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  buscar  departamento por edificio

    public Collection<departamento> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<departamento> list = new ArrayList<departamento>();

        try {
            String sql = "select * from departamento";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                departamento reg = new departamento();
                reg.setIdDepartamento(result.getInt("id_departamento"));
                reg.setCantConductos(result.getInt("cant_conductos"));
                reg.setIdConductos(result.getInt("id_conducto"));
                reg.setDescripcion(result.getString("descripcion"));
                reg.setSelloDepartamento(result.getString("sello_departamento"));
                reg.setNumDepartamento(result.getInt("num_departamento"));
                reg.setInfo(result.getInt("info"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, getAll() : " + ex);
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

//  obtener todos segun el id de conducto
    public Collection<departamento> getAllById(int idConducto) {
        PreparedStatement sentence = null;
        ResultSet result = null;
        Collection<departamento> list = new ArrayList<departamento>();
        try {
            String sql = "select * from departamento where id_conducto=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idConducto);
            result = sentence.executeQuery();
            while (result.next()) {
                departamento reg = new departamento();
                reg.setIdDepartamento(result.getInt("id_departamento"));
                reg.setIdConductos(result.getInt("id_conducto"));
                reg.setCantConductos(result.getInt("cant_conductos"));
                reg.setDescripcion(result.getString("descripcion"));
                reg.setSelloDepartamento(result.getString("sello_departamento"));
                reg.setNumDepartamento(result.getInt("num_departamento"));
                reg.setInfo(result.getInt("info"));
                list.add(reg);
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, getAll() : " + ex);
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
    public void delete(int id_departamento) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from departamento where id_departamento=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_departamento);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar

    public void update(departamento departamento) {

        PreparedStatement sentence = null;

        try {
            String sql = "update departamento set id_edificio=?,cant_conductos=?,id_conductos=?,descripcion=?,sello_departamento=?,num_departamento=? where id_departamento=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, departamento.getCantConductos());
            sentence.setInt(2, departamento.getIdConductos());
            sentence.setString(3, departamento.getDescripcion());
            sentence.setString(4, departamento.getSelloDepartamento());
            sentence.setInt(5, departamento.getNumDepartamento());
            sentence.setInt(6, departamento.getIdDepartamento());
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void updateInfo(int info, int idDepto) {
        PreparedStatement sentence = null;
        try {
            String sql = "update departamento set info = ? where id_departamento = ?";
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, info);
            sentence.setInt(2, idDepto);
            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, updateInfo() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, updateInfo() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, updateInfo() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, updateInfo() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, updateInfo() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, updateInfo() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void updateSello(int idDepto, String sello) {
        PreparedStatement sentence = null;
        try {
            String sql = "update departamento set sello_departamento = ? where id_departamento = ?";
            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, sello);
            sentence.setInt(2, idDepto);
            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, updateSello() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, updateSello() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, updateSello() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, updateSello() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, updateSello() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, updateSello() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void updateNumberDescription(String description, int number, int idDepto) {
        PreparedStatement sentence = null;
        try {
            String sql = "update departamento set descripcion = ?, num_departamento= ? where id_departamento = ?";
            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, description);
            sentence.setInt(2, number);
            sentence.setInt(3, idDepto);
            sentence.executeUpdate();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, updateNumberDescription() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, updateNumberDescription() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, updateNumberDescription() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, updateNumberDescription() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, updateNumberDescription() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, updateNumberDescription() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public departamento getByIdDepto(int idDepto) {
        PreparedStatement sentence = null;
        ResultSet result = null;
        departamento depto = null;
        try {
            String sql = "select * from departamento where id_departamento=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idDepto);
            result = sentence.executeQuery();
            while (result.next()) {
                depto = new departamento();
                depto.setIdDepartamento(result.getInt("id_departamento"));
                depto.setIdConductos(result.getInt("id_conducto"));
                depto.setCantConductos(result.getInt("cant_conductos"));
                depto.setDescripcion(result.getString("descripcion"));
                depto.setSelloDepartamento(result.getString("sello_departamento"));
                depto.setNumDepartamento(result.getInt("num_departamento"));
                depto.setInfo(result.getInt("info"));

            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, getByIdDepto() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, getByIdDepto() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, getByIdDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, getByIdDepto() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, getByIdDepto() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, getByIdDepto() : " + ex);
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
        return depto;
    }

    public int getLastId() {
        int idDepto = 0;
        Statement sentence = null;
        ResultSet result = null;
        try {
            String sql = "select MAX(id_departamento) as id from departamento";
            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);
            while (result.next()) {
                idDepto = result.getInt("id");
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en departamentoDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en departamentoDAO, getLastId() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en departamentoDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en departamentoDAO, getLastId() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en departamentoDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en departamentoDAO, getLastId() : " + ex);
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

        return idDepto;
    }
}
