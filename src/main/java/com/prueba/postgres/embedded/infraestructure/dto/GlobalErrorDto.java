package com.prueba.postgres.embedded.infraestructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalErrorDto implements Serializable {
  
  @Schema(description = "Indicates the date of the error", example = "2025-09-21", requiredMode = RequiredMode.REQUIRED)
  private String date;
  
  @Schema(description = "Type of error that occurred", example = "404", requiredMode = RequiredMode.REQUIRED)
  private int code;
  
  @Schema(description = "Outgoing message", example = "The rate model you were looking for does not exist. Please enter another model.", requiredMode = RequiredMode.REQUIRED)
  private String message;
  
  @Schema(description = "The type of cause associated with the error returned", example = "Conflict", requiredMode = RequiredMode.REQUIRED)
  private String cause; 
  
  private static final long serialVersionUID = -688089042095135010L;

}
