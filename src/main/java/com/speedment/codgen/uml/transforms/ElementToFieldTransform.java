package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToFieldTransform implements Transform<Element, Field>, TransformDelegator {

	@Override
	public Optional<Field> transform(Generator gen, Element model) {
		if ("Argument".equals(model.getName())
		||  "Field".equals(model.getName())) {
			
			final Field result = Field.of(
				model.getAttributeValue("name"), 
				Type.of(model.getAttributeValue("type"))
			);
			
			declareVisibility(result, model);
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
	
}
