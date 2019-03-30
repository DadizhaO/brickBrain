package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ErrorMessage;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HandlingAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        ErrorMessage errorMessage = new ErrorMessage("HTTP Status 401 - " + authException.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(errorMessage));
    }
}
