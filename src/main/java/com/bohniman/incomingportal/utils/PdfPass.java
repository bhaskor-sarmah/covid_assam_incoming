package com.bohniman.incomingportal.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import com.bohniman.incomingportal.model.Journey;
import com.bohniman.incomingportal.model.ScreeningLocation;
import com.bohniman.incomingportal.service.CitizenService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GeneratePdfBill
 */
@Component
public class PdfPass {

    private static final Logger logger = LoggerFactory.getLogger(PdfPass.class);

    @Autowired
    CitizenService citizenService;

    public ByteArrayInputStream generatePassPdf(Journey journey) {
        Document document = new Document(PageSize.A4, 50, 50, 60, 10);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            PdfPCell cell;
            Font f = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

            // First Part
            float[] pointColumnWidths1 = { 10F, 10F, 10F };
            PdfPTable table1 = new PdfPTable(pointColumnWidths1);
            table1.setWidthPercentage(85);

            // cell = new PdfPCell(new Paragraph("TO BE SHOWN AT THE TIME OF
            // BOARDING/DE-BOARDING/SCREENING",
            // new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK)));
            // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // cell.setColspan(3);
            // cell.setBorder(0);
            // table1.addCell(cell);

            cell = new PdfPCell(
                    new Paragraph("The print out of this to be deposited at Destination Airport".toUpperCase(),
                            new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table1.addCell(cell);

            cell = new PdfPCell(new Paragraph("To be shown at the check-in counter of the Airline",
                    new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setBorder(0);
            table1.addCell(cell);

            // JSONObject qrCodeObj = new JSONObject();
            // qrCodeObj.put("id", journey.getId());
            byte[] qrcode = QrcodeBarCodeGenerator.from(journey.getId().toString()).withSize(700)
                    .generateQrCodeByteArray();

            cell = new PdfPCell(new Paragraph(""));
            cell.setBorder(0);
            table1.addCell(cell);

            Image image = Image.getInstance(qrcode);
            image.scaleToFit(80, 80);
            cell = new PdfPCell(image);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            cell.setPaddingLeft(0);
            cell.setPaddingTop(0);
            cell.setPaddingBottom(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(20);
            cell.setBorder(0);
            table1.addCell(cell);
            cell = new PdfPCell(new Paragraph(""));
            cell.setBorder(0);
            table1.addCell(cell);
            cell = new PdfPCell(
                    new Paragraph(journey.getId() + "", new Font(Font.HELVETICA, 14, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(-16);
            cell.setBorder(0);
            cell.setColspan(3);
            table1.addCell(cell);

            // Second Part
            float[] pointColumnWidths2 = { 6F, 6F, 4F, 4F };
            PdfPTable table2 = new PdfPTable(pointColumnWidths2);
            table2.setWidthPercentage(105);

            cell = new PdfPCell(new Paragraph("ID", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getId(), f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("DATE OF TRAVEL", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);

            DateFormat Date = DateFormat.getDateInstance();
            cell = new PdfPCell(new Paragraph(" : " + Date.format(journey.getDateOfTravel()), f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("NAME", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getName(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("FLIGHT NO", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getFlightNo(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("PNR NO", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getPnrNumber(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("DESTINATION DISTRICT", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getDistrict().getDistrictName(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("DESTINATION ADDRESS", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getAddress(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table2.addCell(cell);

            cell = new PdfPCell(new Paragraph("DESTINATION PINCODE", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table2.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getPincode(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table2.addCell(cell);

            List<ScreeningLocation> locations = citizenService
                    .getScreeningCenter(journey.getDistrict().getDistrictCode());

            cell = new PdfPCell(
                    new Paragraph("Screening Locations", new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(4);
            cell.setPaddingTop(20);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            cell.setBorder(Rectangle.BOTTOM);
            table2.addCell(cell);
            int i = 1;
            for (ScreeningLocation sl : locations) {
                cell = new PdfPCell(new Paragraph(i + ". " + sl.getLocation(),
                        new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setPaddingBottom(10);
                table2.addCell(cell);
                i++;
            }

            // Fourth Part
            float[] pointColumnWidths4 = { 1F, 1F };
            PdfPTable table4 = new PdfPTable(pointColumnWidths4);
            table4.setWidthPercentage(105);

            cell = new PdfPCell(table1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setPadding(10);
            table4.addCell(cell);

            cell = new PdfPCell(table2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setPadding(10);
            table4.addCell(cell);

            document.add(table4);

            document.newPage();

            float[] pointColumnWidths11 = { 10F, 10F, 10F };
            PdfPTable table11 = new PdfPTable(pointColumnWidths11);
            table11.setWidthPercentage(85);

            // cell = new PdfPCell(new Paragraph("TO BE SHOWN AT THE TIME OF
            // BOARDING/DE-BOARDING/SCREENING",
            // new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK)));
            // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // cell.setColspan(3);
            // cell.setBorder(0);
            // table11.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    "The print out of this to be deposited on report at the screening center".toUpperCase(),
                    new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table11.addCell(cell);

            // cell = new PdfPCell(new Paragraph("(To be deposited at home district
            // screening center.)",
            // new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
            // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // cell.setColspan(3);
            // cell.setBorder(0);
            // cell.setPaddingTop(10);
            // table11.addCell(cell);

            // JSONObject qrCodeObj = new JSONObject();
            // qrCodeObj.put("id", journey.getId());
            // byte[] qrcode =
            // QrcodeBarCodeGenerator.from(journey.getId().toString()).withSize(700)
            // .generateQrCodeByteArray();

            cell = new PdfPCell(new Paragraph(""));
            cell.setBorder(0);
            table11.addCell(cell);

            // Image image = Image.getInstance(qrcode);
            // image.scaleToFit(80, 80);
            cell = new PdfPCell(image);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            cell.setPaddingLeft(0);
            cell.setPaddingTop(0);
            cell.setPaddingBottom(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(20);
            cell.setBorder(0);
            table11.addCell(cell);
            cell = new PdfPCell(new Paragraph(""));
            cell.setBorder(0);
            table11.addCell(cell);
            cell = new PdfPCell(
                    new Paragraph(journey.getId() + "", new Font(Font.HELVETICA, 14, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(-16);
            cell.setBorder(0);
            cell.setColspan(3);
            table11.addCell(cell);

            // Second Part
            float[] pointColumnWidths22 = { 6F, 6F, 4F, 4F };
            PdfPTable table22 = new PdfPTable(pointColumnWidths22);
            table22.setWidthPercentage(105);

            cell = new PdfPCell(new Paragraph("ID", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getId(), f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("DATE OF TRAVEL", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);

            // DateFormat Date = DateFormat.getDateInstance();
            cell = new PdfPCell(new Paragraph(" : " + Date.format(journey.getDateOfTravel()), f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("NAME", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getName(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("FLIGHT NO", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getFlightNo(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("PNR NO", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getPnrNumber(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("DESTINATION DISTRICT", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getDistrict().getDistrictName(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("DESTINATION ADDRESS", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getAddress(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table22.addCell(cell);

            cell = new PdfPCell(new Paragraph("DESTINATION PINCODE", f));
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table22.addCell(cell);
            cell = new PdfPCell(new Paragraph(" : " + journey.getPincode(), f));
            cell.setColspan(3);
            cell.setPaddingTop(10);
            cell.setBorder(0);
            table22.addCell(cell);

            // List<ScreeningLocation> locations = citizenService
            // .getScreeningCenter(journey.getDistrict().getDistrictCode());

            cell = new PdfPCell(
                    new Paragraph("Screening Locations", new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(4);
            cell.setPaddingTop(20);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            cell.setBorder(Rectangle.BOTTOM);
            table22.addCell(cell);
            i = 1;
            for (ScreeningLocation sl : locations) {
                cell = new PdfPCell(new Paragraph(i + ". " + sl.getLocation(),
                        new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setPaddingBottom(10);
                table22.addCell(cell);
                i++;
            }

            // Fourth Part
            float[] pointColumnWidths44 = { 1F, 1F };
            PdfPTable table44 = new PdfPTable(pointColumnWidths44);
            table44.setWidthPercentage(105);

            cell = new PdfPCell(table11);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setPadding(10);
            table44.addCell(cell);

            cell = new PdfPCell(table22);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setPadding(10);
            table44.addCell(cell);

            document.add(table44);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generateErrorPdf(String msg) {

        Document document = new Document(PageSize.A4, 30, 30, 60, 10);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPCell cell;
            Font f = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

            // First Part
            float[] pointColumnWidths1 = { 100F };
            PdfPTable table1 = new PdfPTable(pointColumnWidths1);

            cell = new PdfPCell(new Paragraph(msg, new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            table1.addCell(cell);

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(table1);

            document.close();

        } catch (DocumentException ex) {
            logger.error("Error Generating error PDF: {}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}