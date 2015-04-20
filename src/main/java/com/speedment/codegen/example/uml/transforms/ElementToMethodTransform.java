/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.example.uml.transforms;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.Field;
import com.speedment.codegen.lang.models.Method;
import static com.speedment.codegen.lang.models.constants.DefaultType.VOID;
import com.speedment.codgen.example.uml.TransformDelegator;
import com.speedment.codgen.uml.TypeStore;
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
			
			result.set(TypeStore.INST.get(model.getAttributeValue("returnType")));
			
			return Optional.of(result);
		} 
		
		return Optional.empty();
	}
	
}
