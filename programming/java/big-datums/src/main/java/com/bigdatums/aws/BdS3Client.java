package com.bigdatums.aws;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class BdS3Client extends AmazonS3Client{

    public BdS3Client(){
        super(new EnvironmentVariableCredentialsProvider());
    }

    public BdS3Client(AWSCredentialsProvider awsCredentialsProvider){
        super(awsCredentialsProvider);
    }


    public List<S3ObjectSummary> getBucketObjectSummaries(String bucketName){

        List<S3ObjectSummary> s3ObjectSummaries = new ArrayList<S3ObjectSummary>();

        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
            ObjectListing objectListing;

            do {
                objectListing = this.listObjects(listObjectsRequest);
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    s3ObjectSummaries.add(objectSummary);
                }
                listObjectsRequest.setMarker(objectListing.getNextMarker());
            } while (objectListing.isTruncated());
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it " +
                    "to Amazon BdS3Client, but was rejected with an error response " +
                    "for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
                    "which means the client encountered " +
                    "an internal error while trying to communicate" +
                    " with BdS3Client, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return s3ObjectSummaries;
    }

    public List<String> getBucketObjectNames(String bucketName){
        List<String> s3ObjectNames = new ArrayList<String>();
        List<S3ObjectSummary> s3ObjectSummaries = getBucketObjectSummaries(bucketName);

        for(S3ObjectSummary s3ObjectSummary : s3ObjectSummaries){
            s3ObjectNames.add(s3ObjectSummary.getKey());
        }
        return s3ObjectNames;
    }

    public void printBucketObjectNames(String bucketName){
        List<String> s3ObjectNames = getBucketObjectNames(bucketName);
        for (String s3ObjectName : s3ObjectNames){
            System.out.println(s3ObjectName);
        }
    }

    public int deleteAllFromBucket(String bucketName){

        //get objects from bucket
        List<String> bucketKeys = getBucketObjectNames(bucketName);
        int numOfObjects = bucketKeys.size();

        //create DeleteObjectsRequest
        DeleteObjectsRequest dor = new DeleteObjectsRequest(bucketName);
        List<DeleteObjectsRequest.KeyVersion> deleteKeys = new ArrayList<DeleteObjectsRequest.KeyVersion>();

        //add items to DeleteObjectsRequest.
        for(int i=0; i<numOfObjects; i++) {
            String key = bucketKeys.get(i);
            deleteKeys.add(new DeleteObjectsRequest.KeyVersion(key));

            //delete objects (must delete 1000 or fewer per deleteObjects() call)
            if(i > 0 && ((i % 999) == 0 || i == (numOfObjects - 1))){
                dor.setKeys(deleteKeys);
                this.deleteObjects(dor);
                deleteKeys.clear();
            }

        }

        return numOfObjects;
    }

    public void putEmptyFiles(String bucketName, String prefix, String extension, int numFiles) throws IOException {

        for (int i = 0; i<numFiles; i++) {
            String filename = prefix + i + "." + extension;
            File file = new File(filename);
            file.createNewFile();
            this.putObject(bucketName, filename, file);
            file.delete();
        }

    }

    public static void main(String[] args){
        BdS3Client s3Client = new BdS3Client();
        String bucketName = "big-datums-tmp";
        s3Client.createBucket(bucketName);

        try {
            s3Client.putEmptyFiles(bucketName, "myTempFiles", "txt", 25);
        } catch (IOException e){
            System.out.println(e);
        }

        List<String> objectList = s3Client.getBucketObjectNames(bucketName);
        s3Client.printBucketObjectNames(bucketName);

    }


}