/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conducto;

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
public class conductoDAO implements Serializable {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
//  agregar

    public void insert(conducto reg) {

        PreparedStatement sentence = null;

        try {

            String sql = "insert into conducto (id_conducto,id_edificio,cant_depto_conducto,sello_conducto,con_sombrete,secciones,con_interior,relacion_lados,prueba_tiro,toma_aire,materialidad,observaciones,sello,artefacto_conducto,potencia_artefacto) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdConducto());
            sentence.setInt(2, reg.getIdEdificio());
            sentence.setInt(3, reg.getCantDeptoConducto());
            sentence.setString(4, reg.getSelloConducto());
            sentence.setString(5, reg.getCondSombrete());
            sentence.setString(6, reg.getSecciones());
            sentence.setString(7, reg.getConInterior());
            sentence.setString(8, reg.getRelacionLados());
            sentence.setString(9, reg.getPruebaTiro());
            sentence.setString(10, reg.getTomaAire());
            sentence.setString(11, reg.getMaterialidad());
            sentence.setString(12, reg.getObservaciones());
            sentence.setString(13, reg.getSello());
            sentence.setString(14, reg.getArtefactoConducto());
            sentence.setInt(15, reg.getPotenciaArtefacto());
            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, insert() : " + ex);
        } finally {
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }

    }
//  consultar por conducto

    public Collection<conducto> getAll() {

        Statement sentence = null;
        ResultSet result = null;

        Collection<conducto> list = new ArrayList<conducto>();

        try {
            String sql = "select * from conducto";

            sentence = conexion.createStatement();

            result = sentence.executeQuery(sql);

            while (result.next()) {
                conducto reg = new conducto();
                reg.setIdConducto(result.getInt("id_conducto"));
                reg.setCantDeptoConducto(result.getInt("cant_depto_conducto"));
                reg.setSelloConducto(result.getString("sello_conducto"));
                reg.setCondSombrete(result.getString("con_sombrete"));
                reg.setSecciones(result.getString("secciones"));
                reg.setConInterior(result.getString("con_interior"));
                reg.setRelacionLados(result.getString("relacion_lados"));
                reg.setPruebaTiro(result.getString("prueba_tiro"));
                reg.setTomaAire(result.getString("toma_aire"));
                reg.setMaterialidad(result.getString("materialidad"));
                reg.setObservaciones(result.getString("observaciones"));
                reg.setSello(result.getString("sello"));
                reg.setArtefactoConducto(result.getString("artefacto_conducto"));
                reg.setPotenciaArtefacto(result.getInt("potencia_artefacto"));
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, getAll() : " + ex);
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

// obtener lista de registros segun el id de edificio
    public Collection<conducto> getAllById(int idEdificio) {
        PreparedStatement sentence = null;
        ResultSet result = null;
        Collection<conducto> list = new ArrayList<conducto>();
        try {
            String sql = "select * from conducto where id_edificio=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idEdificio);
            result = sentence.executeQuery();

            while (result.next()) {
                conducto reg = new conducto();
                reg.setIdConducto(result.getInt("id_conducto"));
                reg.setCantDeptoConducto(result.getInt("cant_depto_conducto"));
                reg.setSelloConducto(result.getString("sello_conducto"));
                reg.setCondSombrete(result.getString("con_sombrete"));
                reg.setSecciones(result.getString("secciones"));
                reg.setConInterior(result.getString("con_interior"));
                reg.setRelacionLados(result.getString("relacion_lados"));
                reg.setPruebaTiro(result.getString("prueba_tiro"));
                reg.setTomaAire(result.getString("toma_aire"));
                reg.setMaterialidad(result.getString("materialidad"));
                reg.setObservaciones(result.getString("observaciones"));
                reg.setSello(result.getString("sello"));
                reg.setArtefactoConducto(result.getString("artefacto_conducto"));
                reg.setPotenciaArtefacto(result.getInt("potencia_artefacto"));
                list.add(reg);
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, getAll() : " + ex);
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

    public void delete(int id_conducto) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from conducto where id_conducto=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id_conducto);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
//  actualizar

    public void update(conducto conducto) {

        PreparedStatement sentence = null;

        try {
            String sql = "update conducto cant_depto_conducto=?,sello_conducto=?,con_sombrete=?,secciones=?,con_interior=?,relacion_lados=?,prueba_tiro=?,toma_aire=?,materialidad=? where id_conducto=?";

            sentence = conexion.prepareStatement(sql);

            
            sentence.setInt(1, conducto.getCantDeptoConducto());
            sentence.setString(2, conducto.getSelloConducto());
            sentence.setString(3, conducto.getCondSombrete());
            sentence.setString(4, conducto.getSecciones());
            sentence.setString(5, conducto.getConInterior());
            sentence.setString(6, conducto.getRelacionLados());
            sentence.setString(7, conducto.getPruebaTiro());
            sentence.setString(8, conducto.getTomaAire());
            sentence.setString(9, conducto.getMaterialidad());
            sentence.setString(10, conducto.getObservaciones());
            sentence.setString(11, conducto.getSello());
            
            sentence.setInt(12, conducto.getIdConducto());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public conducto findById(int idConducto) {
        PreparedStatement sentence = null;
        ResultSet result = null;
        conducto reg = null;
        try {
            String sql = "select * from conducto where id_conducto=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, idConducto);
            result = sentence.executeQuery();
            while (result.next()) {
                reg = new conducto();
                reg.setIdConducto(result.getInt("id_conducto"));
                reg.setCantDeptoConducto(result.getInt("cant_depto_conducto"));
                reg.setSelloConducto(result.getString("sello_conducto"));
                reg.setCondSombrete(result.getString("con_sombrete"));
                reg.setSecciones(result.getString("secciones"));
                reg.setConInterior(result.getString("con_interior"));
                reg.setRelacionLados(result.getString("relacion_lados"));
                reg.setPruebaTiro(result.getString("prueba_tiro"));
                reg.setTomaAire(result.getString("toma_aire"));
                reg.setMaterialidad(result.getString("materialidad"));
                reg.setObservaciones(result.getString("observaciones"));
                reg.setSello(result.getString("sello"));
                reg.setArtefactoConducto(result.getString("artefacto_conducto"));
                reg.setPotenciaArtefacto(result.getInt("potencia_artefacto"));
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return reg;
    }

    public int cantidadConductos(int id_edificio) {
        PreparedStatement sentence = null;
        ResultSet result = null;
        int cant = 0;
        try {
            String sql = "select count(*) as cantidad from conducto where id_edificio=?";
            sentence = conexion.prepareStatement(sql);
            sentence.setInt(1, id_edificio);
            result = sentence.executeQuery();
            while (result.next()) {
                cant = result.getInt("cantidad");
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return cant;
    }

    public int getLastId() {
        PreparedStatement sentence = null;
        ResultSet result = null;
        int idConducto = 0;
        try {
            String sql = "SELECT MAX( id_conducto ) AS id FROM conducto";
            sentence = conexion.prepareStatement(sql);
            result = sentence.executeQuery();
            while (result.next()) {
                idConducto = result.getInt("id");
            }
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, getLastId() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, getLastId() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, getLastId() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, getLastId() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return idConducto;
    }
    
     public void updateCondiciones(conducto conducto) {

        PreparedStatement sentence = null;

        try {
            String sql = "update conducto set con_sombrete=?, secciones=?, con_interior=?, relacion_lados=?, prueba_tiro=?, toma_aire=?, materialidad=?, observaciones=?, sello=?, artefacto_conducto=?, potencia_artefacto =? where id_conducto=?";

            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, conducto.getCondSombrete());
            sentence.setString(2, conducto.getSecciones());
            sentence.setString(3, conducto.getConInterior());
            sentence.setString(4, conducto.getRelacionLados());
            sentence.setString(5, conducto.getPruebaTiro());
            sentence.setString(6, conducto.getTomaAire());
            sentence.setString(7, conducto.getMaterialidad());
            sentence.setString(8, conducto.getObservaciones());
            sentence.setString(9, conducto.getSello());
            sentence.setString(10,conducto.getArtefactoConducto());
            sentence.setInt(11, conducto.getPotenciaArtefacto());
            
            sentence.setInt(12, conducto.getIdConducto());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en conductoDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en conductoDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en conductoDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
