package com.prueba.postgres.embedded.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class PageDto implements Serializable {

	// Número de elementos que se muestran por página
	private Integer pageSize;

	// Página mostrada de entre todas las que tiene la búsqueda
	private Integer currentPage;

	// Número de pages que tiene la búsqueda
	private Integer pages;

	// Número total de elementos que tiene la búsqueda
	private Integer records;

	// Inicializamos el paginador con valores por defecto
	public PageDto() {
		this.pageSize = 10;
		this.currentPage = 1;
		this.pages = 0;
		this.records = 0;
	}

	public PageDto(Integer currentPage) {
		this();
		this.currentPage = currentPage;
	}

	public PageDto(Integer records, Integer pageSize) {
		this();
		this.records = records;
		this.pageSize = pageSize;
		this.pages = this.getPages();
	}

	public boolean isStart() {
		return this.currentPage == 1;
	}

	public boolean isEnd() {
		return this.getPages() == 0 || this.currentPage.equals(this.pages);
	}

	public Integer getPages() {
		if (records != null) {
			this.pages = (int) Math.ceil((double) records / (double) pageSize);
		} else {
			this.pages = 0;
		}
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	@Serial
	private static final long serialVersionUID = -1047203424550875364L;

}
