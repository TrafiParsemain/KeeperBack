package KeeperGroup.KeeperBack.Users;

import KeeperGroup.KeeperBack.KeeperBackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
public class UsersRessource {

    @Autowired
    private UsersRepository usersRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);

    @GetMapping("/jpa/bdusers")
    public List<User> getAllUsers() throws InterruptedException{
        //Thread.sleep(3000);
        LOGGER.info("Get request all users");
        //return noteService.findAll();
        //return noteJpaRepository.findByUsername(username, Sort.by("id"));
        return usersRepository.findAll();
    }

    @DeleteMapping("/jpa/bdusers/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        LOGGER.info("Delete user request :" + username);
        LOGGER.info("Will delete user with id :" + usersRepository.getByUsername(username).getId());
        usersRepository.deleteById(usersRepository.getByUsername(username).getId());
        return ResponseEntity.noContent().build();
    }

    //Update
    @PutMapping("/jpa/bdusers/{username}")
    public ResponseEntity<User> updateUser(
            @PathVariable String username,
            @RequestBody User user){
        LOGGER.info("Update user with name: " + username);
        User BdUser = usersRepository.getByUsername(username);
        BdUser.setPassword(user.getPassword());
        User userUpdated =  usersRepository.save(BdUser);
        return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/jpa/bdusers")
    public ResponseEntity<User> addUser(@RequestBody User user){

        LOGGER.info("Create user request");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        user.setRegistrationDate(date);

        User existantUser ;
        existantUser = usersRepository.getByUsername(user.getUsername());
        if(existantUser== null) {
            User userAdded = usersRepository.save(user);
            return new ResponseEntity<User>(userAdded, HttpStatus.CREATED);
        } else {
            new RuntimeException("Username already taken");
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }


    }
}
