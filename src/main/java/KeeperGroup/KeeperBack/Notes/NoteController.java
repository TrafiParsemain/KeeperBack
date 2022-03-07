package KeeperGroup.KeeperBack.Notes;

import KeeperGroup.KeeperBack.KeeperBackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
public class NoteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);
    private final CsvExportService csvExportService;

    public NoteController(CsvExportService csvExportService) {
        this.csvExportService = csvExportService;
    }

    @RequestMapping(path = "/dlnotes")
    public void getAllNotesInCsv(HttpServletResponse servletResponse) throws IOException {
        LOGGER.info("Get request DL NOTES CSV");
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"notes.csv\"");
        csvExportService.writeNotesToCsv(servletResponse.getWriter());
        LOGGER.info("Fin de request DL");
    }

}
