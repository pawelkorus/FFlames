package fflames;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fflames.model.Functions;

public class ExportXMLFileFractal {
	private Functions _functions;
	
	public ExportXMLFileFractal(Functions _functions) {
		super();
		this._functions = _functions;
	}

	public void save(String path) throws IOException {
		File file = new File(path);
		save(file);
	}
	
	public void save(File file) throws IOException {
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file));
		OutputStreamWriter out = new OutputStreamWriter(bout, "8859_2");

		out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?>\r\r");
		out.write("<Fractal>\n");

		_functions.writeXML(out);

		out.write("</Fractal>\r\n");
		out.flush();
		out.close();
	}
}
