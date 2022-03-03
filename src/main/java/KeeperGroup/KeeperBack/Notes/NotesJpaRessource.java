package KeeperGroup.KeeperBack.Notes;

import KeeperGroup.KeeperBack.KeeperBackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
public class NotesJpaRessource {

    @Autowired
    private NoteHardCodeService noteService;

    @Autowired
    private NotesJpaRepository noteJpaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);


    @GetMapping("/jpa/users/{username}/notes")
    public List<Note> getAllNotes(@PathVariable String username) throws InterruptedException{
        //Thread.sleep(3000);
        LOGGER.info("Get request all note");
        //return noteService.findAll();
        return noteJpaRepository.findByUsername(username, Sort.by("id"));
        //return noteJpaRepository.findAllByOrderByIdAsc();
        //return noteJpaRepository.findByAndSort(username,Sort.by("id"));
    }

    @DeleteMapping("/jpa/users/{username}/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String username, @PathVariable long id){
        LOGGER.info("Delete request id :" + id);
        noteJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Update
    @PutMapping("/jpa/users/{username}/notes/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable String username,
            @PathVariable long id,
            @RequestBody Note note){
        LOGGER.info("Update note with id: " + id);
        Note BdNote = noteJpaRepository.getById(id);
        //note.setUsername(username);
        BdNote.setStatut(note.getStatut());
        BdNote.setTitle(note.getTitle());
        BdNote.setContent(note.getContent());
        BdNote.setUsername(note.getUsername());
        Note noteUpdated =  noteJpaRepository.save(BdNote);
        return new ResponseEntity<Note>(noteUpdated, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/notes")
    public ResponseEntity<Void> addNote(
            @PathVariable String username,
            @RequestBody Note note){

        LOGGER.info("Create note request id known : " + note.getId());
        note.setUsername(username);
        Note noteAdded =  noteJpaRepository.save(note);

        //return new ResponseEntity<Note>(noteAdded, HttpStatus.OK);

        //Renvoi l'URL de L'objet creer
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(noteAdded.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
