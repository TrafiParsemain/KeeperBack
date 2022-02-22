package KeeperGroup.KeeperBack.Notes;

import KeeperGroup.KeeperBack.KeeperBackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;




@RestController
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
public class NotesRessource {

    @Autowired
    private NoteHardCodeService noteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);


    @GetMapping("/users/{username}/notes")
    public List<Note> getAllNotes(@PathVariable String username) throws InterruptedException{
        //Thread.sleep(3000);
        LOGGER.info("Get request all note");
        return noteService.findAll();
    }

    @DeleteMapping("/users/{username}/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String username, @PathVariable long id){
        LOGGER.info("Delete request id :" + id);
        Note note = noteService.deleteById(id);
        if (note != null ){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Update
    @PutMapping("/users/{username}/notes/{id}")
    public ResponseEntity<Note> updateNote(
                @PathVariable String username,
                @PathVariable long id,
                @RequestBody Note note){
        Note noteUpdated =  noteService.save(note);
        return new ResponseEntity<Note>(noteUpdated, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/notes")
    public ResponseEntity<Void> addNote(
            @PathVariable String username,
            @RequestBody Note note){
        Note noteAdded =  noteService.save(note);
        //return new ResponseEntity<Note>(noteAdded, HttpStatus.OK);

        //Renvoi l'URL de L'objet creer
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(noteAdded.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
