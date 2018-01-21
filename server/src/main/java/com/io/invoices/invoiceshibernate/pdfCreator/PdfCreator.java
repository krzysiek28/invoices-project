package com.io.invoices.invoiceshibernate.pdfCreator;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.productentry.ProductEntry;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfCreator {

    public static final String RESULT = "invoice.pdf";
    //private Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    Font font = FontFactory.getFont("resources/arial.ttf", "Cp1250", BaseFont.EMBEDDED);
    private Facture facture;

    //

    public PdfCreator(Facture facture) {
        this.facture = facture;
    }

    public void createPdf() throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        document.open();
//        Font colfont = new Font(Font.FontFamily.HELVETICA, 10);
//        Font colfontb = new Font(Font.FontFamily.HELVETICA, 10,Font.BOLD);
//        Font bankfont = new Font(Font.FontFamily.HELVETICA, 8);

        //Font colfont = FontFactory.getFont("resources/arial.ttf", "UTF-8", BaseFont.CACHED, 10);
       // Font colfontb = FontFactory.getFont("resources/arial.ttf", "Cp1250", BaseFont.CACHED, 10,Font.BOLD);
       // Font bankfont = FontFactory.getFont("resources/arial.ttf", "Cp1250", BaseFont.CACHED, 8);

        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font colfont=new Font(helvetica,10);
        Font colfontb=new Font(helvetica,10, Font.BOLD);

        Font bankfont=new Font(helvetica,8);




        Paragraph p2 = new Paragraph("Faktura nr " + facture.getNumber().toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);

        PdfPCell emptycell = new PdfPCell(new Phrase("", colfont));
        emptycell.setBorder(Rectangle.NO_BORDER);
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

        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );


        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{1, 1});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        cell = new PdfPCell(new Phrase("Sprzedawca", colfontb));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nabywca", colfontb));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getFirm().getName().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getClient().getName().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getFirm().getPlace().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
      //  table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getClient().getAdditionalData().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("NIP:" + facture.getFirm().getNip(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptycell);
        cell.setBorder(Rectangle.NO_BORDER);
        cell = new PdfPCell(new Phrase("E-mail:" + facture.getFirm().getEmail(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(emptycell);
        cell = new PdfPCell(new Phrase("Tel:" + facture.getFirm().getPhone().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.addCell(emptycell);
        document.add(table);

        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );

        List<ProductEntry> products = facture.getProducts();

        table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setWidths(new int[]{2, 8, 2, 2, 4, 3, 4, 4, 4});
        table.addCell(new Phrase("Lp",colfont));
        table.addCell(new Phrase("Nazwa towaru lub usługi", colfont));
        table.addCell(new Phrase("Ilość",colfont));
        table.addCell(new Phrase("Jedn.",colfont));
        table.addCell(new Phrase("Cena netto",colfont));
        table.addCell(new Phrase("Stawka VAT",colfont));
        table.addCell(new Phrase("Wartość netto",colfont));
        table.addCell(new Phrase("Wartość VAT",colfont));
        table.addCell(new Phrase("Wartość Brutto",colfont));
        Integer nr;
        for (int i = 0; i < products.size(); i++) {
            nr = i + 1;

            String vatInfo = "";

            if ((products.get(i).getProduct().getVatInfo() != null) && (!products.get(i).getProduct().getVatInfo().isEmpty()))
                vatInfo = "["+products.get(i).getProduct().getVatInfo()+"]";

            table.addCell(new Phrase(nr.toString(),colfont));
            table.addCell(new Phrase(products.get(i).getProduct().getName(),colfont));
            table.addCell(new Phrase(products.get(i).getQuantity().toString(),colfont));
            table.addCell(new Phrase(products.get(i).getProduct().getUnit(),colfont));
            table.addCell(new Phrase(products.get(i).getProduct().getNetUnitPrice().toString()+products.get(i).getProduct().getCurrency(),colfont));
            table.addCell(new Phrase(products.get(i).getProduct().getVatRate().toString()+"% " + vatInfo,colfont));
            table.addCell(new Phrase(products.get(i).getNetprice().toString()+products.get(i).getProduct().getCurrency(),colfont));
            table.addCell(new Phrase(products.get(i).getVat().toString()+products.get(i).getProduct().getCurrency(),colfont));
            table.addCell(new Phrase(products.get(i).getGrossprice().toString()+products.get(i).getProduct().getCurrency(),colfont));
        }
        document.add(table);
        document.add( Chunk.NEWLINE );


        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setWidths(new int[]{1,1});

        Double totalNet = 0.0;
        Double totalVat = 0.0;

        for(ProductEntry product : products) {
            totalNet += product.getNetprice();
            totalVat += product.getVat();
        }

        totalNet = Math.round(totalNet * 100.0) / 100.0;
        totalVat = Math.round(totalVat * 100.0) / 100.0;

        table.addCell(emptycell);
        cell = new PdfPCell(new Phrase("Razem netto: "+ totalNet.toString() + " " + facture.getCurrency(), colfont));
        table.addCell(cell);

//        cell = new PdfPCell(new Phrase("", bankfont));
//        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptycell);
        cell = new PdfPCell(new Phrase("Razem VAT: " + totalVat.toString() + " " + facture.getCurrency(), colfont));
        table.addCell(cell);


        table.addCell(emptycell);
        cell = new PdfPCell(new Phrase("Razem brutto: "+facture.getTotal() + " " + facture.getCurrency(), colfont));
        table.addCell(cell);
        document.add(table);

        document.add( Chunk.NEWLINE );


        table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidths(new int[]{1, 1, 1, 1});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        cell = new PdfPCell(new Phrase("Forma zapłaty:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getPaymentMethod(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Razem:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getTotal().toString()+" "+facture.getCurrency().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Termin zapłaty:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getPaymentDate().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Zapłacono:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getPaid().toString()+" "+facture.getCurrency().toString(), colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nr konta:", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(facture.getBankAccount().getBankAccount().toString()+"\n"+facture.getBankAccount().getAdditionalData().toString(), bankfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Do zapłaty", colfontb));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(String.format("%.2f",facture.getToPay())+" "+facture.getCurrency().toString(), colfontb));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        //table.addCell(emptycell);
        //table.addCell(emptycell);
        document.add(table);








        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );

        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setWidths(new int[]{1,1});

        cell = new PdfPCell(new Phrase("Kupujący", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Sprzedający", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

//        cell = new PdfPCell(new Phrase("", bankfont));
//        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptycell);
        cell = new PdfPCell(new Phrase(facture.getIssuer().toString(), bankfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("______________________________", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("______________________________", colfont));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);



       document.add(table);

        // step 5
        document.close();
    }

}
