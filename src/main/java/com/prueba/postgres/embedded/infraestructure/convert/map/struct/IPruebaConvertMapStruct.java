package com.prueba.postgres.embedded.infraestructure.convert.map.struct;


import com.prueba.postgres.embedded.infraestructure.dto.PruebaDto;
import com.prueba.postgres.embedded.infraestructure.entity.Prueba;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPruebaConvertMapStruct {

  public IPruebaConvertMapStruct mapper = Mappers.getMapper(IPruebaConvertMapStruct.class);
  
  public List<PruebaDto> convertEntityToDto(List<Prueba> language);
  
  public PruebaDto convertEntityToDto(Prueba language);
  
  public Prueba convertDtoToEntity(PruebaDto languageDto);
  
}
