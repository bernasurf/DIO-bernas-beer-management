package au.com.worryfreeconsulting.DIObernasbeermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerStockExceededException extends Exception{
    public BeerStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Cannot increment Beer with ID: %s because the amount to increment %s exceeds the max stock capacity", id, quantityToIncrement));
    }
}
