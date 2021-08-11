//package com.nyash.travellizermono.api.common.model.transform.mapper;
//
//import java.util.UUID;
//
///**
// * Converts UUID to string values
// *
// * @author Nyash
// *
// */
//public class UuidToStringMapper implements Mapper<UUID, String>{
//    @Override
//    public boolean supports(Class<UUID> sourceType, Class<String> destinationType) {
//        return sourceType.equals(UUID.class) && destinationType.equals(String.class);
//    }
//
//    @Override
//    public Object map(UUID sourceValue, Class<String> destinationType) {
//        return sourceValue == null ? null: sourceValue.toString();
//    }
//}
