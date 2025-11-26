package com.project.it_job.util.helper;

import com.project.it_job.exception.common.ParamExceptionHandler;
import com.project.it_job.request.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageCustomHelper {
    private final IntegerHelper integerHelper;

    public PageRequestCustom validatePageCustom(PageRequestCustom pageRequestCustom) throws ParamExceptionHandler {
        // Check xem pageSize và pageNumber có phải int không
        int pageSize = integerHelper.parseIntOrThrow(pageRequestCustom.getPageSize(), "pageSize");
        int pageNumber = integerHelper.parseIntOrThrow(pageRequestCustom.getPageNumber(), "pageNumber");

        // Trường hợp keyword = null
        if (pageRequestCustom.getKeyword() == null) {
            pageRequestCustom.setKeyword("");
        } else {
            // set pageSize mặc định khi có keyword và pageSize không được truyền
            if (pageSize == 0) {
                pageRequestCustom.setPageSize(10);
            }
        }

        if (pageRequestCustom.getSortBy() == null || pageRequestCustom.getSortBy().isBlank()) {
            pageRequestCustom.setSortBy("createdAtDesc");
        } else {
            if (pageSize == 0) {
                pageRequestCustom.setPageSize(10);
            }
        }

        if (pageNumber == 0) {
            pageRequestCustom.setPageNumber(1);
        }

        // truyền pageSize không hợp lệ ( > 0 mới tính)
        if (pageSize < 0)
            throw new ParamExceptionHandler("Truyền pageSize không hợp lệ!");

        return pageRequestCustom;
    }
}
