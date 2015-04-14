package com.speedment.codgen.uml;

import com.speedment.codegen.base.Generator;
import com.speedment.codegen.base.Meta;
import com.speedment.codegen.java.JavaGenerator;
import com.speedment.codegen.java.JavaTransformFactory;
import com.speedment.codegen.lang.models.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Emil Forslund
 */
public class Generate {
	
	private final static String PATH = "/xml/";
	
	public static void main(String... params) {
		final Generator gen = new JavaGenerator(
			new JavaTransformFactory(),
			new UMLTransformFactory()
		);
		
		final URL umlPath = Generate.class.getResource(PATH + "ExampleUML.cdg");
		
		try {
			final SAXBuilder jdomBuilder = new SAXBuilder();
			final Document doc = jdomBuilder.build(umlPath);
			final Element classDiagram = doc.getRootElement();
			
			System.out.println(
			gen.metaOn(classDiagram.getChild("ClassDiagramComponents").getChildren(), File.class)
				.map(Meta::getResult)
				.flatMap(gen::metaOn)
				.map(Meta::getResult)
				.collect(joining("\n----------------------------------\n"))
			);
			
		} catch (JDOMException ex) {
			Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, 
				"Failed to parse XML structure.", ex
			);
		} catch (IOException ex) {
			Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, 
				"Could not load file '" + umlPath.toExternalForm() + "'.", ex
			);
		}
		
	}
}