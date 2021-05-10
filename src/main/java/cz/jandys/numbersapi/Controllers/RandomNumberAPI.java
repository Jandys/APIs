package cz.jandys.numbersapi.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomNumberAPI {

    Random rnd;
    @RequestMapping(value = "/rnd", produces = "text/html")
    public String rnd(@RequestParam(value = "l",defaultValue = "1000")int lenght){
        return randomNumber(lenght);
    }

    @RequestMapping(value = "/random", produces = "text/html")
    public String randomNumber(@RequestParam(value = "l", defaultValue = "100")int lenght){
        getRandom();
        return "<h1>Random Number Generator</h1><h2 style=\"color:darkgray\">Your generated number is:   <b id=\"number\" style=\"font-size:150%;color:black\">"+(rnd.nextInt(lenght)+1)+"</b></h2>";
    }

    private void getRandom() {
        if(rnd == null){
            rnd = new Random(System.currentTimeMillis());
        }
    }
}
