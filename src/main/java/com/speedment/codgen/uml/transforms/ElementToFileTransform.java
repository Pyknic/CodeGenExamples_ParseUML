package com.speedment.codgen.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Meta;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Enum;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codgen.uml.TransformDelegator;
import java.util.Optional;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class ElementToFileTransform implements Transform<Element, File>, TransformDelegator {

	@Override
	public Optional<File> transform(Generator gen, Element model) {
		if ("Class".equals(model.getName())
		||  "Interface".equals(model.getName())
		||  "Enum".equals(model.getName())) {
			
			final File result = File.of(
				model.getAttributeValue("package") + 
				model.getAttributeValue("name")
			);
			
			declareVisibility(result, model);
			
			gen.metaOn(model, Class.class).map(Meta::getResult).forEachOrdered(result::add);
			gen.metaOn(model, Interface.class).map(Meta::getResult).forEachOrdered(result::add);
			gen.metaOn(model, Enum.class).map(Meta::getResult).forEachOrdered(result::add);
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
}
