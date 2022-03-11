package com.example.demo.web;


import com.example.demo.domain.Kyonggi;
import com.example.demo.domain.KyonggiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Controller
public class KyonggiController {

    private final KyonggiRepository kyonggiRepository;
    @GetMapping("/list")
    public String kyonggiList(Model model) {
        model.addAttribute("sigunNms", kyonggiRepository.mFindSigunNm());
        return "list";
    }

    @GetMapping("/api/kyonggi")
    public @ResponseBody
    Page<Kyonggi> kyonggi(String sigunNm, String cmpnmNm, @PageableDefault(size= 10) Pageable pageable) {
        return kyonggiRepository.findAllBySigunNmAndCmpnmNmContainingOrderByCmpnmNm(sigunNm, cmpnmNm, pageable);
    }

}
