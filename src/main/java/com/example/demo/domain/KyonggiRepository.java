package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


// public 붙여야 하구나
// 순서 틀렸구나! <T, ID>
public interface KyonggiRepository extends JpaRepository<Kyonggi, Integer> {

}
