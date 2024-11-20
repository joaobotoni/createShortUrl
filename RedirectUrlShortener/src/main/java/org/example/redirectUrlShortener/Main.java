package org.example.redirectUrlShortener;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    private final S3Client s3Client = S3Client.builder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> response = new HashMap<>();

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {

        String pathParameters = (String) input.get("rawPath"); // Obtém o valor do parâmetro de caminho "raw-path" da rota requisitada.

        // Verifica se pathParameters existe e não é vazio
        if (pathParameters == null || pathParameters.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: 'rawPath' is required.");
        }

        String shortUrlCode = pathParameters.replace("/", ""); // Obtém o valor do UUID.

        if (shortUrlCode == null || shortUrlCode.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: 'shortUrlCode' is required.");
        }

        // Prepara a solicitação para buscar o dado no S3
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket("url-shortener-storage-testbot")
                .key(shortUrlCode + ".json")
                .build();

        InputStream s3Stream;

        // Tenta buscar o dado do S3 e tratar erros
        try {
            s3Stream = s3Client.getObject(request);
        } catch (AwsServiceException e) {
            throw new RuntimeException("Error fetching data from S3: " + e.getMessage(), e);
        }

        UrlData data;

        // Deserializa o JSON para o objeto UrlData
        try {
            data = objectMapper.readValue(s3Stream, UrlData.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing URL data: " + e.getMessage(), e);
        }

        // Verifica se o URL expirou
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;

        // Caso o URL tenha expirado, retorna um código 410
        if ( data.getExpirationTime() < currentTimeInSeconds ) {
            response.put("statusCode", 410); // Código 410 (Gone) para URL expirado
            response.put("body", "The short URL has expired.");
            return response;

        }
            response.put("statusCode", 302); // Código de status HTTP para redirecionamento
            Map<String, String> headers = new HashMap<>();
            headers.put("Location", data.getOriginalUrl()); // URL de redirecionamento
            response.put("headers", headers);

        return response; // Retorna resposta para URL expirado
    }
}
