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

                        for (int ii = 0; ii < 2; ii++) {
                                // First Part
                                float[] pointColumnWidths1 = { 10F, 10F, 10F };
                                PdfPTable table1 = new PdfPTable(pointColumnWidths1);
                                table1.setWidthPercentage(85);

                                if (ii == 0) {
                                        cell = new PdfPCell(new Paragraph(
                                                        "The print out of this is to be deposited at Destination Airport"
                                                                        .toUpperCase(),
                                                        new Font(Font.HELVETICA, 14, Font.BOLD, Color.BLACK)));
                                } else {
                                        cell = new PdfPCell(new Paragraph(
                                                        "The print out of this is to be deposited during reporting at Screening Centre"
                                                                        .toUpperCase(),
                                                        new Font(Font.HELVETICA, 14, Font.BOLD, Color.BLACK)));
                                }
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setColspan(3);
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table1.addCell(cell);

                                if (ii == 0) {
                                        cell = new PdfPCell(new Paragraph(
                                                        "To be shown at the check-in counter of the Airline",
                                                        new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setColspan(3);
                                        cell.setBorder(0);
                                        table1.addCell(cell);
                                }

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
                                cell = new PdfPCell(new Paragraph(journey.getId() + "",
                                                new Font(Font.HELVETICA, 14, Font.BOLD, Color.BLACK)));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setPaddingTop(-16);
                                cell.setBorder(0);
                                cell.setColspan(3);
                                table1.addCell(cell);

                                // Second Part
                                float[] pointColumnWidths2 = { 6F, 6F, 6F, 6F };
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

                                cell = new PdfPCell(new Paragraph("AGE", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph(" : " + journey.getAge(), f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("NAME", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getName(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("GENDER", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getGender(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
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

                                cell = new PdfPCell(new Paragraph("FLIGHT NO", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getFlightNo(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("PNR NO", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getPnrNumber(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("DESTINATION DISTRICT", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getDistrict().getDistrictName(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("DESTINATION THANA", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getThana(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("DESTINATION PINCODE", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getPincode(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("FLAT/HOUSE NO", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getFlat_house_no(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("STREET/ROAD NAME", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getStreet(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("LANDMARK", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getLandmark(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph("", f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("ID PROOF TYPE", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getDocumentType(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                cell = new PdfPCell(new Paragraph("ID PROOF NUMBER", f));
                                cell.setBorder(0);
                                cell.setPaddingTop(10);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Paragraph(" : " + journey.getDocumentNumber(), f));
                                cell.setPaddingTop(10);
                                cell.setBorder(0);
                                table2.addCell(cell);

                                List<ScreeningLocation> locations = citizenService
                                                .getScreeningCenter(journey.getDistrict().getDistrictCode());

                                cell = new PdfPCell(new Paragraph("Screening Locations",
                                                new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK)));
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
                                if (ii == 0) {
                                        document.newPage();
                                }
                        }
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