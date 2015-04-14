package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToConstructorTransform implements Transform<Element, Constructor>, TransformDelegator {

	@Override
	public Optional<Constructor> transform(Generator gen, Element model) {
		if ("Constructor".equals(model.getName())) {
			final Constructor result = Constructor.of();
			
			declareVisibility(result, model);
			children(gen, model, Field.class).forEachOrdered(result::add);
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
	
}
