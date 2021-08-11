//package com.nyash.travellizermono.api.common.model.transform.mapper;
//
//import java.util.Objects;
//
///**
// * Converts enumeration into string values
// *
// * @author Nyash
// *
// */
//
//public class EnumToStringMapper implements Mapper<Enum<?>, String>{
//
//    @Override
//    public boolean supports(Class<Enum<?>> sourceType, Class<String> destinationType) {
//        return Objects.equals(sourceType.getSuperclass(), Enum.class) && destinationType.equals(String.class);
//    }
//
//    @Override
//    public Object map(Enum<?> sourceValue, Class<String> destinationType) {
//        return sourceValue == null ? null : sourceValue.name();
//    }
//}
