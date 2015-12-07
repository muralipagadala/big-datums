package com.bigdatums.aws;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class BdS3ClientTest {

    @Test
    public void testS3FileCount() throws IOException{

        //BdS3Client client
        BdS3Client s3Client = new BdS3Client();

        //create bucket for test
        String bucketName = "s3-count-test";
        s3Client.createBucket(bucketName);

        //push empty files to bucket
        int filesToPush = 1100;
        s3Client.putEmptyFiles(bucketName, "BdS3ClientTest-", "txt", filesToPush);

        //get number of items in bucket
        List<String> bucketKeys = s3Client.getBucketObjectNames(bucketName);
        int numOfObjects = bucketKeys.size();

        //test list contains all objects in bucket
        Assert.assertEquals(numOfObjects, filesToPush);

        //delete temp objects and bucket
        s3Client.deleteAllFromBucket(bucketName);
        s3Client.deleteBucket(bucketName);

    }

}
