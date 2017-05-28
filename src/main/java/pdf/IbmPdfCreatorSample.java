package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * pdf-creator
 * Created on 27.05.17.
 */
public class IbmPdfCreatorSample {
    public static void main(String[] args) throws IOException, DocumentException {
        /*Create document*/
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        /**/

        /*Create pdf writer*/
        PdfWriter writer = PdfWriter.getInstance(
                document, new FileOutputStream("ITextTest.pdf")
        );
        document.open();
        /**/

        /*Create Paragraph*/
        Anchor anchorTarget = new Anchor("First page of the document.");
        anchorTarget.setName("BackToTop");
        Paragraph paragraph1 = new Paragraph();

        paragraph1.setSpacingBefore(50);

        paragraph1.add(anchorTarget);
        document.add(paragraph1);

        document.add(
                new Paragraph("Some more text on the " +
                        "first page with different color and font type.",
                        FontFactory.getFont(
                                FontFactory.COURIER,
                                14,
                                Font.BOLD,
                                new CMYKColor(0, 255, 0, 0)
                        )
                )
        );
        /**/

        /*Create Chapter*/
        Paragraph title1 = new Paragraph(
                "Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA,
                        18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);
        document.add(chapter1);
        /**/

        /*Create Section*/
        Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 255, 17)));
        Section section1 = chapter1.addSection(title11);
        Paragraph someSectionText = new Paragraph("This text comes as part of section 1of chapter 1. ");

        section1.add(someSectionText);

        someSectionText = new Paragraph("Following is a 3 X 2 table.");
        section1.add(someSectionText);
        /**/

        /*Create Table*/
        PdfPTable t = new PdfPTable(3);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
        t.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
        t.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
        t.addCell(c3);
        t.addCell("1.1");
        t.addCell("1.2");
        t.addCell("1.3");

        section1.add(t);
        /**/

        /*Create List*/
        List l = new List(true, false, 10);

        l.add(new ListItem("First item of list"));
        l.add(new ListItem("Second item of list"));

        section1.add(l);
        /**/

        /*Add Image*/
        Image image2 = Image.getInstance("image.jpeg");
        image2.scaleAbsolute(120f, 120f);

        section1.add(image2);
        /**/

        /*Add Anchor*/
        Paragraph title2 = new Paragraph("Using Anchor",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 0, 0)));
        section1.add(title2);
        title2.setSpacingBefore(5000);

        Anchor anchor2 = new Anchor("Back To Top");
        anchor2.setReference("#BackToTop");
        section1.add(anchor2);
        /**/

        document.add(chapter1);
        document.close();
    }
}
