package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


// public 붙여야 하구나
// 순서 틀렸구나! <T, ID>
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    // name query 사용시에 어노테이션 필요
    @Query(value = "SELECT * FROM hospital WHERE sidoCdNm = :sidoCdNm AND sgguCdNm = :sgguCdNm AND pcrPsblYn = 'Y'", nativeQuery = true)
    public List<Hospital> mFindHospital(@Param("sidoCdNm") String sidoCdNm, @Param("sgguCdNm") String sgguCdNm);


    @Query(value = "SELECT distinct sidoCdNm FROM hospital order by sidoCdNm", nativeQuery = true)
    public List<String> mFindSidoCdNm();

    @Query(value = "SELECT distinct sgguCdNm FROM HOSPITAL WHERE sidoCdNm = :sidoCdNm order by sgguCdNm", nativeQuery = true)
    public List<String> mFindSggucdnm(@Param("sidoCdNm") String sidoCdNm);
}
