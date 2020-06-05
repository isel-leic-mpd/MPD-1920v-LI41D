package pt.isel.mpd.weather.dto;

public class SearchApiResultDto {
    private final ContainerDto[] areaName;
    private final ContainerDto[] country;
    private final ContainerDto[] region;
    private final String latitude;
    private final String longitude;

    public SearchApiResultDto(ContainerDto[] areaName, ContainerDto[] country, ContainerDto[] region, String latitude, String longitude) {
        this.areaName = areaName;
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ContainerDto[] getAreaName() {
        return areaName;
    }

    public ContainerDto[] getCountry() {
        return country;
    }

    public ContainerDto[] getRegion() {
        return region;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
