//package com.nyash.travellizermono.api.common.model.transform.mapper;
//
//import java.util.Objects;
//
///**
// * Generic converter from string values into enumerations
// * @author Nyash
// *
// */
//public class StringToEnumMapper implements Mapper<String, Enum>{
//
//    @Override
//    public boolean supports(Class<String> sourceType, Class<Enum> destinationType) {
//        return sourceType.equals(String.class) && Objects.equals(destinationType.getSuperclass(), Enum.class);
//    }
//
//    @Override
//    public Object map(String sourceValue, Class<Enum> destinationType) {
//        return sourceValue == null ? null : Enum.valueOf(destinationType, sourceValue.toUpperCase());
//    }
//}
