package com.nyash.travellizermono.api.common.infra.util;//package com.nyash.travellizer.common.infra.util;
//
//import com.nyash.travellizer.common.infra.exception.ConfigurationException;
//import com.nyash.travellizer.common.infra.util.annotations.Ignore;
//import com.nyash.travellizer.common.model.transform.mapper.*;
//import org.apache.commons.lang3.StringUtils;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
///**
// * Contains reflection-related utility operations
// *
// * @author Nyash
// */
//public class ReflectionUtil {
//    private static final List<Mapper> MAPPERS;
//
//    static {
//        MAPPERS = List.of(new EnumToStringMapper(), new UuidToStringMapper(), new StringToEnumMapper(),
//                new StringToUuidMapper(), new StandardMapper());
//    }
//
//    private ReflectionUtil() {
//    }
//
//    /**
//     * Creates an instance of the specified class. This method throws unchecked
//     * exception if creation fails
//     *
//     * @param clz
//     * @return
//     * @throws ConfigurationException
//     */
//    public static <T> T createInstance(Class<T> clz) throws ConfigurationException {
//        try {
//            return clz.getDeclaredConstructor().newInstance();
//        } catch (Exception ex) {
//            throw new ConfigurationException(ex);
//        }
//    }
//
//    /**
//     * Returns list of fields with identical names irregardles of their modifiers
//     *
//     * @param clz1
//     * @param clz2
//     * @return
//     */
//    public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2) throws ConfigurationException {
//        try {
//            Predicate<Field> ignoreAbsent = field -> !field.isAnnotationPresent(Ignore.class);
//            List<String> targetFields = getFields(clz2, List.of(ignoreAbsent));
//
//            return getFields(clz1, List.of(ignoreAbsent,
//                    field -> !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()),
//                    field -> targetFields.contains(field.getName())));
//        } catch (SecurityException ex) {
//            throw new ConfigurationException(ex);
//        }
//    }
//
//    public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2, List<Field> ignoredFields)
//            throws ConfigurationException {
//        try {
//            Predicate<Field> ignoreAbsent = field -> !ignoredFields.contains(field);
//            List<String> targetFields = getFields(clz2, List.of(ignoreAbsent));
//
//            return getFields(clz1, List.of(ignoreAbsent,
//                    field -> !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()),
//                    field -> targetFields.contains(field.getName())));
//        } catch (SecurityException ex) {
//            throw new ConfigurationException(ex);
//        }
//    }
//
//    /***
//     * Returns all declared fields of the specified classes and all superclasses
//     *
//     * @param cls
//     * @return
//     */
//    public static <T> List<Field> getFields(final Class<?> cls) {
//        List<Field> fields = new ArrayList<Field>();
//
//        Class<?> current = cls;
//        while (current != null) {
//            fields.addAll(Arrays.asList(current.getDeclaredFields()));
//            current = current.getSuperclass();
//        }
//
//        return fields;
//    }
//
//    /**
//     * Returns list of the class fields that match specified criteria
//     *
//     * @param cls
//     * @param filters
//     * @return
//     */
//    public static List<String> getFields(final Class<?> cls, final List<Predicate<Field>> filters) {
//        List<Field> fields = new ArrayList<Field>();
//
//        Class<?> current = cls;
//        while (current != null) {
//            fields.addAll(Arrays.stream(current.getDeclaredFields())
//                    .filter(field -> filters.stream().allMatch(p -> p.test(field))).collect(Collectors.toList()));
//            current = current.getSuperclass();
//        }
//
//        return fields.stream().map(Field::getName).collect(Collectors.toList());
//    }
//
//    /**
//     * Copy specified fields values from source to destination objects
//     *
//     * @param src
//     * @param dest
//     * @param fields
//     */
//    public static void copyFields(final Object src, final Object dest, final List<String> fields)
//            throws ConfigurationException {
//        Checks.checkParameter(src != null, "Source object is not initialized");
//        Checks.checkParameter(dest != null, "Destination object is not initialized");
//        try {
//            for (String field : fields) {
//                Field sourceField = getField(src.getClass(), field);
//                Field destField = getField(dest.getClass(), field);
//                // Skip unknown fields
//                if (sourceField != null && destField != null) {
//                    Object value = convert(sourceField, destField, src);
//
//                    setFieldValue(dest, field, value);
//                }
//            }
//        } catch (SecurityException | ReflectiveOperationException | IllegalArgumentException ex) {
//            throw new ConfigurationException(ex);
//        }
//    }
//
//    /**
//     * Converts value of source field using corresponding mapper
//     * @param sourceField
//     * @param destField
//     * @param src
//     * @return
//     * @throws IllegalAccessException
//     * @throws IllegalArgumentException
//     */
//    private static Object convert(Field sourceField, Field destField, Object src)
//            throws IllegalArgumentException, ReflectiveOperationException {
//        Mapper currentMapper = MAPPERS.stream()
//                .filter(mapper -> mapper.supports(sourceField.getType(), destField.getType())).findAny()
//                .orElseThrow(() -> new IllegalArgumentException(
//                        "No mapper for field types: " + sourceField.getType() + " to " + destField.getType()));
//
//        sourceField.setAccessible(true);
//        return currentMapper.map(sourceField.get(src), destField.getType());
//    }
//
//    /**
//     * Returns class field by its name. This method supports base classes as well
//     *
//     * @param clz
//     * @param name
//     * @return
//     */
//    public static <T> Field getField(final Class<T> clz, final String name) {
//        Class<?> current = clz;
//        while (current != null) {
//            try {
//                return current.getDeclaredField(name);
//            } catch (NoSuchFieldException | SecurityException e) {
//                current = current.getSuperclass();
//            }
//        }
//        throw new ConfigurationException("No field " + name + " in the class " + clz);
//    }
//
//    /**
//     * Returns value of the specified field of the object
//     *
//     * @param src
//     * @param name
//     */
//    public static Object getFieldValue(final Object src, final String name) throws ConfigurationException {
//        Checks.checkParameter(src != null, "Source object is not initialized");
//        Checks.checkParameter(!StringUtils.isEmpty(name), "Field name is not initialized");
//        try {
//            Field fld = getField(src.getClass(), name);
//            fld.setAccessible(true);
//            return fld.get(src);
//        } catch (SecurityException | ReflectiveOperationException | IllegalArgumentException ex) {
//            throw new ConfigurationException(ex);
//        }
//    }
//
//    /**
//     * Changes value of the specified field of the object
//     *
//     * @param src
//     * @param name
//     * @param value
//     */
//    public static void setFieldValue(final Object src, final String name, final Object value)
//            throws ConfigurationException {
//        Checks.checkParameter(src != null, "Source object is not initialized");
//        Checks.checkParameter(!StringUtils.isEmpty(name), "Field name is not initialized");
//        try {
//            Field fld = getField(src.getClass(), name);
//            fld.setAccessible(true);
//            fld.set(src, value);
//        } catch (SecurityException | ReflectiveOperationException | IllegalArgumentException ex) {
//            throw new ConfigurationException(ex);
//        }
//    }
//
//}