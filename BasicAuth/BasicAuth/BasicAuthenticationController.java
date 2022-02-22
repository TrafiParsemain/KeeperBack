package KeeperGroup.KeeperBack.BasicAuth;

import org.springframework.web.bind.annotation.*;

//Controller
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
@RestController
public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public AuthenticationObject helloWorldObject(){
        return new AuthenticationObject("You are authenticated!");
    }


}
