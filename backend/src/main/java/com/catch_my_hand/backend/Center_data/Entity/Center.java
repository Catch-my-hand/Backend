package com.catch_my_hand.backend.Center_data.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Center {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id ;

    private String desertionNo; //유기번호
    private String happenDt; //접수일
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
    private String careAddr; //보호 장소
    private String orgNm; //관할 기관
    private String popfile; //이미지 파일
    private String filename; //썸네일이미지 파일

//GETTER, SETTER
public Integer getId() {return id;}

public void setId(Integer id) {this.id = id;}

public String getDesertionNo() { return desertionNo; }

public void setDesertionNo(String desertionNo) {this.desertionNo = desertionNo;}
    
public String getHappenDt() {return happenDt;}
    
public void setHappenDt(String happenDt) {this.happenDt = happenDt;}
   
public String getProcessState() {return processState;}

public void setProcessState(String processState) {this.processState = processState;}

public String getNoticeSdt() { return noticeSdt; }

public void setNoticeSdt(String noticeSdt) {this.noticeSdt = noticeSdt;}

public String getNoticeEdt() { return noticeEdt; }

public void setNoticeEdt(String noticeEdt) {this.noticeEdt = noticeEdt;}

public String getKindCd() {return kindCd;}

public void setKindCd(String kindCd) {this.kindCd = kindCd; }

public String getColorCd() {return colorCd; }

public void setColorCd(String colorCd) { this.colorCd = colorCd;}

public String getAge() {return age;}

public void setAge(String age) {this.age = age;}

public String getWeight() {return weight;}

public void setWeight(String weight) {this.weight = weight;}

public String getSexCd() {return sexCd;}

public void setSexCd(String sexCd) {this.sexCd = sexCd;}

public String getNeuterYn() {return neuterYn;}

public void setNeuterYn(String neuterYn) {this.neuterYn = neuterYn;}

public String getSpecialMark() { return specialMark; }

public void setSpecialMark(String specialMark) {this.specialMark = specialMark; }

public String getCareNm() {return careNm;}

public void setCareNm(String careNm) {this.careNm = careNm; }

public String getCareAddr() {return careAddr;}

public void setCareAddr(String careAddr) {this.careAddr = careAddr;}

public String getOrgNm() {return orgNm;}

public void setOrgNm(String orgNm) {this.orgNm = orgNm;}

public String getPopfile() {return popfile; }

public void setPopfile(String popfile) {this.popfile = popfile;}

public String getFilename() {return filename; }

public void setFilename(String filename) {this.filename = filename; }

    @Override
    public String toString() {
        return "Center [desertionNo =" + desertionNo + ", happenDt=" + happenDt + ", processState=" + processState + ", noticeSdt=" + noticeSdt + ", noticeEdt=" +noticeEdt +
                ", kindCd=" + kindCd + ", colorCd=" + colorCd + ", age=" + age + ", weight=" +weight + ", sexCd=" + sexCd + ", neuterYn=" + neuterYn + ", specialMark=" + specialMark +
                ", careNm=" + careNm + ", careAddr=" + careAddr + ", orgNm=" + orgNm + ", popfile=" + popfile + ", filename=" + filename + "]";
    }
}

    

