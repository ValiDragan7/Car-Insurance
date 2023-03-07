import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

import org.apache.poi.xwpf.usermodel.*;

public class Print {

    public Print(Client c) {

        try {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setText("Contract de asigurare auto");
            run.setFontSize(20);
            paragraph.setSpacingAfter(1000);
            XWPFParagraph paragraph2 = document.createParagraph();
            paragraph2.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun run2 = paragraph2.createRun();
            run2.setText("Agentie: D&G");
            paragraph2.setSpacingAfter(2000);

            XWPFParagraph paragraph3 = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run3 = paragraph3.createRun();
            paragraph3.setIndentationHanging(50);
            run3.setText("Nume si prenumele clientului: " + c.getNume() + " " +
                    c.getPrenume());
            paragraph3.setIndentationHanging(0);
            paragraph3.setSpacingAfter(2000);
            run3.addBreak();
            run3.setText("Varsta Clientului: " + c.getVarsta());
            run3.addBreak();

            XWPFParagraph paragraph5 = document.createParagraph();
            XWPFRun run5 = paragraph5.createRun();
            paragraph5.setSpacingAfter(3000);
            run5.setText("Specificatii masina: ");
            run5.addBreak();
            run5.setText("Marca masina: " + c.getMarca());
            run5.addBreak();
            run5.setText("Model masina: " + c.getSpecificatii().getModel());
            run5.addBreak();
            run5.setText("Capacitate cilindrica: " + c.getSpecificatii().getCapCilindrica());
            run5.addBreak();
            run5.setText("Norma de poluare europeana: " + c.getSpecificatii().getClasaEuro());
            run5.addBreak();
            run5.setText("Anul masinii: " + c.getAnMasina());
            run5.addBreak();
            run5.setText("Tip de motor: " + c.getSpecificatii().getComb());
            run5.addBreak();
            run5.setText("Valabilitate contract de pe date de " + getDate() + " pana pe " + getNextYear()
                    + ". Contract emis pe data de " + getDate());
            run5.addBreak();
            run5.setText("Total plata: " + String.valueOf(c.calculeazaAsigurare()));

            XWPFParagraph paragraph4 = document.createParagraph();
            XWPFRun run4 = paragraph4.createRun();
            run4.setText(
                    "Data: " + getDate() + "                                                                    "
                            + "Semnatura: "
                            + c.getNume() + " " +
                            c.getPrenume());

            FileOutputStream output = new FileOutputStream(
                    "Documente/" + c.getNume() + c.getPrenume() + ".docx");
            document.write(output);
            output.close();
            document.close();
            JOptionPane.showMessageDialog(null, "Contractul a fost creat in folderul \"Documente\"!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public String getNextYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date date = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
