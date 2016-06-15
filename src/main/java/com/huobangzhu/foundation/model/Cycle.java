package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.temporal.ChronoUnit;

/**
 * @author: Tim.Yao
 */
@Embeddable
@Setter
@Getter
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
@ToString
@EqualsAndHashCode
public class Cycle implements Comparable<Cycle>{

    public static final Cycle ZERO = Cycle.of(0);
    public static final Cycle FOREVER = Cycle.of(1, ChronoUnit.FOREVER);

    @ApiModelProperty(value = "Interval Value", required = true)
    @JsonProperty("_i")
    @NonNull
    private long interval;

    @ApiModelProperty(value = "Interval Unit", required = true, dataType = "enum")
    @JsonProperty("_u")
    @Enumerated
    private ChronoUnit unit = ChronoUnit.DAYS;

    public Cycle(){
    }

    public boolean gt(@NotNull Cycle cycle){
        return 0 < compareTo(cycle);
    }

    public boolean ge(@NotNull Cycle cycle){
        return -1 < compareTo(cycle);
    }

    public long toMillis(){
        return interval * unit.getDuration().toMillis();
    }

    public Cycle add(@NotNull Cycle augend){
        if(unit != augend.unit)
            throw new UnsupportedOperationException("The unit of 2 cycles must be the same.");
        return Cycle.of(interval + augend.interval, unit);
    }

    @Override
    public int compareTo(@NotNull Cycle o){
        long diff = toMillis() - o.toMillis();
        return 0 == diff ? 0 : 0 < diff ? 1 : -1;
    }
}
