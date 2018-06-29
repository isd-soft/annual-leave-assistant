package com.example.demo2.utility;

import com.example.demo2.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFLeaveRequest {

    public static ByteArrayInputStream leaveRequestAnual(User user) {

        Document document = new Document(PageSize.A4, 60, 60, 60, 60);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            BaseFont arial = BaseFont.createFont("c:/windows/fonts/arial.ttf", "UTF-8", BaseFont.EMBEDDED);

            Font normalFont = new Font(arial, 18);
            Font boldFont = new Font(arial, 20, Font.BOLD);

            Chunk intro = new Chunk("Către Directorul SRL „Inther Software \n Development”,\n" +
                    "\t\t\t\tDl. Radu Corlăteanu\n",boldFont);
            Paragraph director = new Paragraph();
            director.add(intro);
            director.setAlignment(Element.ALIGN_RIGHT);
            director.setLeading(20);

            Paragraph cerere = new Paragraph("Cerere", boldFont);
            cerere.setAlignment(Element.ALIGN_CENTER);

            Chunk text = new Chunk("Domnule Director,\n" +
                    "\n" +
                    "Subsemnatul, " + user.getName() +
                    ", salariat al SRL „Inther Software Development”, având funcţia de programator, rog să îmi acordaţi concediu de odihnă anual pentru perioada: "
                    + user.getId() + " - " + user.getRole() + "(pe durata a X zile calendaristice).\n");

            Paragraph baseText = new Paragraph();
            baseText.add(text);

            Chunk dateSign = new Chunk("Data \n" + "Semnătura");
            Paragraph dateSignParagraph = new Paragraph(dateSign);

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(director);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(cerere);
            document.add(Chunk.NEWLINE);
            document.add(baseText);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(dateSignParagraph);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
