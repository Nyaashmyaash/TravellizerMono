//package com.nyash.travellizermono.api.common.model.transform.impl;
//
//import com.nyash.travellizer.common.infra.util.ReflectionUtil;
//import com.nyash.travellizer.common.model.transform.annotation.DomainProperty;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
///**
// * Base functionality of the field preparation
// *
// * @author Nyash
// *
// */
//public class FieldProvider {
//    public List<String> getFieldNames (Class<?> source, Class<?> dest) {
//        return ReflectionUtil.findSimilarFields(source, dest);
//    }
//
//    public List<String> getFieldNames (Class<?> source, Class<?> dest, List<Field> ignoredFields) {
//        return ReflectionUtil.findSimilarFields(source, dest, ignoredFields);
//    }
//
//    /**
//     * Returns list of fields of clz class annotaed with @DomainProperty annotation
//     * @param clz
//     * @return
//     */
//    public List<String> getDomainProperties(Class<?> clz) {
//        return ReflectionUtil.getFields(clz, List.of(field -> field.isAnnotationPresent(DomainProperty.class)));
//    }
//}
//
