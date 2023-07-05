package com.br.regUser.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Service
public class AmazonS3Service {
    @Value("${amazon.user.bucket}")
    private String bucketName;
    @Value("${amazon.user.accesskey}")
    private String accessKey;
    @Value("${amazon.user.secretkey}")
    private String secretKey;
    @Value("${amazon.user.location}")
    private String location;

    public String uploadFileToS3(MultipartFile file) {
        try{
            Date currentDate = new Date();
            String timestamp = String.valueOf(currentDate.getTime());
            String uniqueKey = file.getOriginalFilename() + "_" + timestamp;

            S3Client s3Client = S3Client.builder()
                    .region(Region.of(location))
                    .credentialsProvider(DefaultCredentialsProvider.create())
                    .build();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(uniqueKey)
                    .build();

            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());

            PutObjectResponse response = s3Client.putObject(request, requestBody);
            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(uniqueKey)).toExternalForm();
        } catch (IOException e){
            throw new RuntimeException("Update failed!!");
        }
    }

    public void deleteImageFromS3(String imageUrl) {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        S3Client s3Client = S3Client.builder()
                .region(Region.of(location))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        String imageName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);

        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .build();

        try {
            DeleteObjectResponse response = s3Client.deleteObject(deleteRequest);
            System.out.println("A imagem " + imageName + " foi excluída com sucesso do bucket " + bucketName);
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}