package com.project.it_job.util;

import com.project.it_job.exception.ParamExceptionHandler;
import com.project.it_job.request.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageCustomHelpper {
    private final IntegerHelpper integerHelpper;

    public PageRequestCustom validatePageCustom(PageRequestCustom pageRequestCustom) throws ParamExceptionHandler {
        //        Check xem pageSize và pageNumber có phải int không
        int pageSize = integerHelpper.parseIntOrThrow(pageRequestCustom.getPageSize(),"pageSize");
        int pageNumber = integerHelpper.parseIntOrThrow(pageRequestCustom.getPageSize(),"pageNumber");


//        Trường hợp keyword = null
        if (pageRequestCustom.getKeyword() == null) {
            pageRequestCustom.setKeyword("");
        }else{
//            set pageSize mặc định khi có keyword và pageSize không được truyền
            if (pageSize == 0) {
                pageRequestCustom.setPageSize(10);
            }
        }

        if (pageRequestCustom.getSortBy() == null || pageRequestCustom.getSortBy().isBlank()) {
            pageRequestCustom.setSortBy("createdAtDesc");
        }else{
            if (pageSize == 0) {
                pageRequestCustom.setPageSize(10);
            }
        }

        if(pageNumber == 0){
            pageRequestCustom.setPageNumber(1);
        }

//        truyền pageSize không hợp lệ ( > 0 mới tính)
        if (pageSize < 0)
            throw new ParamExceptionHandler("Truyền pageSize không hợp lệ!");

        return  pageRequestCustom;
    }
}