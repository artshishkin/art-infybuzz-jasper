package net.shyshkin.study.jasper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coordinate {

    private Double xVal;
    private Double yVal;
    private String seriesName;

    public Double getxVal() {
        return xVal;
    }

    public Coordinate setxVal(Double xVal) {
        this.xVal = xVal;
        return this;
    }

    public Double getyVal() {
        return yVal;
    }

    public Coordinate setyVal(Double yVal) {
        this.yVal = yVal;
        return this;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public Coordinate setSeriesName(String seriesName) {
        this.seriesName = seriesName;
        return this;
    }
}
