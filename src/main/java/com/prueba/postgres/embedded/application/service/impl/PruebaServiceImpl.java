package com.prueba.postgres.embedded.application.service.impl;

import com.prueba.postgres.embedded.application.exception.ControllerException;
import com.prueba.postgres.embedded.application.exception.ValidationException;
import com.prueba.postgres.embedded.application.facade.service.IPruebaFacadeService;
import com.prueba.postgres.embedded.application.response.dto.PruebaResponseDto;
import com.prueba.postgres.embedded.infraestructure.config.AuditAwareImplConfig;
import com.prueba.postgres.embedded.infraestructure.convert.map.struct.IPruebaConvertMapStruct;
import com.prueba.postgres.embedded.infraestructure.dto.PaginationFeaturesDto;
import com.prueba.postgres.embedded.infraestructure.dto.PruebaDto;
import com.prueba.postgres.embedded.infraestructure.entity.Prueba;
import com.prueba.postgres.embedded.infraestructure.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PruebaServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PruebaServiceImpl.class);

    private final IPruebaFacadeService pruebaService;
    
    private final MessageConfigServiceImpl messageConfigService;

    private final AuditAwareImplConfig auditAwareConfig;

    public PruebaServiceImpl(IPruebaFacadeService pruebaService,
                               MessageConfigServiceImpl messageConfigService,
                               AuditAwareImplConfig auditAwareConfig) {
        this.pruebaService = pruebaService;
        this.messageConfigService = messageConfigService;
        this.auditAwareConfig = auditAwareConfig;
    }

    /**
     * Función encargada de listar todos los idiomas
     */
    public PruebaResponseDto getAllPrueba(PaginationFeaturesDto paginationFeatureDto) {
        try {
            LOGGER.info("Configuring pagination for Prueba");
            Pageable page = Utilities.configurarPageRequest(paginationFeatureDto.getPage(),
                    paginationFeatureDto.getSizePage(), paginationFeatureDto.getOrder(), paginationFeatureDto.getOrderField());

            LOGGER.info("Listing all the Prueba");
            Page<Prueba> listPrueba = pruebaService.getAllPrueba(page);
            LOGGER.info("Prueba obtained succesfully");

            return PruebaResponseDto.builder()
                    .pruebaDto(IPruebaConvertMapStruct.mapper.convertEntityToDto(listPrueba.getContent()))
                    .pageDto(Utilities.configPaginator(page, listPrueba))
                    .build();
        } catch (Exception e) {
            throw new ControllerException(messageConfigService.messaSource("error.prueba.list"));
        }
    }

    /**
     * Función encargada de actualizar el idioma
     */
    public void updatePrueba(PruebaDto pruebaDto) {
        if (pruebaService.pruebaExist(pruebaDto.getCode()) == 0) {
            LOGGER.error("The prueba with code: {} does not exist", pruebaDto.getCode());
            throw new ValidationException(messageConfigService.messaSource("error.prueba.update.not.exist", pruebaDto.getDescription()));
        }

        validDefaultLanguegeApplication(pruebaDto.getIsDefault(), pruebaDto.getIdPrueba(), false);

        try {
            LOGGER.info("The prueba with ID is updated: {}", pruebaDto.getIdPrueba());
            pruebaService.updatePruebaById(pruebaDto.getCode(), pruebaDto.getDescription(), pruebaDto.getIsDefault(),
                    pruebaDto.getIdPrueba(), auditAwareConfig.getCurrentUserId(), pruebaDto.getActive());
            LOGGER.info("Prueba registration updated successfully.");

            LOGGER.info("The previous default prueba in the application is updated to false.");
            pruebaService.updatePruebaDefault(pruebaDto.getIdPrueba(), auditAwareConfig.getCurrentUserId());
            LOGGER.info("Registration successfully updated.");

        } catch (Exception e) {
            throw new ControllerException(messageConfigService.messaSource("error.prueba.update"));
        }
    }

    /**
     * Función encargada de eliminar el idioma por su id
     */
    public void insertPrueba(PruebaDto pruebaDto) {
        if (pruebaService.pruebaExist(pruebaDto.getCode()) == 1) {
            throw new ValidationException(messageConfigService.messaSource("error.prueba.insert.exist"));
        }
        try {
            LOGGER.info("The prueba with Code: {}", pruebaDto.getCode());
            pruebaService.insertNewPrueba(IPruebaConvertMapStruct.mapper.convertDtoToEntity(pruebaDto));
            LOGGER.info("Prueba record successfully insert.");
            if(Boolean.TRUE.equals(pruebaDto.getIsDefault())) {
                LOGGER.info("The previous default prueba in the application is updated to false.");
                long idPrueba = pruebaService.obtainIdByCode(pruebaDto.getCode());
                pruebaService.updatePruebaDefault(idPrueba, auditAwareConfig.getCurrentUserId());
                LOGGER.info("Registration successfully updated.");
            }
        } catch (Exception e) {
            throw new ControllerException(messageConfigService.messaSource("error.prueba.insert"));
        }
    }

    /**
     * Función encargada de eliminar el idioma, para ello, debería de validar lo
     * siguiente: 1.- No existe ningún lenguaje asociado a la tabla
     * ServiceTraduction o RateModelTraduction En caso de que exista, no se podrá
     * eliminar el idioma y deberá de deshabilitarlo
     */
    public void deletePrueba(Long idPrueba, Boolean defaultPrueba) {
//        if (rateModelTraductionService.validateCountPruebaId(idPrueba) >= 1) {
//            throw new ValidationException(messageConfigService.messaSource("error.prueba.existing"));
//        }

        validDefaultLanguegeApplication(defaultPrueba, idPrueba, true);

//        if (serviceTraductionService.validateCounPruebaServiceId(idPrueba) >= 1) {
//            throw new ValidationException(messageConfigService.messaSource("error.prueba.existing"));
//        }

        LOGGER.info("The record with ID is deleted: {}", idPrueba);
        pruebaService.deletePruebaById(idPrueba);
        LOGGER.info("Record deleted successfully");
    }

    private void validDefaultLanguegeApplication(Boolean isDefault, long idPrueba, boolean delete) {
        // Validamos si se pretende desactivar el lenguaje de la aplicación, en el caso de que no existe ninguno más, no podrá realizar la acción
        if (( !Boolean.TRUE.equals(isDefault) || delete)
                && pruebaService.validatePruebaDefault(idPrueba) == 0) {
            LOGGER.info("You cannot disable the prueba, a default prueba must exist in the application.");
            throw new ValidationException(messageConfigService.messaSource("error.prueba.default"));
        }
    }


}
