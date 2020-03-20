package com.tianhy.springbootspringmvc.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.tianhy.springbootspringmvc.service.PdfExportService;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc: PDF视图
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
public class PDFView extends AbstractPdfView {

    private PdfExportService pdfExportService;

    public PDFView(PdfExportService pdfExportService) {
        this.pdfExportService = pdfExportService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        pdfExportService.make(model, document, writer, request, response);
    }
}
