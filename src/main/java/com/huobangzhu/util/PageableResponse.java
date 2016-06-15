package com.huobangzhu.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;

/**
 * @author: Tim.Yao
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class PageableResponse<T>{

    @JsonProperty("records")
    private Collection<T> collection;

    @JsonProperty("queryRecordCount")
    @NonNull
    private int queryRecordCount;


    @JsonProperty("totalRecordCount")
    private Long totalRecordCount;




    @JsonCreator
    public static PageableResponse of(
            @JsonProperty(value = "records") Collection collection,
            @JsonProperty(value = "queryRecordCount", required = true) int queryRecordCount,
            @JsonProperty(value = "totalRecordCount", required = true) Long totalRecordCount
            ) {

        return new PageableResponse(collection, queryRecordCount, totalRecordCount);
    }




    public static PageableResponse empty() {
        return of(null, 0, Long.valueOf(0));
    }



}

