package com.example.event_management_app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationInfo {
    private Long totalElements;
    private Long currentPage;
    private Long pageSize;
    private Long totalPages;
}
