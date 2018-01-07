package com.io.invoices.invoiceshibernate.pdfCreator;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;


import java.io.FileOutputStream;
import java.io.IOException;

public class PdfCreator {

    public PdfCreator(Facture facture){
        this.facture =facture;
    }


    public static final String RESULT = "invoice.pdf";
    Font font = new Font(Font.FontFamily.HELVETICA, 12,Font.BOLD);
    Facture facture;

    public void createPdf() throws IOException, DocumentException{

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        document.open();
        Font colfont = new Font(Font.FontFamily.HELVETICA, 10);
/*
        if(facture == null) System.out.print("null");
        Paragraph p2 = new Paragraph("Faktura nr FV " + facture.getIssueDate().toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);

        Font font1 = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(35);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidths(new int[]{1, 1});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cell = new PdfPCell(new Phrase("Data wystawienia:", font1));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getIssueDate().toString(), font1));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Miejsce wystawienia:", font1));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getPlace(), font1));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        document.add(table);



        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{1, 1});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        cell = new PdfPCell(new Phrase("Sprzedawca", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nabywca", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getUser().getName(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getUser().getName(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("os. Złotej Jesieni 4", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("31-826 Kraków", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("NIP: 6751273612", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("E-mail: abc@gmail.com", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(cell);
        document.add(table);
*/
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setWidths(new int[]{2, 10, 4, 4, 4, 4, 4, 4});
        table.addCell("Lp");
        table.addCell("Nazwa towaru lub usługi");
        table.addCell("Ilość");
        table.addCell("Cena netto");
        table.addCell("Stawka VAT");
        table.addCell("Wartość netto");
        table.addCell("Wartość VAT");
        table.addCell("Wartość Brutto");
        table.addCell("1");
        table.addCell("Kasztany");
        table.addCell("2");
        table.addCell("100");
        table.addCell("100");
        table.addCell("100");
        table.addCell("100");
        table.addCell("100");
        document.add(table);

        colfont = new Font(Font.FontFamily.HELVETICA, 10);
        table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{1, 1, 1, 1    });
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cell = new PdfPCell(new Phrase("Forma zapłaty:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("przelew", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Razem:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("100 PLN", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Termin zapłaty:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("04-11-2017", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nr konta:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("83101010230000261395100000", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        document.add(table);
        // step 5
        document.close();
    }

}
