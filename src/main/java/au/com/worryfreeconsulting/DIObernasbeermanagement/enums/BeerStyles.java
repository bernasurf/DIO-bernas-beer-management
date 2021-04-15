package au.com.worryfreeconsulting.DIObernasbeermanagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerStyles {

    ALE("Ale"),
    IPA("IPA"),
    LAGER("Lager"),
    MALZBIER("Malzbier"),
    STOUT("Stout"),
    WEISS("Weiss"),
    WITBIER("Witbier");

    private final String description;
}
