package com.speedment.codgen.uml;

import com.speedment.codegen.base.DefaultTransformFactory;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Constructor;
import com.speedment.codegen.lang.models.Enum;
import com.speedment.codegen.lang.models.EnumConstant;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codgen.uml.transforms.ElementToClassTransform;
import com.speedment.codgen.uml.transforms.ElementToConstructorTransform;
import com.speedment.codgen.uml.transforms.ElementToEnumConstantTransform;
import com.speedment.codgen.uml.transforms.ElementToEnumTransform;
import com.speedment.codgen.uml.transforms.ElementToFieldTransform;
import com.speedment.codgen.uml.transforms.ElementToFileTransform;
import com.speedment.codgen.uml.transforms.ElementToInterfaceTransform;
import com.speedment.codgen.uml.transforms.ElementToMethodTransform;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public class UMLTransformFactory extends DefaultTransformFactory {

	public UMLTransformFactory() {
		super ("UMLTransformFactory");
		
		install(Element.class, Class.class, ElementToClassTransform.class);
		install(Element.class, Constructor.class, ElementToConstructorTransform.class);
		install(Element.class, Enum.class, ElementToEnumTransform.class);
		install(Element.class, EnumConstant.class, ElementToEnumConstantTransform.class);
		install(Element.class, Field.class, ElementToFieldTransform.class);
		install(Element.class, File.class, ElementToFileTransform.class);
		install(Element.class, Interface.class, ElementToInterfaceTransform.class);
		install(Element.class, Method.class, ElementToMethodTransform.class);
		
	}
	
}