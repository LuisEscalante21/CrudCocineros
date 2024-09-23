
package Modelo;

import Vista.frmCocineros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Cocineros {
    //1- Parametros
    private String UUID_Cocinero;
    private String Nombre_Cocinero;
    private int Edad_Cocinero;
    private double Peso_Cocinero;
    private String Correo_Cocinero;

   
    public String getUUID_Cocinero() {
        return UUID_Cocinero;
    }

    public void setUUID_Cocinero(String UUID_Cocinero) {
        this.UUID_Cocinero = UUID_Cocinero;
    }

    public String getNombre_Cocinero() {
        return Nombre_Cocinero;
    }

    public void setNombre_Cocinero(String Nombre_Cocinero) {
        this.Nombre_Cocinero = Nombre_Cocinero;
    }

    public int getEdad_Cocinero() {
        return Edad_Cocinero;
    }

    public void setEdad_Cocinero(int Edad_Cocinero) {
        this.Edad_Cocinero = Edad_Cocinero;
    }

    public Double getPeso_Cocinero() {
        return Peso_Cocinero;
    }

    public void setPeso_Cocinero(double Peso_Cocinero) {
        this.Peso_Cocinero = Peso_Cocinero;
    }

    public String getCorreo_Cocinero() {
        return Correo_Cocinero;
    }

    public void setCorreo_Cocinero(String Correo_Cocinero) {
        this.Correo_Cocinero = Correo_Cocinero;
    }
    
    public void Guardar() {
        
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement addCocinero = conexion.prepareStatement("INSERT INTO tb_Cocineros(UUID_Cocinero, Nombre_Cocinero, Edad_Cocinero, Peso_Cocinero, Correo_Cocinero) VALUES (?, ?, ?, ?, ?)");
            addCocinero.setString(1, UUID.randomUUID().toString());
            addCocinero.setString(2, getNombre_Cocinero());
            addCocinero.setInt(3, getEdad_Cocinero());
            addCocinero.setDouble(4, getPeso_Cocinero());
            addCocinero.setString(5, getCorreo_Cocinero());
 
            addCocinero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Cocinero", "Nombre_Cocinero", "Edad_Cocinero", "Peso_Cocinero", "Correo_Cocinero"});
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tb_Cocineros");
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Cocinero"), 
                    rs.getString("Nombre_Cocinero"), 
                    rs.getInt("Edad_Cocinero"), 
                    rs.getDouble("Peso_Cocinero"),
                    rs.getString("Correo_Cocinero")});
            }
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
                e.printStackTrace();
        }
    }
    
    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tb_Cocineros where UUID_Cocinero = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
     public void cargarDatosTabla(frmCocineros vista) {
        int filaSeleccionada = vista.jtbCocineros.getSelectedRow();

        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbCocineros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbCocineros.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 4).toString();

            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }
     
     public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                PreparedStatement updateCocinero = conexion.prepareStatement("update tb_Cocineros set Nombre_Cocinero = ?, Edad_Cocinero = ?, Peso_Cocinero = ?, Correo_Cocinero = ? where UUID_Cocinero = ?");

                updateCocinero.setString(1, getNombre_Cocinero());
                updateCocinero.setInt(2, getEdad_Cocinero());
                updateCocinero.setDouble(3, getPeso_Cocinero());
                updateCocinero.setString(4, getCorreo_Cocinero());
                updateCocinero.setString(5, miUUId);
                updateCocinero.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
     
    public void limpiar(frmCocineros vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
}
