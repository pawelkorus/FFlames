package fflames.gui;

import fflames.generator.IVariation;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import fflames.generator.Transform;
import fflames.generator.variation.VariationsFactory;
import fflames.gui.exceptions.ImportXMLFractalFileException;
import fflames.gui.model.TransformTableModel;

public class ImportXMLFractalFile {

	public ImportXMLFractalFile() {
		super();
	}

	public void load(TransformTableModel transformsModel, String path) throws IOException, ImportXMLFractalFileException {
		File file = new File(path);
		load(transformsModel, file);
	}

	public void load(TransformTableModel transformsModel, File file) throws IOException, ImportXMLFractalFileException {
		FileReader r = new FileReader(file);
		load(transformsModel, r);
	}

	public void load(TransformTableModel transformsModel, InputStreamReader input) throws IOException, ImportXMLFractalFileException {
		try {
			XMLReader xr = XMLReaderFactory.createXMLReader();

			XMLHandler handler = new XMLHandler(transformsModel.getTransforms());
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);

			xr.parse(new InputSource(input));
		} catch (SAXException e) {
			throw new ImportXMLFractalFileException(e);
		}
	}

	class XMLHandler extends DefaultHandler {

		XMLHandler(ArrayList<Transform> transforms) {
			super();
			_transforms = transforms;
		}

		private double[] affTrCoefs = new double[6];
		private ArrayList<IVariation> variations = new ArrayList<>();
		private ArrayList<Double> param = new ArrayList<>();
		private Double coefficient = new Double(0.0);
		private Double propability = new Double(0.0);
		private int flag = 0;
		private int i = 0;
		ArrayList<Transform> _transforms;

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes atts) {
			if (localName.compareTo("Propability") == 0) {
				flag = 1;
			} else if (localName.compareTo("AffineTransform") == 0) {
				flag = 2;
			} else if (localName.compareTo("Coefficient") == 0) {
				flag = 3;
			} else if (localName.compareTo("Par") == 0) {
				flag = 4;
			} else if (localName.compareTo("Name") == 0) {
				flag = 5;
			} else
				;
		}

		@Override
		public void endElement(String uri, String localName, String qName) {
			if (localName.compareTo("Function") == 0) {
				_transforms.add(new Transform(new AffineTransform(
						affTrCoefs), variations, propability));
				variations = new ArrayList<>();
				param.clear();
			} else if (localName.compareTo("Wsp") == 0) {
				i++;
			} else if (localName.compareTo("AffineTransform") == 0) {
				i = 0;
			} else
				;
		}

		@Override
		public void characters(char ch[], int start, int length) {
			String value = new String(ch);
			value = value.substring(start, start + length);
			if ((value.compareTo("\r") != 0) && (value.compareTo("\n") != 0)) {
				if (flag == 1) {
					propability = Double.valueOf(value);
				}
				if (flag == 2) {
					affTrCoefs[i] = Double.valueOf(value);
				}
				if (flag == 3) {
					coefficient = Double.valueOf(value);
				}
				if (flag == 4) {
					param.add(Double.valueOf(value));
				}
				if (flag == 5) {
					variations.add(VariationsFactory.getWariation(value,
							coefficient));
					if (param.size() > 0) {
						variations.get(variations.size() - 1).setParameters(param);
					}
				}
			}
		}
	}
}
