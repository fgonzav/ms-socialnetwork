package cl.social.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResponseTemplateErrorHandler implements ResponseErrorHandler{

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        
        return response.getStatusCode().series() == Series.CLIENT_ERROR
                || response.getStatusCode().series() == Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        
        log.error("[handleError] response: " + response);
        
        /*if (response.getStatusCode().series() == Series.SERVER_ERROR) {
        
            log.error("[handleError] ");
        } else if (response.getStatusCode()
                .series() == Series.CLIENT_ERROR) {
                  // handle CLIENT_ERROR
                  if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
//                      throw new NotFoundException();
                  }
              }*/
        
    }

}
