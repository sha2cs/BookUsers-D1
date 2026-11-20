package modelo;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PruebaPDF {

    public static void main(String[] args) {

        Document documento = new Document();

        try {
            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream("Prueba.pdf")
            );

            documento.open();
            documento.add(new Paragraph("¡OpenPDF funciona correctamente!"));
            documento.close();

            System.out.println("PDF creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}