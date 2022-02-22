package KeeperGroup.KeeperBack.HelloPack;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

//Controller
@CrossOrigin(origins= "http://localhost:3000") //Autorise cette source pour l'appel
@RestController
@RequestMapping
public class HelloClass {

    //GET
    //URI -/hello_world
    // methode - "hello World"
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World!Plop";
    }



    @GetMapping(path = "/hello-world-object")
    public HelloWorldObject helloWorldObject(){
        return new HelloWorldObject("Hello World!PULP");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldObject helloWorldObject(@PathVariable String name){
        //throw new RuntimeException("Something went wrong");
        return new HelloWorldObject(String.format("Hello to %s" ,name));
    }

   /* @GetMapping(value = "/hello-world/path-variable/{name}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},headers = "Accept=application/json")
    public HelloWorldObject helloWorldObject(@PathVariable String name){
        //throw new RuntimeException("Something went wrong");
        return new HelloWorldObject(String.format("Hello to %s" ,name));
    }*/

}
