package com.draen.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<T, R> {
    T toEntity(R dto);
    R toDto(T entity);

    default List<T> toEntities(Collection<R> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
    default List<R> toDtos(Collection<T> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
