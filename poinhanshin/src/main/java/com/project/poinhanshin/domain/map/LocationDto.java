package com.project.poinhanshin.domain.map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LocationDto {
    private Integer locationno; // PK, 지역번호
    private String location;    // 지역

    /* DB location table
     * 1 / 영등포
     * 2 / 상성동
     * 3 / 미선동
     * 4 / 권선동
     */

    public LocationDto(){}

    public LocationDto(Integer locationno, String location) {
        this.location = location;
        this.locationno = locationno;
    }
}
