package com.example.demo.service;

import com.example.demo.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


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
        qna.setCount(0);
        qnaRepository.save(qna);
    }
    @Transactional(readOnly = false)
    public Qna boardDetail(Integer id) {
        return qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패"));
    }

    @Transactional
    public void qnaDelete(Integer id) {
        replyRepository.mDeleteReplyByQna(id);
        qnaRepository.deleteById(id);
    }

    @Transactional
    public void qnaUpdate(Qna qna) {
        qnaRepository.mUpdateQna(qna.getContent(), qna.getTitle(), qna.getId());
    }


    @Transactional
    public void qnaUpdate(Integer id, Qna requestQna) {
        Qna qna = qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패"));
        qna.setTitle(requestQna.getTitle());
        qna.setContent(requestQna.getContent());
    }

    @Transactional
    public void replyWrite(ReplyDTO replyDTO) {
        log.info("\t+", replyDTO);
        replyRepository.mSave(replyDTO.getQnaId(), replyDTO.getContent(), replyDTO.getWriter());
    }

    @Transactional
    public void replyUpdate(ReplyDTO replyDTO) {
        replyRepository.mUpdate(replyDTO.getContent(), replyDTO.getReplyId());
    }

    @Transactional
    public void replyDelete(Integer id) {
        replyRepository.deleteById(id);
    }


    @Transactional
    public int boardCount(Integer id) {
       return qnaRepository.qnaCount(id);
    }


    @Transactional
    public List<Map<String, Object>> replyCount() {
        return qnaRepository.replyCount();
    }
}
