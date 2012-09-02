package fflames;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import fflames.interfaces.*;
import fflames.model.Functions;
import fflames.model.Transform;
import fflames.model.VariationsFactory;

public class ImportXMLFractalFile {
	public ImportXMLFractalFile(Functions _functions) {
		super();
		this._functions = _functions;
	}

	public boolean load(String path) throws IOException {
		File file = new File(path);
		return load(file);
	}
	
	public boolean load(File file) throws IOException {
		FileReader r = new FileReader(file);
		return load(r);
	}
	
	private boolean load(InputStreamReader input) throws IOException {
		_functions.removeAllElemens();

		try {
			XMLReader xr = XMLReaderFactory.createXMLReader();
			
			XMLHandler handler = new XMLHandler();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);

			xr.parse(new InputSource(input));
			
			return true;
		} catch (SAXException e) {
			_functions.removeAllElemens();
		}
		
		return false;
	}
	
	class XMLHandler extends DefaultHandler {
		private double[] affTrCoefs = new double[6];
		private Vector<IVariation> wariations = new Vector<IVariation>();
		private Vector<Double> param = new Vector<Double>();
		private Double coefficient = new Double(0.0);
		private Double propability = new Double(0.0);
		private int flag = 0;
		private int i = 0;

		XMLHandler() {
			super();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes atts) {
			if (localName.compareTo("Propability") == 0)
				flag = 1;
			else if (localName.compareTo("AffineTransform") == 0)
				flag = 2;
			else if (localName.compareTo("Coefficient") == 0)
				flag = 3;
			else if (localName.compareTo("Par") == 0)
				flag = 4;
			else if (localName.compareTo("Name") == 0)
				flag = 5;
			else
				;
		}

		@Override
		public void endElement(String uri, String localName, String qName) {
			if (localName.compareTo("Function") == 0) {
				_functions.addElement(new Transform(new AffineTransform(
						affTrCoefs), wariations, propability));
				wariations = new Vector<IVariation>();
				param.removeAllElements();
			} else if (localName.compareTo("Wsp") == 0)
				i++;
			else if (localName.compareTo("AffineTransform") == 0)
				i = 0;
			else
				;
		}

		@Override
		public void characters(char ch[], int start, int length) {
			String value = new String(ch);
			value = value.substring(start, start + length);
			if ((value.compareTo("\r") != 0) && (value.compareTo("\n") != 0)) {
				if (flag == 1)
					propability = Double.valueOf(value);
				if (flag == 2)
					affTrCoefs[i] = Double.valueOf(value);
				if (flag == 3)
					coefficient = Double.valueOf(value);
				if (flag == 4)
					param.add(Double.valueOf(value));
				if (flag == 5) {
					wariations.add(VariationsFactory.getWariation(value,
							coefficient));
					if (param.size() > 0)
						wariations.lastElement().setParameters(param);
				}
			}
		}
	}
	
	private Functions _functions;
}
