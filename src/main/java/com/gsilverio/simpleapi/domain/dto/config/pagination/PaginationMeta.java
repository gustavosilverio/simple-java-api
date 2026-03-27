package com.gsilverio.simpleapi.domain.dto.config.pagination;

public record PaginationMeta(
        int page,
        int pageSize,
        long totalSize,
        int totalPages,
        boolean hasMoreItems,
        boolean hasLastPage,
        boolean hasNextPage
) {}