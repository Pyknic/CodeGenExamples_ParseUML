package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.ClassOrInterface;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Attribute;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToInterfaceTransform implements Transform<Element, Interface>, TransformDelegator {

	@Override
	public Optional<Interface> transform(Generator gen, Element model) {
		if ("Interface".equals(model.getName())) {
			
			final Interface result = Interface.of(model.getAttributeValue("name"));
			
			declareVisibility(result, model);
			children(gen, model, "Fields", Field.class).forEachOrdered(result::add);
			children(gen, model, "Methods", Method.class).forEachOrdered(result::add);
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
}
