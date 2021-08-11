//package com.nyash.travellizermono.api.common.model.transform.impl;
//
//import com.nyash.travellizer.common.infra.util.ReflectionUtil;
//
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * This thread-safe class caches field names for each transformation pair
// *
// * @author Nyash
// */
//public class CachedFieldProvider extends FieldProvider {
//
//    /**
//     * Mapping between transformation pair(class names) and field list
//     */
//    private final Map<String, List<String>> cache;
//
//    private final Map<String, List<String>> domainFields;
//
//    public CachedFieldProvider() {
//        cache = new ConcurrentHashMap<>();
//        domainFields = new ConcurrentHashMap<>();
//    }
//
//    /**
//     * Returns list of similar field names for source/destination classes
//     *
//     * @param source
//     * @param dest
//     * @return
//     */
//    @Override
//    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
//        String key = source.getSimpleName() + dest.getSimpleName();
//        return cache.computeIfAbsent(key, item -> ReflectionUtil.findSimilarFields(source, dest));
//    }
//
//    @Override
//    public List<String> getDomainProperties(Class<?> clz) {
//        String key = clz.getSimpleName();
//        return domainFields.computeIfAbsent(key, item -> super.getDomainProperties(clz));
//    }
//
//    @Override
//    public List<String> getFieldNames(Class<?> source, Class<?> dest, List<Field> ignoredFields) {
//        String key = source.getSimpleName() + dest.getSimpleName();
//        return cache.computeIfAbsent(key, item -> ReflectionUtil.findSimilarFields(source, dest, ignoredFields));
//    }
//}
