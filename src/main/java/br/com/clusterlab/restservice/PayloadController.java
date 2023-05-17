package br.com.clusterlab.restservice;

import br.com.clusterlab.dto.action.PayloadAction;
import br.com.clusterlab.service.ClientInfo;
import br.com.clusterlab.service.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PayloadController {
    static Logger logger = LoggerFactory.getLogger(Message.class);
        @PostMapping("/payload")
    public String payloadAction(@Valid @RequestBody PayloadAction payloadAction, HttpServletRequest request)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String payload = mapper.writeValueAsString(payloadAction);
            Message message = new Message();
            message.setServer_bind("tcp://localhost:5555");
            String payback = message.SendRequest(payload);
            String ip = ClientInfo.getRequestIP(request);
            logger.info("Source IP= " + ip);
            System.out.println(ip);
            return payback;
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
