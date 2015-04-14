package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.ClassOrInterface;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Enum;
import com.speedment.codegen.lang.models.EnumConstant;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import java.util.stream.Stream;
import org.jdom2.Attribute;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToEnumTransform implements Transform<Element, Enum>, TransformDelegator {

	@Override
	public Optional<Enum> transform(Generator gen, Element model) {
		if ("Enum".equals(model.getName())) {
			
			final Enum result = Enum.of(model.getAttributeValue("name"));

			declareVisibility(result, model);
			children(gen, model, "Literals", EnumConstant.class).forEachOrdered(result::add);
			children(gen, model, "Constructors", Constructor.class).forEachOrdered(result::add);
			children(gen, model, "Fields", Field.class).forEachOrdered(result::add);
			children(gen, model, "Methods", Method.class).forEachOrdered(result::add);
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
}
