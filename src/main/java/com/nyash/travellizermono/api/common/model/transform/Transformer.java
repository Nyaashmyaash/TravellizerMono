//package com.nyash.travellizermono.api.common.model.transform;
//
//import com.nyash.travellizer.common.infra.exception.flow.ValidationException;
//import com.nyash.travellizer.common.infra.util.Checks;
//import com.nyash.travellizer.common.infra.util.ReflectionUtil;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Represents transformation engine to convert business entities into DTO
// * objects
// *
// * @author Nyash
// */
//public interface Transformer {
//
//    /**
//     * Converts specified list of entities into destination class
//     *
//     * @param <T>
//     * @param <P>
//     * @param entities
//     * @param clz
//     * @return
//     */
//    default <T, P> List<P> transform(List<T> entities, Class<P> clz) {
//        checkParams(entities, clz);
//
//        return entities.stream().map(entity -> transform(entity, clz)).collect(Collectors.toList());
//    }
//
//    /**
//     * Converts specified entity into DTO object
//     *
//     * @param entity
//     * @param clz
//     * @return
//     */
//    default <T, P> P transform(T entity, Class<P> clz) {
//        checkParams(entity, clz);
//
//        P destination = ReflectionUtil.createInstance(clz);
//
//        return transform(entity, destination);
//    }
//
//    private void checkParams(final Object param, final Class<?> clz) {
//        checkParam(param, "Source transformation object is not initialized");
//        checkParam(clz, "No class is defined for transformation");
//    }
//
//    private void checkParam(final Object param, final String errorMessage) {
//        Checks.checkParameter(param != null, errorMessage);
//    }
//
//    /**
//     * Converts specified entity into existing DTO object
//     *
//     * @param entity
//     * @param dest
//     * @return
//     */
//    <T, P> P transform(T entity, P dest);
//
//    /**
//     * Converts specified DTO object into business entity
//     *
//     * @param dto
//     * @param entity
//     * @return
//     */
//    <T, P> T untransform(P dto, T entity);
//
//    /**
//     * Converts specified DTO object into the business entity of given class
//     *
//     * @param dto
//     * @param clz
//     * @return
//     */
//    default <T, P> T untransform(P dto, Class<T> clz) {
//        checkParams(dto, clz);
//
//        T entity = ReflectionUtil.createInstance(clz);
//
//        try {
//            return untransform(dto, entity);
//        } catch (Exception ex) {
//            throw new ValidationException(ex.getMessage(), ex);
//        }
//    }
//
//
//}
