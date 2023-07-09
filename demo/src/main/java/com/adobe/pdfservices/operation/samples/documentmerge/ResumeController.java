package com.adobe.pdfservices.operation.samples.documentmerge;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import com.adobe.pdfservices.operation.samples.documentmerge.advice.ApplicationExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.http.auth.InvalidCredentialsException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ResumeController implements java.io.Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResumeController.class);

    private static final String OUTPUT_FILENAME = "resume.pdf";

    @PostMapping(value = "/resume", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<byte[]> saveResume(@RequestBody ResumeRequest resumeRequest){
        // Convert ResumeRequest to JSON Object

        // Process and save the resumeJsonObject as needed

        byte[] contents = new byte[0];

        final ResponseEntity<byte[]> response;

        int flag;

        try {
            flag = 0;

            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.servicePrincipalCredentialsBuilder()
                    .withClientId("61a039208f1140b19e686cbbfbf25c5a")
                    .withClientSecret("p8e-SRms7rp3i9BLWF7FRruyv_oDneqp0ot-")
                    .build();

            Map<String, String> templateDictionary = new HashMap<>();

            // Add entries to the dictionary
            templateDictionary.put("1", "BasicTemplate.docx");
            templateDictionary.put("2", "LinkTemplate.docx");
            templateDictionary.put("3", "ImageTemplate.docx");


            if (!templateDictionary.containsKey(resumeRequest.getTemplate_id())) {
                String errorMessage = "404 - Template not found";
                LOGGER.error(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 - Template not found
            }

            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addSerializer(ResumeRequest.class, new resumeRequestSerializer());
            objectMapper.registerModule(module);
            System.out.println("Json Conversion: " + objectMapper.writeValueAsString(resumeRequest));

            // Setup input data for the document merge process
            JSONObject jsonDataForMerge = new JSONObject(objectMapper.writeValueAsString(resumeRequest));

            // Create an ExecutionContext using credentials.
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            // Create a new DocumentMergeOptions instance
            DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);

            // Create a new DocumentMergeOperation instance with the DocumentMergeOptions instance
            DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);


            // Set the operation input document template from a source file.
            String templateName = templateDictionary.get(resumeRequest.getTemplate_id());
            FileRef documentTemplate = FileRef.createFromLocalFile("src/main/resources/templates/" + templateName);
            documentMergeOperation.setInput(documentTemplate);

            // Execute the operation

            FileRef result = documentMergeOperation.execute(executionContext);

            // Save the result to the specified location.
            String outputFilePath = createOutputFilePath(templateName);
//            result.saveAs(outputFilePath); //This is in-case you want to save the file in any requried file

            // output the created file as a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            result.saveAs(byteArrayOutputStream);
            contents = byteArrayOutputStream.toByteArray();


        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error(" 500 - Internal server error", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (HttpMessageNotReadableException ex) {
            LOGGER.error("Error parsing JSON request: {}", ex.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            LOGGER.error("400 - Bad request", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 - Bad request
        }

        System.out.println("Received Resume Request: " + resumeRequest);

        response = new ResponseEntity<>(contents, createHttpHeadersForOutputFile(), HttpStatus.OK);
        return response;
    }

    private HttpHeaders createHttpHeadersForOutputFile() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(OUTPUT_FILENAME, OUTPUT_FILENAME);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return headers;
    }

    //Generates a string containing a directory structure and file name for the output file.
    public static String createOutputFilePath(String templateName) {
        return ("output/MergeDocumentToPDF/merge" + templateName + ".pdf");
    }
}