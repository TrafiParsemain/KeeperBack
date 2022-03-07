package KeeperGroup.KeeperBack.Notes;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CsvExportService {

    private static final Logger log = getLogger(CsvExportService.class);

    private final NotesJpaRepository notesRepository;

    public CsvExportService(NotesJpaRepository notesRepository) {
        this.notesRepository= notesRepository;
    }

    public void writeNotesToCsv(Writer writer) throws IOException {
        List<Note> notes = notesRepository.findAll();


        // Send Csv data to response
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL)) {
            csvPrinter.printRecord("ID", "Title", "Content", "Statut","Username");
            for (Note note : notes) {
                csvPrinter.printRecord(note.getId(), note.getTitle(), note.getContent(), note.getStatut(),note.getUsername());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
        //Ecriture locale
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(("C:\\Users\\Parsemain\\Desktop\\Protect\\NotesEntity.csv")), CSVFormat.EXCEL)) {
            csvPrinter.printRecord("ID", "Title", "Content", "Statut","Username");
            for (Note note : notes) {
                csvPrinter.printRecord(note.getId(), note.getTitle(), note.getContent(), note.getStatut(),note.getUsername());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }

        // Reader
        log.info("Reading the CSV file ......");
        //Before reading value we delete all
        notesRepository.deleteAll();
        FileReader theReader = new FileReader("C:\\Users\\Parsemain\\Desktop\\Protect\\NotesEntity.csv");
        CSVParser csvParser = new CSVParser(theReader , CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase());
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {

            //log.info("CSV data Id: " + csvRecord.get("ID"));
            //log.info("CSV data Id: " + csvRecord.get("Title"));
            Note newNote = new Note() ;
            newNote.setId(Long.parseLong(csvRecord.get("ID")));
            newNote.setUsername(csvRecord.get("username"));
            newNote.setTitle(csvRecord.get("Title"));
            newNote.setContent(csvRecord.get("content"));
            newNote.setStatut(csvRecord.get("statut"));
            //log.info("Here's a note with ID : " + newNote.getId() + " - "+ newNote.getTitle() + " - says : " + newNote.getContent());

            notesRepository.save(newNote);

        }
    }

}
