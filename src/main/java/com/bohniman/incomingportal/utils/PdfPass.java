package com.bohniman.incomingportal.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * GeneratePdfBill
 */
@Component
public class PdfPass {

    // @Autowired
    // TransMedicalCheckupRepository medicalCheckupRepository;

    private static final Logger logger = LoggerFactory.getLogger(PdfPass.class);

    // public ByteArrayInputStream generatePassPdf(Journey journey) {
    // // .rotate()
    // Document document = new Document(PageSize.A4, 0, 10, 22, 10);
    // ByteArrayOutputStream out = new ByteArrayOutputStream();
    // System.out.println(journey.getCitizen().getMobile());
    // Resource resource = new ClassPathResource("static/images/emblem.png");
    // try {

    // PdfWriter.getInstance(document, out);
    // document.open();

    // logger.info("Application ePass");
    // PdfPCell cell;
    // Font fbs = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
    // Font f = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);
    // Font fu = new Font(Font.HELVETICA, 10, Font.UNDERLINE, Color.BLACK);
    // Font fb = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
    // Font fbl = new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK);

    // // ========================================================================
    // // # Student info part
    // // ========================================================================
    // float[] columnWidth = { 40F };
    // PdfPTable table = new PdfPTable(columnWidth);
    // cell = new PdfPCell(new Paragraph("Inter State Online e-Pass", fbl));
    // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(20);
    // cell.setColspan(2);
    // table.addCell(cell);

    // Image image = Image.getInstance(resource.getFile().getAbsolutePath());
    // cell = new PdfPCell(image);
    // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(20);
    // cell.setColspan(2);
    // table.addCell(cell);

    // document.add(table);

    // float[] columnWidth1 = { 5F, 5F, 5F, 5F, 5F, 5F, 5F, 5F };
    // PdfPTable table1 = new PdfPTable(columnWidth1);
    // table1.setWidthPercentage(95);

    // cell = new PdfPCell(new Paragraph("PASS DETAILS", fb));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Mobile : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getCitizen().getMobile(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(6);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("CATEGORY OF PASSENGERS", fb));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Category : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getPurpose().getPurpose().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Category Details : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getPurposeDetails().toUpperCase(),
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Willing To : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getJourneyType().getJourneyType().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(3);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("PASSENGER DETAILS", fb));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // if (journey.getMemList() == null || journey.getMemList().size() == 0) {
    // cell = new PdfPCell(new Paragraph("", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);
    // } else {
    // for (MemberDetails member : journey.getMemList()) {
    // cell = new PdfPCell(new Paragraph("Name : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(member.getMemberName().toUpperCase(),
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // // cell.setColspan(3);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Age : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(member.getMemberAge(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Gender : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph((member.getMemberGender().equals("M")) ?
    // "MALE"
    // : (member.getMemberGender().equals("F")) ? "FEMALE" : "OTHER", fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Medical Condition : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(member.getMedCondition(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // }
    // }

    // cell = new PdfPCell(new Paragraph("JOURNEY FROM", fb));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("State : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getFromState().getStateName().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("District : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getFromDistrict().getDistrictName().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Sub-District : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getFromSubDistrict().getSubDistrictName().toUpperCase(),
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Pincode : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getFromPincode(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Police Station : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getFromPoliceStation().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(6);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("JOURNEY TO", fb));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("State : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getToState().getStateName().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("District : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getToDistrict().getDistrictName().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Sub-District : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getToSubDistrict().getSubDistrictName().toUpperCase(),
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Pincode : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getToPincode(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Police Station : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getToPoliceStation().toUpperCase(),
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(6);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("JOURNEY ARRANGEMENT DETAILS", fb));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(8);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Arrangement Type : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getArrangementType().getArrangementType(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(2);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Journey Date : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getJourneyDate().toString(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Mode of Transport : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setColspan(2);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph((journey.getModeOfTransport().equals("01"))
    // ? "BY ROAD"
    // : (journey.getModeOfTransport().equals("02")) ? "BY TRAIN"
    // : (journey.getModeOfTransport().equals("03")) ? "BY AIR" : "",
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Transport Details : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(15);
    // cell.setPaddingBottom(15);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new
    // Paragraph(journey.getTransportDetails().toUpperCase(), fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(2);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("Assam Border Gate : ", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph(journey.getEntryDetails().toUpperCase(),
    // fbs));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // cell = new PdfPCell(new Paragraph("", f));
    // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    // cell.setColspan(4);
    // cell.setBorder(0);
    // cell.setPaddingLeft(5);
    // cell.setPaddingTop(10);
    // cell.setPaddingBottom(10);
    // cell.setBorder(Rectangle.BOX);
    // table1.addCell(cell);

    // document.add(table1);

    // document.close();

    // } catch (Exception ex) {
    // ex.printStackTrace();
    // logger.error("Generate ePass PDF Error occurred: {}", ex);
    // }

    // return new ByteArrayInputStream(out.toByteArray());
    // }

    // public ByteArrayInputStream generateErrorPdf(String msg) {

    // Document document = new Document(PageSize.A4, 30, 30, 60, 10);
    // ByteArrayOutputStream out = new ByteArrayOutputStream();

    // try {

    // PdfPCell cell;
    // Font f = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

    // // First Part
    // float[] pointColumnWidths1 = { 100F };
    // PdfPTable table1 = new PdfPTable(pointColumnWidths1);

    // cell = new PdfPCell(new Paragraph(msg, new Font(Font.HELVETICA, 10,
    // Font.BOLD, Color.BLACK)));
    // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    // cell.setBorder(0);
    // cell.setPaddingBottom(10);
    // table1.addCell(cell);

    // PdfWriter.getInstance(document, out);
    // document.open();

    // document.add(table1);

    // document.close();

    // } catch (DocumentException ex) {
    // logger.error("Error Generating error PDF: {}", ex);
    // }

    // return new ByteArrayInputStream(out.toByteArray());
    // }

}