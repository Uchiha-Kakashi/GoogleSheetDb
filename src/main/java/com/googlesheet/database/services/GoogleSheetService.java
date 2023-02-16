package com.googlesheet.database.services;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.googlesheet.database.configurations.GoogleSheetConfigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service

public class GoogleSheetService {
    private final String spreadSheetId = "16h0r0IC4Ax5vJRDPIN6jHEYiJrD2zzfPXXvc6LA7kzw";

    @Autowired private GoogleSheetConfigurations googleSheetConfigurations;

    public void saveDetails(String name, String mobNum){

        try {
            Sheets googleSheet = googleSheetConfigurations.getSheets();
            String range = "Sheet1!A:B";
            ValueRange body = new ValueRange().setValues(Arrays.asList(
                    Arrays.asList(name, mobNum)
            ));

            AppendValuesResponse result = googleSheet.spreadsheets().values()
                    .append(spreadSheetId, range, body)
                    .setValueInputOption("RAW")
                    .setInsertDataOption("INSERT_ROWS")
                    .setIncludeValuesInResponse(true)
                    .execute();
        } catch (Exception e){
            System.out.println("Exception occurred " + e);
        }

        System.out.println("Data entered successfully");
    }
}
