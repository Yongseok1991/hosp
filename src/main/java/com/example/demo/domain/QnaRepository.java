package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface QnaRepository extends JpaRepository<Qna, Integer> {

    @Query(value="UPDATE qna SET content = ?1, title = ?2, updateDate = now() where id = ?3", nativeQuery = true)
    @Modifying
    public int mUpdateQna(String content, String title, int id);




}
