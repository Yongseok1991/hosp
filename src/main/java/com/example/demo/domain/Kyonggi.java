package com.example.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Kyonggi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // auto_increment
    private String sigunNm;
    private String cmpnmNm;
    private String indutypeNm;
    private String refineRoadnmAddr;
    private String refineLotnoAddr;
    private String dateStdEd;
}
