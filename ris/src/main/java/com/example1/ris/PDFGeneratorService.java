package com.example1.ris;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws IOException, URISyntaxException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(20);

        Paragraph paragraph= new Paragraph("Obrazec za dodajanje inštruktorja.", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        /**String imagePath = "C:\\Users\\tanej\\IdeaProjects\\Zadnja_probapulla\\RIS\\driveSafePhoto.jpg";
        Path path = Paths.get(ClassLoader.getSystemResource("driveSafePhoto.jpg").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        document.add(img);**/
        Table table = new Table(7,20);
        //PdfPTable table1 = new PdfPTable(new float[2,2,2,2,2]);
        table.addCell(new Cell(" Id"));
        table.addCell(new Cell(" Ime"));
        table.addCell(new Cell(" Priimek"));
        table.addCell(new Cell(" Geslo"));
        table.addCell(new Cell(" E-pošta"));
        table.addCell(new Cell(" Kontakt"));
        table.addCell(new Cell(" Cena ure"));
        



        document.add(paragraph);
        document.add(table);
        document.close();
        }

    }


