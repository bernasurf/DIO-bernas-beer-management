package au.com.worry.DIObernasbeermanagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerType {

    ALE("Ale"),
    IPA("IPA"),
    LAGER("Lager"),
    MALZBIER("Malzbier"),
    STOUT("Stout"),
    WEISS("Weiss"),
    WITBIER("Witbier");

    private final String description;
}
