/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

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
public class usuarioDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar

    public void insert(usuario reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into usuario (id_usuario,nombre_usuario,contrasena,tipo_usuario,email_usuario) values (?,?,?,?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdUsuario());
            sentence.setString(2, reg.getNombreUsuario());
            sentence.setString(3, reg.getContrasena());
            sentence.setString(4, reg.getTipoUsuario());
            sentence.setString(5, reg.getEmailUsuario());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usuarioDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }

    //

    public int nuevoId() {

        Statement sentence = null;
        ResultSet result = null;

        int ID;

        try {
            sentence = conexion.createStatement();
            String sql = "select max(id_usuario) from usuario";
            result = sentence.executeQuery(sql);
            result.next();
            ID = result.getInt(1);
            ID++;

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usuarioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, getAll() : " + ex);
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
        return ID;
    }
//  consultar

    public Collection<usuario> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<usuario> list = new ArrayList<usuario>();

        try {
            String sql = "select * from usuario";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                usuario reg = new usuario();
                reg.setIdUsuario(result.getInt("id_usuario"));
                reg.setNombreUsuario(result.getString("nombre_usuario"));
                reg.setContrasena(result.getString("contrasena"));
                reg.setTipoUsuario(result.getString("tipo_usuario"));
                reg.setEmailUsuario(result.getString("email_usuario"));

                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usuarioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, getAll() : " + ex);
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

    // consulta por id ingreso usuario
    public usuario findbyIdUsuario(String nombreUsuario) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        usuario reg = null;

        try {
            String sql = "select * from usuario where nombre_usuario = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, nombreUsuario);

            result = sentence.executeQuery();
            while (result.next()) {
                /* instanciar objeto */
                reg = new usuario();
                /* obtener resultset */
                reg.setIdUsuario(result.getInt("id_usuario"));
                reg.setNombreUsuario(result.getString("nombre_usuario"));
                reg.setContrasena(result.getString("contrasena"));
                reg.setTipoUsuario(result.getString("tipo_usuario"));
                reg.setEmailUsuario(result.getString("email_usuario"));

            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, findbyIdUsuario() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, findbyIdUsuario() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, findbyIdUsuario() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usuarioDAO, findbyIdUsuario() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, findbyIdCity() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, findbyIdUsuario() : " + ex);
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

    //buscar por id usuario

    public usuario findbyIdUsuarios(int idUsuario) {

        PreparedStatement sentence = null;
        ResultSet result = null;
        usuario reg = null;

        try {
            String sql = "select * from usuario where id_usuario=?";
            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, idUsuario);
            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new usuario();
                /* obtener resultset */
                reg.setIdUsuario(result.getInt("id_usuario"));
                reg.setNombreUsuario(result.getString("nombre_usuario"));
                reg.setContrasena(result.getString("contrasena"));
                reg.setEmailUsuario(result.getString("email_usuario"));
                reg.setTipoUsuario(result.getString("tipo_usuario"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, findbyIdUsuario() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, findbyIdUsuario() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, findbyIdUsuario() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usuarioDAO, findbyIdUsuario() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, findbyIdCity() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, findbyIdUsuario() : " + ex);
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
//

    public ArrayList<String> getNombre() {

        Statement sentence = null;
        ResultSet result = null;

        ArrayList<String> list = new ArrayList<String>();

        try {
            String sql = "select nombre_usuario from usuario";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                list.add(result.getString("nombre_usuario"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usarioDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, getAll() : " + ex);
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
        return list;
    }
//  eliminar

    public void delete(int id_usuario) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from usuario where id_usuario=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_usuario);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en detalleDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en detalleDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en detalleDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en detalleDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en detalleDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en detalleDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar

    public void update(usuario user) {

        PreparedStatement sentence = null;

        try {
            String sql = "update usuario set nombre_usuario=?,contrasena=?,tipo_usuario=?,email_usuario=? where id_usuario=?";

            sentence = conexion.prepareStatement(sql);
            System.out.println("=======================================");
            System.out.println("id usuario :"+user.getIdUsuario());
            System.out.println("pass :"+user.getContrasena());
            System.out.println("tipo usuario :"+user.getTipoUsuario());
            System.out.println("mail:"+user.getEmailUsuario());
            System.out.println("user:"+user.getNombreUsuario());
            System.out.println("=======================================");
            sentence.setString(1, user.getNombreUsuario());
            sentence.setString(2, user.getContrasena());
            sentence.setString(3, user.getTipoUsuario());
            sentence.setString(4, user.getEmailUsuario());
            sentence.setInt(5, user.getIdUsuario());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en usuarioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en usuarioDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en usuarioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en usuarioDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en usuarioDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en usuarioDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
