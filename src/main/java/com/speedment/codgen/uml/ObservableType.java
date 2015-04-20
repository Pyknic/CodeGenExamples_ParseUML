package com.speedment.codgen.uml;

import com.speedment.codegen.lang.models.AnnotationUsage;
import com.speedment.codegen.lang.models.Generic;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.implementation.TypeImpl;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Emil Forslund
 */
public class ObservableType extends SimpleObjectProperty<Type> implements Type {

	public ObservableType(Class<?> javaImpl) {
		this (javaImpl.getName(), javaImpl);
	}

	public ObservableType(String name) {
		this (name, null);
	}

	public ObservableType(String name, Class<?> javaImpl) {
		super (new TypeImpl(name, javaImpl));
	}

	@Override
	public ObservableType setJavaImpl(Class<?> javaImpl) {
		return update(t -> t.setJavaImpl(javaImpl));
	}

	@Override
	public Optional<Class<?>> getJavaImpl() {
		return get().getJavaImpl();
	}

	@Override
	public ObservableType setArrayDimension(int arrayDimension) {
		return update(t -> t.setArrayDimension(arrayDimension));
	}

	@Override
	public int getArrayDimension() {
		return get().getArrayDimension();
	}

	@Override
	public ObservableType copy() {
		final ObservableType clone = new ObservableType(getName(), getJavaImpl().orElse(null));
		clone.setArrayDimension(getArrayDimension());
		return clone;
	}

	@Override
	public Type setName(String name) {
		return update(t -> t.setName(name));
	}

	@Override
	public String getName() {
		return get().getName();
	}

	@Override
	public List<Generic> getGenerics() {
		return get().getGenerics();
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return get().getAnnotations();
	}

	@Override
	public Type add(AnnotationUsage annotation) {
		return update(t -> t.add(annotation));
	}

	@Override
	public Type add(Generic generic) {
		return update(t -> t.add(generic));
	}
	
	private ObservableType update(Consumer<Type> setter) {
		final Type newType = get().copy();
		setter.accept(newType);
		set(newType);
		return this;
	}
	
}
