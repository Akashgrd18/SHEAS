package com.project.patients.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(
        name = "Response ",
        description = "Schema to hold Response status"
)
@Data
public class ResponseDto {

    private String statusCode;

    private String statusMsg;

    public ResponseDto(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
