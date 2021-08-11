//package com.nyash.travellizermono.api.common.model.transform.mapper;
//
//import java.util.UUID;
//
///**
// * Converts string values to UUID
// * @author Nyash
// *
// */
//public class StringToUuidMapper implements Mapper<String, UUID> {
//
//    @Override
//    public boolean supports(Class<String> sourceType, Class<UUID> destinationType) {
//        return sourceType.equals(String.class) && destinationType.equals(UUID.class);
//    }
//
//    @Override
//    public Object map(String sourceValue, Class<UUID> destinationType) {
//        return sourceValue == null ? null : UUID.fromString(sourceValue);
//    }
//}
