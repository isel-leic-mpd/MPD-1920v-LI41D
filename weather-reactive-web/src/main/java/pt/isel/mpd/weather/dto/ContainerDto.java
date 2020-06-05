package pt.isel.mpd.weather.dto;

public class ContainerDto {
    private final String value;

    public ContainerDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
