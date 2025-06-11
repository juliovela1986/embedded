package com.prueba.postgres.embedded.application.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageConfigServiceImpl {

	private final MessageSource messageSource;

	public MessageConfigServiceImpl(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Función responsable de devolver el mensaje de salida según el idioma del usuario que accede a la aplicación. 
	 */
	public String messaSource(String keyParam, Object... params) {
		return messageSource.getMessage(keyParam, params,
				(LocaleContextHolder.getLocale().getLanguage().contentEquals("ca")
						|| LocaleContextHolder.getLocale().getLanguage().contentEquals("es"))
				? new Locale.Builder().setLanguage(LocaleContextHolder.getLocale().getLanguage())
						.setRegion("ES").build()
						: Locale.ENGLISH);
	}

}
