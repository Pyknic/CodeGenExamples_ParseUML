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
package com.speedment.codgen.example.uml;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Meta;
import static com.speedment.codegen.lang.models.modifiers.Keyword.*;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jdom2.Attribute;
import org.jdom2.Element;

/**
 *
 * @author Emil Forslund
 */
public interface TransformDelegator {
	
	default <M> Stream<M> children(Generator gen, Element model, String childName, java.lang.Class<M> type) {
		return Optional.ofNullable(model)
			.map(m -> m.getChild(childName))
			.map(m -> m.getChildren())
			.map(m -> gen.metaOn(m, type).map(Meta::getResult))
			.orElse(Stream.empty());
	}
	
	default <M> Stream<M> children(Generator gen, Element model, java.lang.Class<M> type) {
		return gen.metaOn(model.getChildren(), type).map(Meta::getResult);
	}
	
	default <M> void declareVisibility(M out, Element in) {
		final Attribute visible = in.getAttribute("visibility");
		
		if (visible != null && visible.isSpecified()) {
			ifType(visible, out, public_.class, public_::public_);
			ifType(visible, out, private_.class, private_::private_);
			ifType(visible, out, protected_.class, protected_::protected_);
		}
	}
	
	static <M> void ifType(Attribute a, Object obj, Class<M> type, Consumer<M> doer) {
		if (a.getValue().equals(type.getSimpleName().replace("_", ""))) {
			Optional.ofNullable(obj)
				.filter(o -> type.isAssignableFrom(o.getClass()))
				.map(o -> type.cast(o))
				.ifPresent(doer);
		}
	}
}
