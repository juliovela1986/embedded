package com.prueba.postgres.embedded.infraestructure.util;

import com.prueba.postgres.embedded.infraestructure.dto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Utilities {

	private Utilities() {
		// Constructor vacío
	}

  /**
   * Listado de URL permitidas dentro de la aplicación
   */
  public static final List<String> publicRoutes = Arrays.asList("/swagger-ui/**", "/api-docs/**", "/logout/**",
      "/h2-console/**", "/r/p/**", "/tercer/generateToken");
//  , "/**"

//	public static Cookie clearCookie() {
//		Cookie c = new Cookie(TariffConstants.JSESSIONID, "");
//		c.setMaxAge(0);
//		c.setPath(TariffConstants.SLASH);
//		c.setHttpOnly(true);
//		c.setSecure(true);
//		return (c);
//	}

	/**
	 * Función para crear el paginador
	 * 
	 * @param <T>
	 * @param paginador
	 * @param pageable
	 * @param listPage
	 */
	public static <T> PageDto configPaginator(Pageable pageable, Page<T> listPage) {
		PageDto paginador = new PageDto();
		configurarPaginador(paginador, pageable);
		paginador.setRecords((int) listPage.getTotalElements());
		return (listPage.isFirst() && listPage.isLast() && listPage.getNumberOfElements() < 5) ? null : paginador;
	}

	/**
	 * Configuramos el paginador
	 * 
	 * @param paginador
	 * @param page
	 */
	public static void configurarPaginador(PageDto paginador, Pageable page) {
		paginador.setCurrentPage(page.getPageNumber() + 1);
		paginador.setPageSize(page.getPageSize());
	}

	/**
	 * Función para el paginador de forma global
	 *
	 * @param paginador
	 * @param tamanioPagina
	 * @param orden
	 * @param campo
	 * @return
	 * @throws ReservaException
	 **/
	public static Pageable configurarPageRequest(int page, int sizePage, int order, String field) {
		return field.isEmpty() ? PageRequest.of(page - 1, sizePage) : 
		    PageRequest.of(page - 1, sizePage, confSort(order, field));
	}

	private static Sort confSort (int order, String field) {
	  return order == 1 ? Sort.by(field).ascending() : Sort.by(field).descending();
	}

  
}
