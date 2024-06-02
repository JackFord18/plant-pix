package com.jackfordtech.plantpix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlantPixServiceImpl implements PlantPixService {
    @Autowired
    S3Client s3Client;

    public void uploadImage(MultipartFile file) throws IOException {
        String defaultImageType = "jpg";
        List<String> acceptableImageTypes = List.of(defaultImageType, "jpeg");
        List<String> acceptableContentTypes = acceptableImageTypes.stream().map((s) -> String.format("image/%s", s)).toList();

        if(!acceptableContentTypes.contains(file.getContentType())){
            throw new InvalidMediaTypeException(Objects.requireNonNull(file.getContentType()), String.format("Content type was found to be %s, but must be one of: %s", file.getContentType(), acceptableContentTypes));
        }

        byte[] contents = file.getBytes();
        String awsKey = getAwsKey(defaultImageType);

        Map<String, String> metadata = new HashMap<>();
        s3Client.putObject(PutObjectRequest.builder().metadata(metadata).bucket("plantpix").key(awsKey).build(), RequestBody.fromBytes(contents));
    }

    private static String getAwsKey(String defaultImageType) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formattedDate = sdf.format(date);

        String year = formattedDate.substring(0,4);
        String month = formattedDate.substring(4,6);
        String day = formattedDate.substring(6,8);
        String hour = formattedDate.substring(8,10);
        String minute = formattedDate.substring(10,12);
        String second = formattedDate.substring(12,14);
        String millisecond = formattedDate.substring(14,17);
        return String.format("%s/%s/%s/%s/%s/%s%s.%s", year, month, day, hour, minute, second, millisecond, defaultImageType);
    }
}
