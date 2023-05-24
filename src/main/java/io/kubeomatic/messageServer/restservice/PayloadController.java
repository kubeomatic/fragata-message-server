package io.kubeomatic.messageServer.restservice;

import io.kubeomatic.messageServer.dto.action.PayloadAction;
import io.kubeomatic.messageServer.service.ClientInfo;
import io.kubeomatic.messageServer.service.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

@RestController
public class PayloadController {
    static Logger logger = LoggerFactory.getLogger(Message.class);
        @PostMapping("/payload")
    public String payloadAction(@Valid @RequestBody PayloadAction payloadAction, HttpServletRequest request, Authentication authentication)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String username = authentication.getName();
            UUID uuid = UUID.randomUUID();
            payloadAction.setUuid(uuid.toString());
            String ip = ClientInfo.getRequestIP(request);
            String payload = mapper.writeValueAsString(payloadAction);
            logger.info(
                    "UUID="+ uuid +
                        ", Username=" + username +
                        ", SourceIp=" + ip +
                        ", Action=" + payloadAction.getAction() +
                        ", Environment=" + payloadAction.getEnvironment() +
                        ", Provider_Kind=" + payloadAction.getProvider() + "/" + payloadAction.getKind()
            );

            logger.debug(
                    "UUID="+ uuid +
                            ", Username=" + username +
                            ", SourceIp=" + ip +
                            ", Payload=" + payload
            );
            Message message = new Message();
            message.setServer_bind("tcp://localhost:5555");
            String response = message.SendRequest(payload);
            logger.debug(
                    "UUID="+ uuid +
                        ", Username=" + username +
                        ", SourceIp=" + ip +
                        ", Response=" + response
            );

            return response;
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
