/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author caarl
 */


public class GenerarTicketPDF {

//    public void generarTicketPDF() throws FileNotFoundException {
//    String asientosDisponibles = lblAsientosDisp.getText();
//    String dia = lblDia.getText();
//    String hora = lblHora.getText();
//    String precioTotal = lblPrecio.getText();
//
//    Document document = new Document(PageSize.A6); // Tamaño A6 para formato de ticket
//
//    try {
//        PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
//        document.open();
//
//        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
//        Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
//        Font fontContenido = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
//
//        // Título del ticket
//        Paragraph titulo = new Paragraph("Muchas Gracias!!!!", fontTitulo);
//        titulo.setAlignment(Element.ALIGN_CENTER);
//        document.add(titulo);
//
//        // Subtítulo
//        Paragraph subtitulo = new Paragraph("Pago realizado con éxito", fontSubTitulo);
//        subtitulo.setAlignment(Element.ALIGN_CENTER);
//        document.add(subtitulo);
//
//        document.add(new Paragraph(" ")); // Espacio en blanco
//
//        // Crear una tabla para los detalles del ticket
//        PdfPTable table = new PdfPTable(2);
//        table.setWidthPercentage(100);
//        table.setSpacingBefore(10f);
//        table.setSpacingAfter(10f);
//
//        // Configurar las celdas de la tabla
//        PdfPCell cell;
//
//        cell = new PdfPCell(new Phrase("Asientos disponibles:", fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(asientosDisponibles, fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Día:", fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(dia, fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Hora:", fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(hora, fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Total a pagar:", fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(precioTotal, fontContenido));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        table.addCell(cell);
//
//        document.add(table);
//
//    } catch (DocumentException | FileNotFoundException e) {
//    } finally {
//        document.close();
//        JOptionPane.showMessageDialog(null, "Se creó el archivo 'Ticket.pdf' en la carpeta del proyecto");
//    }
//    }
}
