package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
import static com.speedment.codegen.lang.models.constants.DefaultType.VOID;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToMethodTransform implements Transform<Element, Method>, TransformDelegator {

	@Override
	public Optional<Method> transform(Generator gen, Element model) {
		if ("Method".equals(model.getName())) {
			
			final Method result = Method.of(model.getAttributeValue("name"), VOID);
			
			declareVisibility(result, model);
			children(gen, model, Field.class).forEachOrdered(result::add);
			
			result.set(Type.of(model.getAttributeValue("returnType")));
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
	
}
