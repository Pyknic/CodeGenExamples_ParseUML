package com.speedment.codgen.uml;

import com.speedment.codegen.lang.models.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Emil Forslund
 */
public enum TypeStore {
	INST;
	
	private final Map<String, ObservableType> types;
	
	private TypeStore() {
		Type.setSupplier(() -> new ObservableType(""));
		this.types = new ConcurrentHashMap<>();
	}
	
	public ObservableType get(String shortName) {
		return types.computeIfAbsent(shortName, s -> new ObservableType(s));
	}
}
