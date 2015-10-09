package co.sistemcobro.com.clases;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;



import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UIUtils.setPreferredLookAndFeel();
	    NativeInterface.open();
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        final JFrame frame = new JFrame("Asesores aplication");
	        frame.setUndecorated(true);
	        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	       
	       
	        SimpleWebBrowser webbrowser = null;
			
	        	  webbrowser = new SimpleWebBrowser();
			
	       Image image = new ImageIcon(getClass().getResource("/images/AsesoresAppicon.png")).getImage();
	       frame.setIconImage(image);
			
			frame.getContentPane().add(webbrowser, BorderLayout.CENTER);
		
	        frame.setSize(850, 700);
	        frame.setLocationByPlatform(true);
	        frame.setVisible(true);
	       
	  	
	        
	        GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
	        GraphicsConfiguration[] config = devices[0].getConfigurations();
	        Rectangle bound = config[0].getBounds();
	        frame.setLocation((int) (bound.getWidth() - frame.getWidth()), 0);
	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	      
	       
	       
	      }
	    });
	    NativeInterface.runEventPump();
		
	}
	
	
}
class MyTimerActionListener implements ActionListener {
	@Override
	
	public void actionPerformed(ActionEvent e) {

	    System.out.println("asdf");

	  }

	
	}

