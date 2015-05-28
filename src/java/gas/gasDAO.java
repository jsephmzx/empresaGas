/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gas;

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
public class gasDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar

    public void insert(gas reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into gas (id_gas,tipo_gas) values (?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdGas());
            sentence.setString(2, reg.getTipoGas());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en gasDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en gasDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en gasDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en gasDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en gasDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en gasDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar

    public Collection<gas> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<gas> list = new ArrayList<gas>();

        try {
            String sql = "select * from gas";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                gas reg = new gas();
                reg.setIdGas(result.getInt("id_gas"));
                reg.setTipoGas(result.getString("tipo_gas"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en gasDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en gasDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en gasDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en gasDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en gasDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en gasDAO, getAll() : " + ex);
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

    public gas findbyIdGas(int idGas) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        gas reg = null;

        try {
            String sql = "select * from gas where id_gas=?";
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, idGas);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */

                reg = new gas();
                /* obtener resultset */
                reg.setIdGas(result.getInt("id_gas"));
                reg.setTipoGas(result.getString("tipo_gas"));

            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en GasDAO, findbyIdGas() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en GasDAO, findbyIdGas() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en GasDAO, findbyIdGas() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en GasDAO, findbyIdGas() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en GasDAO, findbyIdGas() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en GasDAO, findbyIdGas() : " + ex);
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

    public void delete(int id_gas) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from gas where id_gas=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_gas);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en gasDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en gasDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en gasDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en gasDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en gasDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en gasDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar

    public void update(gas gas) {

        PreparedStatement sentence = null;

        try {
            String sql = "update gas set tipo_gas=? where id_gas=?";

            sentence = conexion.prepareStatement(sql);
            sentence.setString(1, gas.getTipoGas());
            sentence.setInt(2, gas.getIdGas());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en gasDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en gasDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en gasDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en gasDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en gasDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en gasDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
