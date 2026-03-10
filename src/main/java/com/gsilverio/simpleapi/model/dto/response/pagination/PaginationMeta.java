package com.gsilverio.simpleapi.model.dto.response.pagination;

public record PaginationMeta(
        int page,
        int pageSize,
        long totalSize,
        int totalPages,
        boolean hasMoreItems,
        boolean hasLastPage,
        boolean hasNextPage
) {}