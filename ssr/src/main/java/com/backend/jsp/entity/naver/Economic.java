package com.backend.jsp.entity.naver;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Economic {

    private Integer economicUid;
    private String code;
    private String eType;
    private String stockDate;
    private Integer openVol;
    private Integer highVol;
    private Integer lowVol;
    private Double closeVol;
    private Integer volume;
    private Integer changeVol;
    private String createAt;
}
