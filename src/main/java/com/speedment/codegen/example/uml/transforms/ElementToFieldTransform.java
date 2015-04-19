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
import com.speedment.codegen.lang.models.Type;
import com.speedment.codgen.example.uml.TransformDelegator;
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
