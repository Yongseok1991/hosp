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
    private Integer id;
    private String cmpnmNm;
    private String dataStdEd;
    private String refineLotnoAddr;
    private String refineRoadNmAddr;
    private String sigunNm;
    private String liveYn;
    private String indutypeNm;
}
