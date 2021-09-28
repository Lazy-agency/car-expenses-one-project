package lazy.agency.spring.carexpenses.rest.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public ResponseEntity<String> getValue(){
        return new ResponseEntity<String>("Web service is working", HttpStatus.OK);
    }
}
