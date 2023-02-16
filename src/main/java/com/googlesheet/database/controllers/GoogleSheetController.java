package com.googlesheet.database.controllers;

import com.googlesheet.database.services.GoogleSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import org.springframework.util.StreamUtils;
import java.nio.charset.StandardCharsets;
import java.io.*;

@RestController
@RequestMapping("/googleSheetDb")
public class GoogleSheetController {

    private final ResourceLoader resourceLoader;

    @Autowired private GoogleSheetService googleSheetService;

    public GoogleSheetController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/displayForm")
    public String getFormPage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/form.html");
        InputStream inputStream = resource.getInputStream();
        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
        String htmlContent = new String(bytes, StandardCharsets.UTF_8);
        return htmlContent;
    }

    @GetMapping("/saveDetails")
    public void saveDetails(@RequestParam("name") String name, @RequestParam("mobile") String mobNum){
        googleSheetService.saveDetails(name, mobNum);
    }
}
