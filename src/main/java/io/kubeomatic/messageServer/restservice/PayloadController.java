package io.kubeomatic.messageServer.restservice;

import io.kubeomatic.messageServer.dto.action.PayloadAction;
import io.kubeomatic.messageServer.service.ClientInfo;
import io.kubeomatic.messageServer.service.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubeomatic.messageServer.service.PayloadService;
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

    @PostMapping("/payload")
    public String payloadAction(@Valid @RequestBody PayloadAction payloadAction, HttpServletRequest request, Authentication authentication)
    {
        return PayloadService.getResponse(
                payloadAction,
                request,
                authentication
        );

    }
}
