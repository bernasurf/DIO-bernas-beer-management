package au.com.worryfreeconsulting.DIObernasbeermanagement.service;

import au.com.worryfreeconsulting.DIObernasbeermanagement.dto.BeerDTO;
import au.com.worryfreeconsulting.DIObernasbeermanagement.entity.Beer;
import au.com.worryfreeconsulting.DIObernasbeermanagement.exception.BeerAlreadyRegisteredException;
import au.com.worryfreeconsulting.DIObernasbeermanagement.exception.BeerNotFoundException;
import au.com.worryfreeconsulting.DIObernasbeermanagement.exception.BeerStockExceededException;
import au.com.worryfreeconsulting.DIObernasbeermanagement.mapper.BeerMapper;
import au.com.worryfreeconsulting.DIObernasbeermanagement.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

//   cria/cadastra a cerveja no sistema usando o Data Type Object e o Mapper que criamos
    public BeerDTO createBeer(BeerDTO beerDTO) {

        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }

    /* retorna objeto cerveja se encontrado ou lança exceção com string dizendo que não
    localizou cerveja com aquele nome no cadastro*/
    public BeerDTO findByName(String name) throws BeerNotFoundException {
        Beer foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name));
        return beerMapper.toDTO(foundBeer);
    }

    // retorna uma lista com todas as cervejas cadastradas
    public List<BeerDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // remove/deleta uma cerveja com o id passado

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }

    // checa se determinada cerveja com aquele id já está cadastrada no sistema e retorna a BeerNotFoundException se não encontrada
    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    // checa se uma cerveja com o nome passado já está cadastrada no sistema
    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(name);
        }
    }

    /* Adiciona/Aumento a quantidade de cerveja do id passado quando verificado as condiçoes do verifyIfExists. caso a quantidade
    seja superior ao estoque máximo lança a BeerStockExceededException */

    public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        Beer beerToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
            Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
            return beerMapper.toDTO(incrementedBeerStock);
        }
        throw new BeerStockExceededException(id, quantityToIncrement);
    }



}
