package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Cocineros;
import Vista.frmCocineros;

public class ctrlCocineros implements MouseListener, KeyListener{
    
    private Cocineros modelo;
    private frmCocineros vista;
   
    public ctrlCocineros(Cocineros modelo, frmCocineros vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbCocineros);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbCocineros.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombre_Cocinero(vista.txtNombre.getText());
            modelo.setEdad_Cocinero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Cocinero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Cocinero(vista.txtCorreo.getText());
            System.out.println("Clicked");
            modelo.Guardar();
            modelo.Mostrar(vista.jtbCocineros);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbCocineros);
            modelo.Mostrar(vista.jtbCocineros);
             System.err.println("Clicked");
        }
        
        if(e.getSource() == vista.jtbCocineros){
            modelo.cargarDatosTabla(vista);
             System.err.println("Clicked");
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre_Cocinero(vista.txtNombre.getText());
            modelo.setEdad_Cocinero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Cocinero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Cocinero(vista.txtCorreo.getText());
            System.err.println("Clicked");
            
            
            modelo.Actualizar(vista.jtbCocineros);
            modelo.Mostrar(vista.jtbCocineros);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
