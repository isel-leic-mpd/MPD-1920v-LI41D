package pt.isel.mpd.weather.dto;

public class SearchApiDto {
    private final SearchApiResultDto[] result;

    public SearchApiDto(SearchApiResultDto[] result) {
        this.result = result;
    }

    public SearchApiResultDto[] getResult() {
        return result;
    }
}
