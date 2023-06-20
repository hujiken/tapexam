package com.thv.tapexam.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DivisionRequest {
    private Integer id;
    private String name;
    private String code;
    private Boolean enable;
}
