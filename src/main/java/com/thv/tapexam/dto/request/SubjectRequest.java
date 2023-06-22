package com.thv.tapexam.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectRequest {
    private Integer id;
    private String name;
    private String code;
    private String divisionCode;
    private Boolean enable;
}
