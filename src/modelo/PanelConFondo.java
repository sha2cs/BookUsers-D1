package modelo;

import javax.swing.*;
import java.awt.*;

// Esta clase permite crear paneles que utilizan
// una imagen como fondo de la interfaz gráfica.
public class PanelConFondo extends JPanel {

    // Variable donde se almacena la imagen de fondo.
    private Image imagen;

    // Constructor que recibe la ubicación de la imagen.
    public PanelConFondo(String rutaImagen) {

        // Buscamos la imagen utilizando la ruta recibida.
        java.net.URL recurso = getClass().getResource(rutaImagen);

        // Comprobamos si la imagen fue encontrada.
        if (recurso != null) {

            // Cargamos la imagen para poder utilizarla como fondo.
            imagen = new ImageIcon(recurso).getImage();

        } else {

            // Si no se encuentra la imagen, mostramos un mensaje
            // en la consola indicando cuál fue la ruta que falló.
            System.out.println("No se encontró la imagen: " + rutaImagen);
        }
    }

    // Este método se encarga de dibujar los elementos del panel.
    @Override
    protected void paintComponent(Graphics g) {

        // Ejecutamos primero el método original de JPanel
        // para preparar correctamente el área de dibujo.
        super.paintComponent(g);

        // Comprobamos que la imagen se haya cargado correctamente.
        if (imagen != null) {

            // Dibujamos la imagen como fondo del panel.
            // getWidth() y getHeight() permiten ajustar la imagen
            // al tamaño actual de la ventana.
            g.drawImage(
                    imagen,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }
}