//package com.nyash.travellizermono.api.common.model.search.criteria.range;
//
//
//import com.nyash.travellizermono.api.common.infra.util.Checks;
//import lombok.Getter;
//
///**
// * Pagination parameters for data retrieval operations
// *
// * @author Nyash
// *
// */
//@Getter
//public class RangeCriteria {
//
//    /**
//     * Page index(0-based)
//     */
//    private final int page;
//
//    /**
//     * Number of elements per page. Zero means that we should return all the
//     * elements
//     */
//    private final int rowCount;
//
//    public RangeCriteria(final int page, final int rowCount){
//        Checks.checkParameter(page >= 0, "Incorrect page index:" + page);
//        Checks.checkParameter(rowCount >= 0, "Incorrect row count:" + rowCount);
//
//        this.page = page;
//        this.rowCount = rowCount;
//    }
//}
