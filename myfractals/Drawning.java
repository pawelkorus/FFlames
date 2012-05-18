/*
 * Drawning.java
 *
 * Created on April 23, 2008, 3:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author victories
 */
public class Drawning extends SwingWorker<BufferedImage, Void> {
    private BufferedImage image;
    private Point2D.Double point, pointFrom, pointTo;
    private Color color;
    private Double xmin, xmax, ymin, ymax, xscale, yscale, xoffset, yoffset, x, y, tempx, tempy;
    private Functions funkcje;
    private IKolor coloring;
    private int quantityOfIter, width, height, whichFunction;
    private Graphics2D g;
    private int howToDraw;
    
    /** Creates a new instance of Drawning
     * @param _funkcje
     * @param _quantityOfIter 
     * @param _coloring 
     * @param pF
     * @param pT
     * @param _image
     * @param _howToDraw 
     */
    public Drawning(Functions _funkcje, int _quantityOfIter, IKolor _coloring, 
                    Point2D.Double pF, Point2D.Double pT, BufferedImage _image,
                    int _howToDraw) 
    {    
        quantityOfIter = _quantityOfIter;
        funkcje = _funkcje;
        coloring = _coloring;
        pointFrom = pF; pointTo = pT;
        image = _image;
        width = image.getWidth(); height = image.getHeight();
        howToDraw = _howToDraw;
        
        image.flush();
        if(howToDraw == 1) {
            g = image.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        }
        
        coloring.setScreenHits(width, height);
        
        x = new Double(0); y = new Double(0);
        
        xmin = new Double(Double.MAX_VALUE); xmax = new Double(Double.MIN_VALUE); 
        ymin = new Double(Double.MAX_VALUE); ymax = new Double(Double.MIN_VALUE);
        xscale = new Double(0.0); yscale = new Double(0.0); 
        xoffset = new Double(0.0); yoffset = new Double(0.0);
        point = new Point2D.Double(Math.random()*2-1, Math.random()*2-1);
        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    
    protected BufferedImage doInBackground() {
        Double progress = new Double(0.0);
        setProgress(progress.intValue());
        DoShowDialog doShowDialog = new DoShowDialog();
        
        int iter = 0; boolean flag = true;
        while(iter <= quantityOfIter && !isCancelled()) {
            int counter = 0;
            do {
                flag = oblicz(iter);
                x = point.getX()*xscale + xoffset;
                y = point.getY()*yscale + yoffset;
                counter++;
                if(counter > 1000) {
                    try {
                        if(!doShowDialog.wasVisible)
                            SwingUtilities.invokeAndWait(doShowDialog);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Drawning.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(Drawning.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
            } while(!narysuj(iter, flag));

            iter++;
            progress = iter*100.0/quantityOfIter;
            setProgress(progress.intValue());
        }
        
        return image;
    }
    
    private boolean oblicz(int iter) {
        whichFunction = ktoraFunkcja();
        boolean flag = funkcje.oblicz(whichFunction, point);
                
        if((iter > 100) && (iter < 1000)) {
            xmax = Math.max(point.getX(), xmax); xmin = Math.min(point.getX(), xmin);
            ymax = Math.max(point.getY(), ymax); ymin = Math.min(point.getY(), ymin);
        }
        else if(iter == 1000) {
            tempx = xmin + pointFrom.getX()*(xmax - xmin);
            tempy = ymin + pointFrom.getY()*(ymax - ymin);
            xmax = xmin + pointTo.getX()*(xmax - xmin);
            ymax = ymin + pointTo.getY()*(ymax - ymin);
            xmin = tempx;
            ymin = tempy;
              
            xscale = (3.0/4.0)*width/(xmax-xmin);
            yscale = ((3.0/4.0)*height/(ymax-ymin) < xscale/1.33) ? (3.0/4.0)*height/(ymax-ymin) : (xscale/1.33);
            xscale = 1.33*yscale;
            xoffset = width/2.0 - (xmax+xmin)*xscale/2.0;
            yoffset = height/2.0 - (ymax+ymin)*yscale/2.0;
        }
        
        return flag;
    }

    private boolean narysuj(int iter, boolean flag) {
        if(iter > 1000) {
            if ((x >=0 ) && (x < width-1) && (y >=0 ) && (y < height-1)) {
                if(flag) {
                    color = coloring.getColor(x.intValue(), y.intValue(), color, whichFunction);
                }
                if(howToDraw == 1) {
                    g.setColor(color);
                    g.fillRect(x.intValue(), y.intValue(), 1, 1);
                } 
                else
                    image.setRGB(x.intValue(), y.intValue(), color.getRGB());
                return true;
            }
            return false;
        }
        return true;
    }
    
    private int ktoraFunkcja() {
        double u = -Math.random();
        int k = 0;

        while(u < 0) {
            u = u + funkcje.getPr(k);
            k = k+1;
        }

        return k-1;
    }
    
    class DoShowDialog implements Runnable {
        public boolean wasVisible = false; 
        public void run() {
            wasVisible = true;
            JOptionPane.showMessageDialog(MyFractals.getFrames()[0], 
                    "Niestety ten program nie narysuje fraktala o tak zadanych parametrach,\n " +
                    "lub bêdzie trwa³o to bardzo d³ugo. Nale¿y przerwaæ generowanie fraktala.");
        }
    }
}
