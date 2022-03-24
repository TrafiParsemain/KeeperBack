package KeeperGroup.KeeperBack.PDF;

import KeeperGroup.KeeperBack.KeeperBackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
public class PdfController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);

    @RequestMapping(path = "/get-pdf-template")
    public void getPdfTemplate(HttpServletResponse servletResponse) throws IOException {
        LOGGER.info("Get request buffer PDF Template");

        File pdfFile = new File("C:\\Users\\Parsemain\\Desktop\\Exemple PDF\\PDF1.pdf");


        servletResponse.setContentType("application/pdf");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"PDF1.pdf\"");
        servletResponse.setContentLength((int) pdfFile.length());

        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        OutputStream responseOutputStream = servletResponse.getOutputStream();
        int bytes;
        while ((bytes = fileInputStream.read()) != -1) {
            responseOutputStream.write(bytes);
        }

        LOGGER.info("Fin de request DL PDF");
    }
}
