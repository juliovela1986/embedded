package com.prueba.postgres.embedded.application.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.prueba.postgres.embedded.infraestructure.dto.PageDto;
import com.prueba.postgres.embedded.infraestructure.dto.PruebaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PruebaResponseDto implements Serializable {
  
  private List<PruebaDto> pruebaDto;

  private PageDto pageDto;
  
  @Serial
  private static final long serialVersionUID = -3281246335380928887L;
  
}
