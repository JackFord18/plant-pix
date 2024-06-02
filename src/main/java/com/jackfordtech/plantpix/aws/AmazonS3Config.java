package com.jackfordtech.plantpix.aws;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class AmazonS3Config {

    @Value("${aws.sdk.access.key}")
    private String accessKey;

    @Value("${aws.sdk.access.key.secret}")
    private String secretKey;
 
    @Value("${aws.sdk.region}")
    private Region region;
 
    @Bean
    public S3Client s3Client() {

        AwsCredentials creds = AwsBasicCredentials.create(accessKey, secretKey);
        AwsCredentialsProvider provider = StaticCredentialsProvider.create(creds);

        S3Client s3Client = S3Client
                .builder()
                .credentialsProvider(provider)
                .region(region)
                .build();

        return s3Client;
    }
}