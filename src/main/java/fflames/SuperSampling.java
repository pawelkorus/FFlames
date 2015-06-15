package fflames;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

import fflames.interfaces.ISuperSampling;

public class SuperSampling implements ISuperSampling {
	public SuperSampling(int targetWidth, int targetHeight, int samples) {
		_targetWidth = targetWidth;
		_targetHeight = targetHeight;
		_samples = samples;
	}
	
	/* (non-Javadoc)
	 * @see fflames.ISuperSampling#getRequiredWidth()
	 */
	@Override
	public int getRequiredWidth() {
		return _samples * _targetWidth;
	}
	
	/* (non-Javadoc)
	 * @see fflames.ISuperSampling#getRequiredHeight()
	 */
	@Override
	public int getRequiredHeight() {
		return _samples * _targetHeight;
	}
	
	/* (non-Javadoc)
	 * @see fflames.ISuperSampling#processImage(java.awt.image.BufferedImage)
	 */
	@Override
	public BufferedImage processImage(BufferedImage image) {
		BufferedImage output = new BufferedImage(image.getColorModel(), image.getColorModel().createCompatibleWritableRaster(_targetWidth, _targetHeight), false, new Hashtable<String, Object>());
		WritableRaster sourceRaster = image.getRaster();
		WritableRaster targetRaster = output.getRaster();
		int sourceNumBands = sourceRaster.getNumBands();
		
		for(int x = 0; x < targetRaster.getWidth(); x ++) {
			for(int y = 0; y < targetRaster.getHeight(); y ++) {
				double[] newValues = new double[sourceNumBands];
				
				for(int i = 0; i < _samples; i++) {
					for(int j = 0; j < _samples; j++) {
						for(int k = 0; k < sourceNumBands; k++) {
							newValues[k] += sourceRaster.getSample(x * _samples + i, y * _samples + j, k);
							//double[] sourceValues = sourceRaster.getSa(x + i, y + j, new double[sourceNumBands]);
						//for(int k = 0; k < sourceValues.length; k++) {
						//	newValues[k] += sourceValues[k];
						//}
						}
					}
				}
				
				for(int i = 0; i < newValues.length; i++) {
					newValues[i] = newValues[i]/(_samples * _samples);
					targetRaster.setSample(x, y, i, newValues[i]);
				}
				
				
			}
		}
		
		return output;
	}
	
	private int _targetWidth, _targetHeight, _samples;
}
