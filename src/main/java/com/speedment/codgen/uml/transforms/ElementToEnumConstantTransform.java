package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.EnumConstant;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToEnumConstantTransform implements Transform<Element, EnumConstant>, TransformDelegator {

	@Override
	public Optional<EnumConstant> transform(Generator gen, Element model) {
		if ("Literal".equals(model.getName())) {
			return Optional.of(EnumConstant.of(model.getAttributeValue("name")));
		} 
		
		return Optional.empty();
	}
	
}
