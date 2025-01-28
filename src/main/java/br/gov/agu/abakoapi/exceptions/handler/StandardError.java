package br.gov.agu.abakoapi.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Integer status;
    private Instant timeStamp;
    private String error;
    private String mensagem;
    private String path;

}
