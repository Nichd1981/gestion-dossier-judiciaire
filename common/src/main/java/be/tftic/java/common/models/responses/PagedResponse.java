package be.tftic.java.common.models.responses;

import java.util.List;

public record PagedResponse<T> (
        List<T> content,
        Integer pageSize,
        Long totalElements,
        Integer totalPages
) { }
