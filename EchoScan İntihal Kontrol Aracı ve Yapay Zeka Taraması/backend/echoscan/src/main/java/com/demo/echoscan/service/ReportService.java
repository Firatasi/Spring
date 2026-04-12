package com.demo.echoscan.service;

import com.demo.echoscan.entity.AnalysisRecord;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;

@Service
public class ReportService {

    public byte[] generatePdfReport(AnalysisRecord record) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
        PdfWriter writer = PdfWriter.getInstance(document, out);

        document.open();

        try {
            // Arctic Core Palette
            Color arcticCyan = new Color(0, 210, 211); 
            Color deepGraphite = new Color(28, 28, 30);
            Color subtleGray = new Color(142, 142, 147);

            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, "Cp1254", BaseFont.NOT_EMBEDDED);
            Font brandFont = new Font(bf, 14, Font.BOLD, arcticCyan);
            Font titleFont = new Font(bf, 22, Font.BOLD, deepGraphite);
            Font sectionFont = new Font(bf, 13, Font.BOLD, deepGraphite);
            Font bodyFont = new Font(bf, 10, Font.NORMAL, new Color(44, 44, 46));

            // 1. MASTER BRANDING (WITH ORBIT & STATUS DOT)
            PdfContentByte cb = writer.getDirectContent();
            float logoX = 75;
            float logoY = PageSize.A4.getHeight() - 85;

            // Draw "Arctic Orbit Brackets"
            cb.setLineWidth(1.2f);
            cb.setColorStroke(new Color(0, 210, 211)); // Arctic Cyan
            cb.arc(logoX - 22, logoY - 22, logoX + 22, logoY + 22, 50, 80);  // Top-Right Bracket
            cb.arc(logoX - 22, logoY - 22, logoX + 22, logoY + 22, 230, 80); // Bottom-Left Bracket
            cb.stroke();

            // Draw "Status Dot" (Arctic Pulse)
            cb.setColorFill(new Color(0, 210, 211));
            cb.circle(logoX + 25, logoY - 15, 2.5f);
            cb.fill();

            try {
                InputStream logoStream = getClass().getResourceAsStream("/logo.png");
                if (logoStream != null) {
                    byte[] logoBytes = logoStream.readAllBytes();
                    Image logo = Image.getInstance(logoBytes);
                    logo.scaleToFit(36, 36);
                    logo.setAbsolutePosition(logoX - 18, logoY - 18);
                    document.add(logo);
                }
            } catch (Exception e) {}

            // Header Margin Spacing
            Paragraph spacer = new Paragraph(" ");
            spacer.setSpacingAfter(45);
            document.add(spacer);

            Paragraph brand = new Paragraph("EchoScan INFINITE ENTERPRISE", brandFont);
            document.add(brand);

            Paragraph docTitle = new Paragraph("GÜVENLİK VE ANALİZ SERTİFİKASI", titleFont);
            docTitle.setSpacingAfter(45);
            document.add(docTitle);

            // 2. INDUSTRY STANDARD METRICS
            addIndustrySection(document, "KRİTİK ANALİTİK VERİLER", sectionFont);
            
            document.add(createIndustryMetric(bf, "Yapay Zeka Tespiti", record.getAiProbability(), true));
            document.add(createIndustryMetric(bf, "İntihal Oranı", record.getPlagiarismScore(), true));
            document.add(createIndustryMetric(bf, "Benzerlik Endeksi", record.getStyleConsistencyScore(), false));

            document.add(new Paragraph("\n"));
            document.add(new LineSeparator(0.8f, 100, new Color(229, 229, 234), Element.ALIGN_CENTER, 0));
            document.add(new Paragraph("\n"));

            // 3. NARRATIVE SENTEZ
            addIndustrySection(document, "KAPSAMLI ANALİZ BULGULARI", sectionFont);
            
            // Clean content formatting
            String summaryContent = record.getSummary() != null ? record.getSummary() : "";
            Paragraph summaryParagraph = new Paragraph(summaryContent, bodyFont);
            summaryParagraph.setLeading(18f);
            summaryParagraph.setSpacingAfter(50);
            document.add(summaryParagraph);

            // 4. SECURE FOOTER
            addSecureFooter(writer, bf, subtleGray);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return out.toByteArray();
    }

    private void addIndustrySection(Document document, String text, Font font) throws DocumentException {
        Paragraph p = new Paragraph(text, font);
        p.setSpacingAfter(18);
        document.add(p);
    }

    private Paragraph createIndustryMetric(BaseFont bf, String label, double score, boolean invert) {
        Color statusColor;
        int intValue = (int) Math.floor(score);

        if (invert) {
            if (intValue > 60) statusColor = new Color(255, 69, 58);
            else if (intValue > 30) statusColor = new Color(255, 159, 10);
            else statusColor = new Color(48, 209, 88);
        } else {
            if (intValue > 75) statusColor = new Color(48, 209, 88);
            else if (intValue > 45) statusColor = new Color(255, 159, 10);
            else statusColor = new Color(255, 69, 58);
        }
        
        // Final fallback color fix
        if (invert && intValue <= 30) statusColor = new Color(48, 209, 88);

        Paragraph p = new Paragraph();
        p.add(new Chunk(label.toUpperCase(Locale.forLanguageTag("tr")) + ": ", new Font(bf, 8, Font.BOLD, new Color(110, 110, 115))));
        p.add(new Chunk("%" + intValue, new Font(bf, 16, Font.BOLD, statusColor)));
        p.setSpacingAfter(12);
        return p;
    }

    private void addSecureFooter(PdfWriter writer, BaseFont bf, Color color) {
        PdfContentByte cb = writer.getDirectContent();
        cb.setColorFill(new Color(245, 245, 247));
        cb.rectangle(0, 0, PageSize.A4.getWidth(), 35);
        cb.fill();

        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(50, 0, PageSize.A4.getWidth() - 50, 30);
        Paragraph footer = new Paragraph("ECHOSCAN INFINITE ENTERPRISE | AKADEMİK DÜRÜSTLÜK SERTİFİKASI | Tüm Hakları Saklıdır.", new Font(bf, 7, Font.NORMAL, color));
        footer.setAlignment(Element.ALIGN_CENTER);
        ct.addElement(footer);
        try { ct.go(); } catch (Exception e) {}
    }
}
