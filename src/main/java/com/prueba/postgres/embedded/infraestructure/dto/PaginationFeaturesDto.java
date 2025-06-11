package com.prueba.postgres.embedded.infraestructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationFeaturesDto implements Serializable {

  @Schema(description = "Home page code", example = "1", requiredMode = RequiredMode.REQUIRED)
  @Min(value = 1, message = "The minimum value for the page is 1")
  @NotNull(message = "The page parameter must start with 1")
  private int page;

  @Schema(description = "Maximum number of records to display on the page", example = "10", requiredMode = RequiredMode.REQUIRED)
  @Min(value = 1, message = "The minimum value for page size is 1")
  @NotNull(message = "The page size parameter must start with 1")
  private int sizePage;

  @Schema(description = "Field to be ordered", example = "codeRateModel", requiredMode = RequiredMode.REQUIRED)
  private String orderField;

  @Schema(description = "Indicates ascending order 1, descending order 0 by the field being sorted", example = "0", requiredMode = RequiredMode.REQUIRED)
  private int order;

  private static final long serialVersionUID = 3991194536889392234L;

}
