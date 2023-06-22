package com.thv.tapexam.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionRequest {
    private Integer id;
    private String name;
    private String code;
    private String divisionCode;
    private Integer divisionPoint;
    private Integer totalPoint;
    private Boolean enable;
}
