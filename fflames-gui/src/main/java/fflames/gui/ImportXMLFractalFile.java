package fflames.gui;

import fflames.base.IVariation;
import fflames.base.Transform;
import fflames.base.variation.VariationsFactory;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ImportXMLFractalFile {

	public ImportXMLFractalFile() {
		super();
	}

	public void load(List<Transform> transforms, List<Double> propabilities, String path) throws IOException, ImportException {
		File file = new File(path);
		load(transforms, propabilities, file);
	}

	public void load(List<Transform> transforms, List<Double> propabilities, File file) throws IOException, ImportException {
		FileReader r = new FileReader(file);
		load(transforms, propabilities, r);
	}

	public void load(List<Transform> transforms, List<Double> propabilities, InputStreamReader input) throws IOException, ImportException {
		List<Transform> tempTransforms = new ArrayList<>();
		List<Double> tempPropabilities = new ArrayList<>();
		
		try {
			XMLReader xr = XMLReaderFactory.createXMLReader();

			XMLHandler handler = new XMLHandler(tempTransforms, tempPropabilities);
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);

			xr.parse(new InputSource(input));
		} catch (SAXException e) {
			throw new ImportException(e);
		}
		
		assert tempTransforms.size() == tempPropabilities.size();
		
		transforms.addAll(tempTransforms);
		propabilities.addAll(tempPropabilities);
	}

	public static class ImportException extends Exception {
		private static final long serialVersionUID = 5505268769493127939L;

		public ImportException() {
			super();
		}

		public ImportException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public ImportException(String message, Throwable cause) {
			super(message, cause);
		}

		public ImportException(String message) {
			super(message);
		}

		public ImportException(Throwable cause) {
			super(cause);
		}
	}
	
	private class XMLHandler extends DefaultHandler {
		private double[] affTrCoefs = new double[6];
		private ArrayList<IVariation> variations = new ArrayList<>();
		private ArrayList<Double> param = new ArrayList<>();
		private Double coefficient = new Double(0.0);
		private Double propability = new Double(0.0);
		private int flag = 0;
		private int i = 0;
		List<Transform> _transforms;
		List<Double> _propabilities;
		
		XMLHandler(List<Transform> transforms, List<Double> propabilities) {
			super();
			_transforms = transforms;
			_propabilities = propabilities;
		}

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
						affTrCoefs), variations));
				_propabilities.add(propability);
				
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
					variations.add(VariationsFactory.getVariation(value,
							coefficient));
					if (param.size() > 0) {
						variations.get(variations.size() - 1).setParameters(param);
					}
				}
			}
		}
	}
}
