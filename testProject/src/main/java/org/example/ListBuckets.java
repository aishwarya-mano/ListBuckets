package org.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import java.util.List;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.nio.charset.StandardCharsets;
public class ListBuckets {
    public static void main(String[] args) {
        String bucket_name="mytestingbucket94";
        String object_key="test.txt";
        String content="Testing the permission";

        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();
        try{
            PutObjectRequest putObjectRequest=PutObjectRequest.builder().bucket(bucket_name).key(object_key).build();
            s3.putObject(putObjectRequest, RequestBody.fromString(content, StandardCharsets.UTF_8));
            System.out.println("Object created successfully");

        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }
        listAllBuckets(s3);
    }

    public static void listAllBuckets(S3Client s3) {
        ListBucketsResponse response = s3.listBuckets();
        List<Bucket> bucketList = response.buckets();
        for (Bucket bucket : bucketList) {
            System.out.println(bucket.name());
        }
    }
}
