package br.com.alanadiastech.apijunit5.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

    private LocalDateTime timesTemp;
    private  Integer Status;
    private  String error;
    private  String path;

}
