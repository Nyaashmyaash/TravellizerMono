//package com.nyash.travellizermono.api.common.model.transform;
//
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//
///**
// * Declares transform/untransform operations that should be used to copy
// * data between pair of objects in two directions (for example, entity and DTO).
// *
// * @author Nyash
// *
// * @param <P>
// */
//public interface Transformable<T, P> {
//
///**
// * Transforms first object into the second one
// * @param t
// * @param p
// * @return
// */
//    default P transform(T t, P p){
//        return p;
//    }
//
//    /**
//     * Transforms first object into the second one
//     * @param p
//     * @param t
//     * @return
//     */
//    default T untransform(P p, T t){
//        return t;
//    }
//
//    /**
//     * Returns list of the fields that should be skipped
//     * during copy process
//     * @return
//     */
//    default List<Field> getIgnoredFields() {
//        return List.of();
//    }
//
//    /**
//     * Returns mappings between source and destination fields
//     * @return
//     */
//    default Map<String, String> getSourceMapping(){
//        return Map.of();
//    }
//}
