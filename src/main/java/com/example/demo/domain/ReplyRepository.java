package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ReplyRepository extends JpaRepository<Reply, Integer> {


    @Modifying
    @Query(value = "INSERT INTO reply(qnaId, content, createDate, writer) VALUES(?1, ?2, now(), ?3)", nativeQuery = true)
    public int mSave(int qnaId, String content, String writer);

    @Query(value="UPDATE reply SET content = ?1 , updateDate = now() where id = ?2", nativeQuery = true)
    @Modifying
    public int mUpdate(String content, int replyId);

    @Query(value="DELETE FROM reply WHERE qnaid = ?1", nativeQuery = true)
    @Modifying
    public int mDeleteReplyByQna(int qnaId);

}
