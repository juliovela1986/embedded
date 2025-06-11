package com.prueba.postgres.embedded.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PruebaDto implements Serializable {

    private Long idPrueba;

    private String code;

    private String description;

    private Boolean isDefault;

    private Boolean active;

    @Serial
    private static final long serialVersionUID = 3839567896861950089L;
}