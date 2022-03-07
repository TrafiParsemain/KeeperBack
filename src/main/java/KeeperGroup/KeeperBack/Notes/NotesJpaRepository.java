package KeeperGroup.KeeperBack.Notes;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesJpaRepository extends JpaRepository<Note, Long> {

        List<Note> findByUsername(String username, Sort id);
        List<Note> findByUsernameAndStatut(String username,String statut, Sort id);
        List<Note> findAllByOrderByIdAsc();
        //List<Note> findByUsernameByOrderByIdAsc(String username);

        //@Query("select * from note where username=?1")
        //List<Note> findByAndSort(String username, Sort sort);


}
