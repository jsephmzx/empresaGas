/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresagas;

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
public class empresagasDAO implements Serializable {
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar
    public void insert(empresagas reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into empresagas (id_empresa,empresa_gas) values (?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdEmpresa());
            sentence.setString(2, reg.getEmpresaGas());


            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en empresagasDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en empresagasDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en empresagasDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en gasDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en empresagasDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en empresagasDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar
    public Collection<empresagas> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<empresagas> list = new ArrayList<empresagas>();

        try {
            String sql = "select * from empresagas ";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                empresagas reg = new empresagas();
                reg.setIdEmpresa(result.getInt("id_empresa"));
                reg.setEmpresaGas(result.getString("empresa_gas"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en empresagasDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en empresagasDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en empresagasDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en empresagasDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en empresagasDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en empresagasDAO, getAll() : " + ex);
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
    // obtener por id de edificio en empresa gas
    public empresagas findbyIdEmpresagas(int idEmp) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        empresagas reg = null;

        try {
            String sql = "select * from empresagas where id_empresa=?";
            sentence = conexion.prepareStatement(sql);
             
            sentence.setInt(1, idEmp);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new empresagas();
                /* obtener resultset */
                reg.setIdEmpresa(result.getInt("id_empresa"));                
                reg.setEmpresaGas(result.getString("empresa_gas"));

            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en EmpresaGasDAO, findbyIdEmpresa() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en EmpresaGasDAO, findbyIdEmpresa() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en EmpresaGasDAO, findbyIdEmpresa() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en EmpresaGasDAO, findbyIdEmpresa() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en EmpresaGasDAO, findbyIdGas() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en EmpresaGasDAO, findbyIdEmpresa() : " + ex);
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
    public void delete(int id_empresa) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from empresagas where id_empresa=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_empresa);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en empresagasDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en empresagasDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en empresagasDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en empresagasDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en empresagasDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en empresagasDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar
    public void update(empresagas empresagas) {

        PreparedStatement sentence = null;

        try {
            String sql = "update empresagas set empresa_gas=? where id_empresa=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, empresagas.getIdEmpresa());
            sentence.setString(2, empresagas.getEmpresaGas());
 

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en empresagasDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en empresagasDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en empresagasDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en empresagasDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en empresagasDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en empresagasDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
