package org.jtheque.primary.utils.web.analyzers.generic;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jdom.Element;
import org.jtheque.primary.utils.web.analyzers.generic.field.FieldGetter;
import org.jtheque.primary.utils.web.analyzers.generic.field.FieldGetterFactory;
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.transform.Transformer;
import org.jtheque.primary.utils.web.analyzers.generic.transform.TransformerFactory;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetterFactory;
import org.jtheque.utils.io.FileUtils;
import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.XMLReader;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A generator for generic film analyzer. This class manage the generation of parser from the xml definition file.
 *
 * @author Baptiste Wicht
 */
public final class GenericGenerator {
	private final Collection<FieldGetter> getters;

	private final Pages pages;

	private XMLReader reader;

	/**
	 * Construct a new GenericGenerator for an analyzer.
	 *
	 * @param analyzer The analyzer for which we generate.
	 */
	public GenericGenerator(ScannerPossessor analyzer){
		super();

		ValueGetterFactory.setScannerPossessor(analyzer);

		getters = new ArrayList<FieldGetter>(10);
		pages = new Pages();
	}

	/**
	 * Generate the parser.
	 *
	 * @param path The path to the XML File in the current ClassLoader.
	 */
	public void generate(String path){
		try {
			reader = new XMLReader();
			reader.openURL(getClass().getClassLoader().getResource(path));

			init();
		} catch (XMLException e){
			LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
		} finally {
			FileUtils.close(reader);
		}
	}

	/**
	 * Generate from a file.
	 *
	 * @param file The file to generate from.
	 */
	public void generate(File file){
		try {
			reader = new XMLReader();
			reader.openFile(file);

			init();
		} catch (XMLException e){
			LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
		} finally {
			FileUtils.close(reader);
		}
	}

	/**
	 * Init the parser from the root of the XML File.
	 *
	 * @throws XMLException If an errors occurs during the parse of the XML Elements.
	 */
	private void init() throws XMLException{
		initPages();

		for (Element currentNode : reader.getNodes("getter", reader.getRootElement())){
			getters.add(FieldGetterFactory.getFieldGetter(currentNode, reader));
		}
	}

	/**
	 * Init the different pages.
	 *
	 * @throws XMLException Thrown if an errors occurs during the xml reading process.
	 */
	private void initPages() throws XMLException{
		for (Element currentNode : reader.getNodes("pages/*", reader.getRootElement())){
			if ("films".equals(currentNode.getName())){
				pages.setFilmsPage(getPage(currentNode));
			} else if ("actors".equals(currentNode.getName())){
				pages.setActorsPage(getPage(currentNode));
			} else if ("results".equals(currentNode.getName())){
				pages.setResultsPage(getPage(currentNode));
			}
		}
	}

	/**
	 * Return the page of the XML Element.
	 *
	 * @param element The element to search in.
	 *
	 * @return The Page corresponding to the element.
	 *
	 * @throws XMLException Thrown if an errors occurs during the xml reading process.
	 */
	private Page getPage(Element element) throws XMLException{
		Page page = new Page();

		page.setUrl(reader.readString("url", element));
		page.setTransformers(new ArrayList<Transformer>(5));

		for (Element n : reader.getNodes("transformers/*", element)){
			Transformer transformer = TransformerFactory.getTransformer(n, reader);

			if (transformer != null){
				page.getTransformers().add(transformer);
			}
		}

		return page;
	}

	/**
	 * Return the FieldGetter with the specific name.
	 *
	 * @param name The name of the searched getter.
	 *
	 * @return The FieldGetter or null if there is no FieldGetter with this name.
	 */
	public FieldGetter getFieldGetter(String name){
		FieldGetter getter = null;

		for (FieldGetter g : getters){
			if (name.equals(g.getFieldName())){
				getter = g;
				break;
			}
		}

		return getter;
	}

	/**
	 * Return all the pages of the generator.
	 *
	 * @return The pages of the generator.
	 */
	public Pages getPages(){
		return pages;
	}
}