package au.com.worry.DIObernasbeermanagement.service;

import au.com.worry.DIObernasbeermanagement.dto.BeerDTO;
import au.com.worry.DIObernasbeermanagement.entity.Beer;
import au.com.worry.DIObernasbeermanagement.exception.BeerAlreadyRegisteredException;
import au.com.worry.DIObernasbeermanagement.exception.BeerNotFoundException;
import au.com.worry.DIObernasbeermanagement.exception.BeerStockExceededException;
import au.com.worry.DIObernasbeermanagement.mapper.BeerMapper;
import au.com.worry.DIObernasbeermanagement.repository.BeerRepository;
import au.com.worry.DIObernasbeermanagement.builder.BeerDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;

    @Test
    void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
        // given a DTO - usando a classe DTOBuilder para "gerar" uma cerveja padrao e retorna um beerDTO
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedSavedBeer = beerMapper.toModel(expectedBeerDTO);

        // when utilizando o import statico do mockito.when
        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

        //then
        BeerDTO createdBeerDTO = beerService.createBeer(expectedBeerDTO);
        //checa se o ID, nome e quantidade da nova cerveja criada e igual ao da "esperada" - usando o hamcrest
        assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId())));
        assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName())));
        assertThat(createdBeerDTO.getQuantity(), is(equalTo(expectedBeerDTO.getQuantity())));
        // checa se a quantidade de cerveja a ser criada 'e maior do que 0
        assertThat(createdBeerDTO.getQuantity(), is(greaterThan(0)));

    }

}
