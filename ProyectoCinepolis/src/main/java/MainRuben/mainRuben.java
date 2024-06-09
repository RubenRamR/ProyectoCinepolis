/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainRuben;

import dtos.FuncionDTO;
import entidades.EntidadSucursal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import negocio.FuncionNegocio;
import negocio.ISucursalNegocio;
import negocio.NegocioException;
import negocio.SucursalNegocio;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.IConexionBD;
import persistencia.IFuncionDAO;
import persistencia.ISucursalDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;

/**
 *
 * @author crazy
 */
public class mainRuben {

    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();

        // Crear una instancia de la implementación de la interfaz IFuncionDAO
        IFuncionDAO funcionDAO = new FuncionDAO(conexionBD);

        // Crear una instancia de FuncionNegocio pasando la instancia de IFuncionDAO
        FuncionNegocio negocio = new FuncionNegocio(funcionDAO);

        // Pruebas de inserción, edición, eliminación y consulta
        try
        {
            // Insertar una nueva función
            FuncionDTO nuevaFuncion = new FuncionDTO(10.5f, new Date(0, 0, 0), new Time(0, 0, 0), new Time(0, 0, 0), new Time(0, 0, 0), 100, 1, 1);
            negocio.insertarFuncion(nuevaFuncion);

            // Editar una función existente
            FuncionDTO funcionEditada = new FuncionDTO(1, 15.5f, new Date(0, 0, 0), new Time(0, 0, 0), new Time(0, 0, 0), new Time(0, 0, 0), 100, 1, 1);
            negocio.editarFuncion(funcionEditada);

            // Eliminar una función existente
            FuncionDTO funcionAEliminar = new FuncionDTO();
            funcionAEliminar.setId(1); // ID de la función a eliminar
            negocio.eliminarFuncion(funcionAEliminar);

            // Consultar todas las funciones
            List<FuncionDTO> funciones = negocio.consultarFunciones(10, 0);
            for (FuncionDTO funcion : funciones)
            {
                System.out.println(funcion.toString());
            }

            // Consultar una función por su ID
            FuncionDTO funcionConsultada = negocio.consultarFuncionPorID(2); // ID de la función a consultar
            System.out.println("Función consultada por ID: " + funcionConsultada.toString());

            // Consultar funciones por película y sucursal
            List<FuncionDTO> funcionesPorPeliculaYSucursal = negocio.consultarFuncionesPorPeliculaYSucursal(1, 1, 10, 0); // IDs de la sucursal y la película
            for (FuncionDTO funcion : funcionesPorPeliculaYSucursal)
            {
                System.out.println(funcion.toString());
            }
        } catch (NegocioException ex)
        {
            // Manejar las excepciones de negocio
            ex.printStackTrace();
        }
//        IConexionBD conexionBD = new ConexionBD();
//        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
//        ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDAO);
//
//        frmCrudSucursal frmCrud = new frmCrudSucursal(sucursalNegocio);
//        frmCrud.show();
    }

}
