package com.thv.tapexam.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionRequest {
    private Integer id;
    private String code;
    private Integer divisionId;
    private Integer divisionPoint;
    private Integer totalPoint;
    private Boolean enable;
}
