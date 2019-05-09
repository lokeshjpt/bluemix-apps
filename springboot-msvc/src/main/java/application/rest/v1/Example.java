package application.rest.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudant.client.api.CloudantClient;

@RestController
public class Example {

    @Autowired
    private CloudantClient client;

    @RequestMapping("v1")
    public @ResponseBody ResponseEntity<String> example() {
        List<String> list = new ArrayList<>();
        //return a simple list of strings
        list.add("Congratulations, your application is up and running");
        return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
    }

    @RequestMapping("v1/cloudant")
    public @ResponseBody ResponseEntity<String> cloudant(){
        List<String> list = new ArrayList<>();
        try {
            list = client.getAllDbs();
        } catch (NullPointerException e) {
            return new ResponseEntity<String>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Available databases : " + list.toString(), HttpStatus.OK);
    }

}
