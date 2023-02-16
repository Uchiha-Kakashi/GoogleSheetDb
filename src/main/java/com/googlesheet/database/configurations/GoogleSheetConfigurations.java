package com.googlesheet.database.configurations;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.security.GeneralSecurityException;


import com.google.api.client.json.JsonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleSheetConfigurations {

    @Value("${credentials.file.path}")
    private String credentialsFilePath;
    @Value("${tokens.directory.path}")
    private String tokensDirectoryPath;

    public Credential getGoogleCredentials() throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("googleSheet_credentials.json");

        if(inputStream == null){
            System.out.println("cred file not found!!");
            return null;
        }

        return GoogleCredential.fromStream(inputStream)
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

    }

    public Sheets getSheets() throws GeneralSecurityException, IOException {
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), getGoogleCredentials())
                .setApplicationName("rupicard-database")
                .build();
    }

}