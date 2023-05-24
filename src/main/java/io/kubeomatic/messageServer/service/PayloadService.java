package io.kubeomatic.messageServer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubeomatic.messageServer.dto.action.PayloadAction;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class PayloadService {
    static Logger logger = LoggerFactory.getLogger(Message.class);
    public static String getResponse(PayloadAction payloadAction, HttpServletRequest request, Authentication authentication){
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
                            ", Owner=" + payloadAction.getOwner() +
                            ", Action=" + payloadAction.getAction() +
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
