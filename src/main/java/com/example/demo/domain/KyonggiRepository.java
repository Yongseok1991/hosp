package com.example.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


// public 붙여야 하구나
// 순서 틀렸구나! <T, ID>
public interface KyonggiRepository extends JpaRepository<Kyonggi, Integer> {

    @Query(value = "SELECT distinct sigunNm FROM KYONGGI", nativeQuery = true)
    public List<String> mFindSigunNm();

    public Page<Kyonggi> findAllBySigunNmAndCmpnmNmContainingOrderByCmpnmNm(@Param("sigunNm") String sigunNm, @Param("cmpnmNm")String cmpnmNm, Pageable pageable);

}
