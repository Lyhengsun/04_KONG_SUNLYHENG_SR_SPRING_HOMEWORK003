package com.example.event_management_app.util;

import com.example.event_management_app.model.dto.response.PaginationInfo;

public class PaginationTools {
    public static PaginationInfo createPaginationInfo(Long totalCount, Long page, Long size) {
        Long totalPage = (totalCount / size) + (totalCount % size > 0 ? 1 : 0);
        PaginationInfo paginationInfo = new PaginationInfo(totalCount, page, size, totalPage);
        return paginationInfo;
    }
}
