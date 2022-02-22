package KeeperGroup.KeeperBack.Notes;



import KeeperGroup.KeeperBack.KeeperBackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteHardCodeService {

    private static List<Note> notes = new ArrayList();
    private static int idCounter = 1;

    static{
        notes.add(new Note(idCounter++,"me","Welcome","It is a content","OK"));
        notes.add(new Note(idCounter++,"me","Hello","Bourgade","alert"));
        notes.add(new Note(idCounter++,"me","Peter Parker","It is Spiderman","warning"));
    }

    public List<Note> findAll(){
        return notes;
    }

    public Note save(Note note){
        if (note.getId()==-1 || note.getId()==0){
            //note.setId(++idCounter);
            note.setId(findFirstIdAvialable());
            notes.add(note);
         } else {
            //deleteById(note.getId());
            //notes.add(note);
            majNoteWithId(note);
        }
        return note;
    }

    public Note deleteById (long id){
        Note note = findById(id);

        if (note == null) return null;

        if(notes.remove(note)) {
            return note;
        }
        return null;
    }

    public Note findById(long id) {
        for(Note note:notes){
            if(note.getId() == id ){
                return note ;
            }
        }
        return null;
    }
    public void majNoteWithId(Note goodNote){
        for(Note note:notes){
            if(goodNote.getId() == note.getId() ){
                note.setContent(goodNote.getContent());
                note.setTitle(goodNote.getTitle());
                note.setStatut(goodNote.getStatut());
            }
        }
    }

    public int findFirstIdAvialable() {
        int freeId  = 1;
        boolean dispo = false;
        while (!dispo) {
            dispo = true;
            for (Note note : notes) {
                if (note.getId() == freeId) {dispo = false;}
            }
            if (!dispo) {freeId++;}
        }
        return freeId ;
    }


}
