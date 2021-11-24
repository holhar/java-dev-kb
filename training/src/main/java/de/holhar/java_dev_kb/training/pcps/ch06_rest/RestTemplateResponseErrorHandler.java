package de.holhar.java_dev_kb.training.pcps.ch06_rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == CLIENT_ERROR
                || response.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new HttpServerErrorException(response.getStatusCode(), response.getStatusText());
        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            final StringBuilder responseBody = new StringBuilder();
            try (final InputStreamReader isReader = new InputStreamReader(response.getBody());
                 final BufferedReader reader = new BufferedReader(isReader)) {
                String stringBuffer;
                while ((stringBuffer = reader.readLine()) != null) {
                    responseBody.append(stringBuffer);
                }
            }
            String errorMessage = String.format("%s - %s", response.getStatusText(), responseBody);
            throw new HttpClientErrorException(response.getStatusCode(), errorMessage);
        }
    }
}
