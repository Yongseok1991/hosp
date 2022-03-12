package com.example.demo.web;

import com.example.demo.domain.Qna;
import com.example.demo.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class QnaController {


    private final QnaService qnaService;

    @GetMapping("/qna")
    public String qna(Model model) {
        return "qna";
    }

    @ResponseBody
    @GetMapping("/api/qna")
    public Page<Qna> selectList(@PageableDefault(size=10) Pageable pageable) {
        Page<Qna> list = qnaService.QnaList(pageable);
       return list;
    }

    @GetMapping("/save")
    public String save(Qna qna) {
        return "save";
    }

    @PostMapping("/qnaApi")
    @ResponseBody
    public void saveForm(@RequestBody Qna qna) {
        qnaService.qnaWrite(qna);
    }

    @GetMapping("/qna/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        model.addAttribute("board", qnaService.boardDetail(id));
        return "detail";
    }

    @ResponseBody
    @PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
    public Map<String, Object> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        Map<String, Object> params = new HashMap<>();

        String fileRoot = "C:\\summernote_image\\"; // 저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

        String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {

            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
            params.put("url", "/summernoteImage/" + savedFileName);
            params.put("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
            params.put("responseCode", "error");
            e.printStackTrace();
        }
        return params;
    }


}
