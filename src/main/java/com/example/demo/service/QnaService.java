package com.example.demo.service;

import com.example.demo.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class QnaService {

    private final QnaRepository qnaRepository;
    private final ReplyRepository replyRepository;
    @Transactional(readOnly = true)
    public Page<Qna> QnaList(Pageable pageable) {
        return qnaRepository.findAll(pageable);
    }

    @Transactional
    public void qnaWrite(Qna qna) {
        qnaRepository.save(qna);
    }
    @Transactional(readOnly = false)
    public Qna boardDetail(Integer id) {

        return qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패"));
    }

    @Transactional
    public void qnaDelete(Integer id) {
        qnaRepository.deleteById(id);
    }

    @Transactional
    public void qnaUpdate(Integer id, Qna requestQna) {
        Qna qna = qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패"));
        qna.setTitle(requestQna.getTitle());
        qna.setContent(requestQna.getContent());
    }

    @Transactional
    public void replyWrite(ReplyDTO replyDTO) {
        replyRepository.mSave(replyDTO.getQnaId(), replyDTO.getContent());
    }



}
