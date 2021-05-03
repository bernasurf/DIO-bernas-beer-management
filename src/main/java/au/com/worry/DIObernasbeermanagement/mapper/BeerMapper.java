package au.com.worry.DIObernasbeermanagement.mapper;

import au.com.worry.DIObernasbeermanagement.dto.BeerDTO;
import au.com.worry.DIObernasbeermanagement.entity.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);

}
