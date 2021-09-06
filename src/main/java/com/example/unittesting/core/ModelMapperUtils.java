package com.example.unittesting.core;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 4:06 PM
 */
public class ModelMapperUtils {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapperUtils() {
    }

    public static <S, D> D map(final S entity, Class<D> outClazz) {
        return modelMapper.map(entity, outClazz);
    }

    public static <S, D> List<D> mapAll(final Collection<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList());
    }

    public static <S, D> Set<D> mapAll(final Set<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toSet());
    }

    public static <S, D> List<D> mapAll(final List<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList());
    }

    public static <S, D> List<D> mapAll(final Page<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList());
    }

    public static <S, D> Page<D> mapAllPage(final Page<S> entityList, Class<D> outClazz) {
        return new PageImpl<>(entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList()));
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}