//package com.nyash.travellizermono.api.common.model.entity.base;
//
//import lombok.EqualsAndHashCode;
//import lombok.Setter;
//
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
///**
// * Base class for all business entities
// *
// * @author Nyash
// */
//@MappedSuperclass
//@Setter
//@EqualsAndHashCode(of="id")
//public abstract class AbstractEntity {
//
//    /**
//     * Unique entity identifier
//     */
//    private int id;
//
//    /**
//     * Timestamp of entity creation
//     */
//    private LocalDateTime createdAt;
//
//    /**
//     * Timestamp of entity last modification
//     */
//    private LocalDateTime modifiedAt;
//
//    /**
//     * Person who created specific entity
//     */
//    private String createdBy;
//
//    /**
//     * Last person who modified entity
//     */
//    private String modifiedBy;
//
//    public int getId() {
//        return id;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public LocalDateTime getModifiedAt() {
//        return modifiedAt;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//}
