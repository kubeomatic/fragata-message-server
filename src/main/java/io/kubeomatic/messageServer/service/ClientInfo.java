package io.kubeomatic.messageServer.service;
//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;

public class ClientInfo {
    private static final String[] IP_HEADERS ={
            "X-Forward-FOR",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOUR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED",
            "HTTP_FORWARDED_FOUR",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };
    public static String getRequestIP(HttpServletRequest request){
        for (String header: IP_HEADERS){
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }
        return request.getRemoteAddr();


    }
}
