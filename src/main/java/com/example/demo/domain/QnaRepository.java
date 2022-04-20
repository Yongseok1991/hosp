package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface QnaRepository extends JpaRepository<Qna, Integer> {

    @Query(value="UPDATE qna SET content = ?1, title = ?2, updateDate = now() where id = ?3", nativeQuery = true)
    @Modifying
    public int mUpdateQna(String content, String title, int id);


    @Query(value="UPDATE Qna a SET a.count = a.count +1 WHERE id = ?1", nativeQuery = true)
    @Modifying
    public int qnaCount(Integer id);

    @Query(value="SELECT Q.id id, (SELECT count(*) FROM reply WHERE qnaId = Q.id) slength FROM qna Q", nativeQuery = true)
    public List<Map<String, Object>> replyCount();
}
