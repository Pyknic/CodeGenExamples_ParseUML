package com.speedment.codgen.uml;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Meta;
import static com.speedment.codegen.lang.models.modifiers.Keyword.*;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jdom2.Attribute;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public interface TransformDelegator {
	
	default <M> Stream<M> children(Generator gen, Element model, String childName, java.lang.Class<M> type) {
		return Optional.ofNullable(model)
			.map(m -> m.getChild(childName))
			.map(m -> m.getChildren())
			.map(m -> gen.metaOn(m, type).map(Meta::getResult))
			.orElse(Stream.empty());
	}
	
	default <M> Stream<M> children(Generator gen, Element model, java.lang.Class<M> type) {
		return gen.metaOn(model.getChildren(), type).map(Meta::getResult);
	}
	
	default <M> void declareVisibility(M out, Element in) {
		final Attribute visible = in.getAttribute("visibility");
		
		if (visible != null && visible.isSpecified()) {
			ifType(visible, out, public_.class, public_::public_);
			ifType(visible, out, private_.class, private_::private_);
			ifType(visible, out, protected_.class, protected_::protected_);
		}
	}
	
	static <M> void ifType(Attribute a, Object obj, Class<M> type, Consumer<M> doer) {
		if (a.getValue().equals(type.getSimpleName().replace("_", ""))) {
			Optional.ofNullable(obj)
				.filter(o -> type.isAssignableFrom(o.getClass()))
				.map(o -> type.cast(o))
				.ifPresent(doer);
		}
	}
}
