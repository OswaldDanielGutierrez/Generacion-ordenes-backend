package com.reto.generacion_ordenes_back.service;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.reto.generacion_ordenes_back.entity.DatosFormulario;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerarOrdenService {

    private final List<DatosFormulario> listaDatosFormulario = new ArrayList<>();
    public DatosFormulario recibirDatos(DatosFormulario datosFormulario) {
        listaDatosFormulario.clear();
        listaDatosFormulario.add(datosFormulario);

        System.out.println(datosFormulario.getNombre());
        System.out.println(datosFormulario.getTelefono());
        System.out.println(datosFormulario.getNumeroDocumento());
        datosFormulario.getProductoSeleccionados().forEach(producto -> System.out.println("Producto: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad()));

        return datosFormulario;
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        Paragraph paragraph = new Paragraph();

        document.open();

        Font fontCompania = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontCompania.setSize(14);
        Paragraph compania = new Paragraph("Mecatico Company", fontCompania);
        compania.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(compania);
        paragraph.setSpacingAfter(5);
        document.add(paragraph);


        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitulo.setSize(30);
        Paragraph titulo = new Paragraph("ORDEN DE COMPRA", fontTitulo);
        titulo.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(titulo);
        paragraph.setSpacingBefore(5);
        document.add(paragraph);

        for (DatosFormulario datos : listaDatosFormulario) {
            document.add(new Paragraph("Nombre: " + datos.getNombre()));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Número de Documento: " + datos.getNumeroDocumento()));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Teléfono: " + datos.getTelefono()));

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            PdfPCell cellProducto = new PdfPCell(new Phrase("Producto",FontFactory.getFont(FontFactory.HELVETICA_BOLD) ));
            cellProducto.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProducto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellProducto.setBackgroundColor(new Color(0, 122, 255));
            PdfPCell cellCantidad = new PdfPCell(new Phrase("Cantidad", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cellCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCantidad.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellCantidad.setBackgroundColor(new Color(0, 122, 255));

            table.addCell(cellProducto);
            table.addCell(cellCantidad);

            boolean colorFondo = true;
            for (int rowIndex = 0; rowIndex < datos.getProductoSeleccionados().size(); rowIndex++) {
                DatosFormulario.ProductoSeleccionado producto = datos.getProductoSeleccionados().get(rowIndex);
                PdfPCell cellNombre = new PdfPCell(new Phrase(producto.getNombre()));
                PdfPCell cellCantidadValue = new PdfPCell(new Phrase(String.valueOf(producto.getCantidad())));
                cellCantidadValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellCantidadValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellNombre.setVerticalAlignment(Element.ALIGN_MIDDLE);

                if (colorFondo) {
                    cellNombre.setBackgroundColor(Color.WHITE);
                    cellCantidadValue.setBackgroundColor(Color.WHITE);
                } else {
                    cellNombre.setBackgroundColor(new Color(142, 196, 255));
                    cellCantidadValue.setBackgroundColor(new Color(142, 196, 255));
                }
                colorFondo = !colorFondo;

                table.addCell(cellNombre);
                table.addCell(cellCantidadValue);
            }


            float cellHeight = 40f;
            for (int i = 0; i < table.getRows().size(); i++) {
                PdfPCell[] cells = table.getRows().get(i).getCells();
                for (PdfPCell cell : cells) {
                    cell.setFixedHeight(cellHeight);
                }
            }

            document.add(table);

            document.add(Chunk.NEWLINE);
        }

        document.close();
    }

}
