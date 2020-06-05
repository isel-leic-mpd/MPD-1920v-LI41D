package pt.isel.mpd.weather.dto;

public class LocationInfo {
    public final String areaName;
    public final String country;
    public final String region;
    public final double latitude;
    public final double longitude;

    public LocationInfo(String areaName, String country, String region, double latitude, double longitude) {
        this.areaName = areaName;
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
            "areaName='" + areaName + '\'' +
            "country='" + country + '\'' +
            ", region='" + region + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }

    public static LocationInfo valueOf(String line) {
        String[] data = line.split("\t");
        return new LocationInfo(
                data[0],
                data[1],
                data[2],
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4]));
    }
}
