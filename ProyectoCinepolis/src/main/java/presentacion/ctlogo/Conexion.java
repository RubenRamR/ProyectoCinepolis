/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.ctlogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author caarl
 */
public class Conexion {

    Connection conectar;
    Statement stSentenciasSQL;
    ResultSet rsDatosResultado;
    PreparedStatement psPrepararSentencia;

    public Conexion() throws ClassNotFoundException, SQLException {
        try {
           Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost/Cinepolis";
            conectar = (Connection) DriverManager.getConnection(url, "root", "Bi0log1a1");
            stSentenciasSQL = (Statement) conectar.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException ex) {
            throw ex;
        } catch (SQLException ex1) {
            throw ex1;
        }
        
    }
    // funcion para ejecutar sentencias SELECT
public ResultSet consulta(String sql) throws SQLException{
    try{
        rsDatosResultado = stSentenciasSQL.executeQuery(sql);
    }catch (SQLException ex){
        throw ex;
    }
    return rsDatosResultado;
}

//funcion para ejecutar sentencias INSERT DELETE UPDATE
public ResultSet ejecutar(String sql) throws SQLException{
    try{
        stSentenciasSQL.execute(sql);
    }catch (SQLException ex){
        throw ex;
    }
    return null;
}

    // funcion para ejecutar sentencias SELECT
}