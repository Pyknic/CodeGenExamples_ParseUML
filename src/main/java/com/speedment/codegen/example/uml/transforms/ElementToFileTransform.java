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
import com.speedment.codegen.base.Meta;
import com.speedment.codegen.base.Transform;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.Enum;
import com.speedment.codegen.lang.models.Interface;
import com.speedment.codgen.example.uml.TransformDelegator;
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
