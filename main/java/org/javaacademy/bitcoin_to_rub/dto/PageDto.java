package org.javaacademy.bitcoin_to_rub.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PageDto<T> {
    @NonNull
    private Integer size;
    @NonNull
    private Integer totalSize;
    @NonNull
    private Integer startElementIndex;
    @NonNull
    private Integer endElementIndex;
    @NonNull
    private T content;
}
