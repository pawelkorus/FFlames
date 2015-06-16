package fflames.gui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import fflames.generator.Transform;

public class ExportXMLFileFractal {
	private ArrayList<Transform> _transforms;
	
	public ExportXMLFileFractal(ArrayList<Transform> transforms) {
		super();
		_transforms = transforms;
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
		out.write("<Functions>\r\n");
		
		Iterator<Transform> it = _transforms.iterator();
		while(it.hasNext()) {
			out.write("<Function>\r\n");
			it.next().writeXML(out);
			out.write("</Function>\r\n");
		}

		out.write("</Functions>\r\n");
		out.write("</Fractal>\r\n");
		out.flush();
		out.close();
	}
}
