package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.ClassOrInterface;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Attribute;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToClassTransform implements Transform<Element, Class>, TransformDelegator {

	@Override
	public Optional<Class> transform(Generator gen, Element model) {
		if ("Class".equals(model.getName())) {
			
			final Class result = Class.of(model.getAttributeValue("name"));
			
			declareVisibility(result, model);
			
			children(gen, model, "Constructors", Constructor.class).forEachOrdered(result::add);
			children(gen, model, "Fields", Field.class).forEachOrdered(result::add);
			children(gen, model, "Methods", Method.class).forEachOrdered(result::add);
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
}
