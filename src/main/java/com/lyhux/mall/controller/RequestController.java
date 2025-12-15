package com.lyhux.mall.controller;

import com.lyhux.mall.dto.LoginDTO;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/res-data")
public class RequestController {



    @GetMapping("get-id")
    public String get(@RequestParam(defaultValue = "0") Integer id) {
        return "ID:" + id;
    }

    @GetMapping("get-id2")
    public String get2(@RequestParam(name = "id") String id, @RequestParam String name) {
        return "ID:" + id + "Name:" + name;
    }

    @GetMapping("get-all")
    public String getAll(@RequestParam Map<String, String> allParams) {
        return "Parameters are: " + allParams.entrySet();
    }

    public record GetVO(String id, String name) {}
    @GetMapping("get-record")
    public String getRecord(@ModelAttribute GetVO vo) {
        return "ID:" + vo.id + "Name:" + vo.name;
    }

    @PostMapping(value = "post-json",
            consumes = APPLICATION_JSON_VALUE,
            produces= APPLICATION_JSON_VALUE
    )
    public LoginDTO postJson(@RequestBody LoginDTO loginDTO) {
        System.out.printf("loginDTO=%s", loginDTO);
        return loginDTO;
    }

    @PostMapping(value = "post-url",
            consumes = APPLICATION_FORM_URLENCODED_VALUE,
            produces= APPLICATION_JSON_VALUE
    )
    public LoginDTO postUrlEncode(@ModelAttribute LoginDTO loginDTO) {
        System.out.printf("Url Encoding loginDTO=%s", loginDTO);
        return loginDTO;
    }

    @PostMapping(value = "post-meta",
            consumes = APPLICATION_JSON_VALUE,
            produces= APPLICATION_JSON_VALUE
    )
    public LoginDTO postMeta(@RequestParam String id, @RequestBody LoginDTO loginDTO) {
        System.out.printf("id=%s, loginDTO=%s", id, loginDTO);
        return loginDTO;
    }

    @PostMapping(value = "post-raw",
            produces= APPLICATION_JSON_VALUE
    )
    public String postRaw(@RequestBody String rawString) {
        System.out.printf("raw string data: %s", rawString);
        return rawString;
    }

    @PostMapping(value = "post-form",
            produces = MULTIPART_FORM_DATA_VALUE
    )
    public String postForm(
            @RequestParam("meta-data") String metadata,
                           @RequestParam("file-data") MultipartFile file) {
//        System.out.printf("multipart meta-data: %s, file: %s", metadata.content(), file.filename());
//        return String.format("multipart meta-data: %s, file: %s", metadata.content(), file.filename());
        System.out.printf("multipart metadata:%s file: %s", metadata, file.getOriginalFilename());
        return String.format("multipart file: %s", file.getOriginalFilename());
    }

    @PostMapping(value = "post-files",
            produces = MULTIPART_FORM_DATA_VALUE
    )
    public String postFiles(
            @RequestParam("files") MultipartFile[] files) {
//        System.out.printf("multipart meta-data: %s, file: %s", metadata.content(), file.filename());
//        return String.format("multipart meta-data: %s, file: %s", metadata.content(), file.filename());
        for (MultipartFile file : files) {
            System.out.printf("multipart file: %s, %d, %s", file.getOriginalFilename(), file.getSize(), file.getContentType());
        }
        return "multiply files uploads";
    }
}
