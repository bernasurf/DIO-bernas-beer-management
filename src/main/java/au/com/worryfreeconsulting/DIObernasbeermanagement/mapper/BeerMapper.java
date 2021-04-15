package au.com.worryfreeconsulting.DIObernasbeermanagement.mapper;

import au.com.worryfreeconsulting.DIObernasbeermanagement.dto.BeerDTO;
import au.com.worryfreeconsulting.DIObernasbeermanagement.entity.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);

}
