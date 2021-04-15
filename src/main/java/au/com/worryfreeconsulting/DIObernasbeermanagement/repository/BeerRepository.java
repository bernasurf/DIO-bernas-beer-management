package au.com.worryfreeconsulting.DIObernasbeermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import au.com.worryfreeconsulting.DIObernasbeermanagement.entity.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByName(String name);

}
