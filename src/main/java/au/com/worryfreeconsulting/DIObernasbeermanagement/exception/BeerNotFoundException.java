package au.com.worryfreeconsulting.DIObernasbeermanagement.exception;

public class BeerNotFoundException extends Exception {

    // retorna essa string em caso de não encontrar cadastrada a cerveja com o nome fornecido
    public BeerNotFoundException(String beerName) {
        super(String.format("Cerveja com o nome %s não encontrada no cadastro", beerName));
    }

    //retorna string em case de nao encontrar cerveja com o id fornecido
    public BeerNotFoundException(Long id) {
        super(String.format("Cerveja com o id %s não encontrada no cadastro", id));
    }

}
