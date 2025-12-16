package com.project.codinviec.util.helper;

import com.project.codinviec.exception.common.ParamExceptionHandler;
import com.project.codinviec.request.PageRequestCompany;
import com.project.codinviec.request.PageRequestCustom;
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

    public PageRequestCompany validatePageCompany(PageRequestCompany pageRequestCompany) throws ParamExceptionHandler {
        // Check xem pageSize và pageNumber có phải int không

        int pageSize = integerHelper.parseIntOrThrow(pageRequestCompany.getPageSize(), "pageSize");
        int pageNumber = integerHelper.parseIntOrThrow(pageRequestCompany.getPageNumber(), "pageNumber");
        int minEmployees = integerHelper.parseIntOrThrow(pageRequestCompany.getMinEmployees(), "minEmployees");
        int maxEmployees = integerHelper.parseIntOrThrow(pageRequestCompany.getMaxEmployees(), "maxEmployees");

        if (maxEmployees == 0) {
            pageRequestCompany.setMaxEmployees(Integer.MAX_VALUE);
        }

        // Trường hợp keyword = null
        if (pageRequestCompany.getKeyword() == null) {
            pageRequestCompany.setKeyword("");
        } else {
            // set pageSize mặc định khi có keyword và pageSize không được truyền
            if (pageSize == 0) {
                pageRequestCompany.setPageSize(10);
            }
        }
        if (pageRequestCompany.getLocation() == null || pageRequestCompany.getLocation().isBlank()) {
            pageRequestCompany.setLocation("");
        } else {
            if (pageSize == 0) {
                pageRequestCompany.setPageSize(10);
            }
        }

        if (pageNumber == 0) {
            pageRequestCompany.setPageNumber(1);
        }

        // truyền pageSize không hợp lệ ( > 0 mới tính)
        if (pageSize < 0)
            throw new ParamExceptionHandler("Truyền pageSize không hợp lệ!");
        if (minEmployees < 0)
            throw new ParamExceptionHandler("Truyền minEmployees không hợp lệ!");

        if (maxEmployees < 0)
            throw new ParamExceptionHandler("Truyền maxEmployees không hợp lệ!");
        return pageRequestCompany;
    }
}
