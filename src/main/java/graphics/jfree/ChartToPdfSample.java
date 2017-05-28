package graphics.jfree;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * pdf-creator
 * Created on 28.05.17.
 */
public class ChartToPdfSample {
    public static void main(String[] args) throws IOException, DocumentException {
        SimpleDrawerImprove simpleDrawerImprove = new SimpleDrawerImprove("Sin");

        simpleDrawerImprove.addChartPanelWithDefaultX(
                IntStream.range(1, 100)
                        .mapToDouble(
                                i -> i
                        )
                        .map(
                                Math::sin
                        )
                        .boxed()
                        .collect(Collectors.toList())
        );

        simpleDrawerImprove.draw();

        final ByteArrayOutputStream outputStream = simpleDrawerImprove.writeChartAsJPEG();

        saveOutputStreamToPdf(outputStream);
    }

    public static void saveOutputStreamToPdf(ByteArrayOutputStream outputStream) throws IOException, DocumentException {
        /*Create document*/
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        /**/

        /*Create pdf writer*/
        PdfWriter writer = PdfWriter.getInstance(
                document, new FileOutputStream("Test.pdf")
        );
        document.open();
        /**/

        /*Create Chapter*/
        Paragraph title1 = new Paragraph(
                "Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA,
                        18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
        Chapter chapter1 = new Chapter(title1, 1);
        document.add(chapter1);
        /**/

        /*Create Section*/
        Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                        new CMYKColor(0, 255, 255, 17)));
        Section section1 = chapter1.addSection(title11);
        Image image2 = Image.getInstance(outputStream.toByteArray());

        section1.add(image2);
        /**/

        document.add(chapter1);
        document.close();
    }
}
