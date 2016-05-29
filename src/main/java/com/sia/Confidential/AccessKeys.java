package com.sia.Confidential;

import com.amazonaws.auth.BasicAWSCredentials;

public class AccessKeys {
    public static String HPKey = "";
 
    public static String dynamoAccessKey = "";
    public static String dynamoSecretKey = "";
 
    public static BasicAWSCredentials dynamoAwsCreds = new BasicAWSCredentials(AccessKeys.dynamoAccessKey, AccessKeys.dynamoSecretKey);
}
