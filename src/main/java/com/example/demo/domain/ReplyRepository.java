package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ReplyRepository extends JpaRepository<Reply, Integer> {


    @Modifying
    @Query(value = "INSERT INTO reply(qnaId, content, createDate) VALUES(?1, ?2, now())", nativeQuery = true)
    public int mSave(int qnaId, String content);

}
