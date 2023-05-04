//package br.com.clusterlab.restservice.health;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//@ResponseBody
//@ResponseStatus(HttpStatus.OK)
//@Controller
//public class AliveController {
//    @GetMapping("/health/alive")
//    public String alive()
//    {
//        try
//        {
//            System.out.println("Test if is alive");
//        } catch ( Exception e)
//        {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return "Healthy";
//    }
//}
