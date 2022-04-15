package com.catch_my_hand.backend.Center_data.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Center {
    //@Id
    private Integer id ;

    private String happenPlace; //발견 장소
    private String processState; //상태
    private String noticeSdt; //공고일
    private String noticeEdt; //공고종료일
    private String kindCd; //품종
    private String colorCd; //색깔
    private String age; //나이
    private String weight; //무게
    private String sexCd; //성별
    private String neuterYn; //중성화 여부
    private String specialMark; //특징
    private String careNm; //보호소 명
    private String careTel; //보호소 전화번호
    private String careAddr; //보호 장소
    private String popfile; //이미지 파일
    private String filename; //썸네일 이미지 파일


    @Override
    public String toString() {
        return "Center [ id= " + id + ", happenPlace= + " + happenPlace + ", processState=" + processState + ", noticeSdt=" + noticeSdt + ", noticeEdt=" +noticeEdt +
                ", kindCd=" + kindCd + ", colorCd=" + colorCd + ", age=" + age + ", weight=" +weight + ", sexCd=" + sexCd + ", neuterYn=" + neuterYn + ", specialMark=" + specialMark +
                ", careNm=" + careNm + ", careTel=" + careTel + ", careAddr=" + careAddr + ", popfile=" + popfile + ", filename=" + filename + "]";
    }

}


    

