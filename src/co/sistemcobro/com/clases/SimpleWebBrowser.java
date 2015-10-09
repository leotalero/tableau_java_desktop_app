/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
package co.sistemcobro.com.clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//import co.sistemcobro.rrhh.dao.EmpleadoDAO;
//import co.sistemcobro.rrhh.ejb.EmpleadoEJB;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import co.sistemcobro.rrhh.bean.EmpleadoBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



@SuppressWarnings("serial")
public class SimpleWebBrowser extends JPanel {
	 private static InitialContext ic;
	 
	 protected static final String LS = System.getProperty("line.separator");



  @SuppressWarnings("unused")
public SimpleWebBrowser() {
	
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Indicadores"));
     JWebBrowser webBrowser = new JWebBrowser();
    /*
    mi codigo 
    */
    
    TableauServlet ts = new TableauServlet();
	
	final String user="appsg";
	final String wgserver="www.sistemcobro.com/tableau";
	 
	String iplocal;
	String ticket="";
	try {
		iplocal = Inet4Address.getLocalHost().getHostAddress();
		ticket= ts.getTrustedTicket(wgserver, user, iplocal);
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	 com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
		
	 String usuario=NTSystem.getName();
	 System.out.printf("usuario:"+usuario);
	 String url="http://www.sistemcobro.com/rrhh/portal/serviciosweb?action=empleado_by_usuario_win&usuariowin="+usuario;
		
	 List<EmpleadoBean> results=new ArrayList<EmpleadoBean>();
    
 	Reader reader;
	try {
		 InputStream input = new URL(url).openStream();
		reader = new InputStreamReader(input, "UTF-8");
		results = new Gson().fromJson(reader, new TypeToken<List<EmpleadoBean>>(){}.getType());
	 	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 	
 	
   //DataSource ds = null;
  // EmpleadoEJB empleadoEJB=new EmpleadoEJB();
   EmpleadoBean empleado=null;
   String msg="";
    String urltableau = null;
    String identificacion="";
  if(results!=null && results.size()>0 || !ticket.equals("-1")){
	  empleado=results.get(0);
	   identificacion=empleado.getEmpleadoidentificacion().getNumeroidentificacion();
	   //identificacion="1030627911";
	//  String parametros="numeroidentificacionP="+identificacion ; //Dashboard_Asistencia_Individual/DashboardAsistencia
	   String parametros="g_asesorcedula="+identificacion+"&"+"a_asesorcedula="+identificacion+"&"+"Parameter_acedula="+identificacion+"&"+"Parameter_c_cedula="+identificacion  ;
	   // String parametros="numeroidentificacionP="+"105758440" ;  
	  urltableau="http://"+wgserver+"/trusted/"+ticket+"/views/Asesores_NPL/Dashboard_asesor?:embed=y&:display_count=no&"+parametros+"+&:refresh=y&:toolbar=no";
	     webBrowser.navigate(urltableau);
 
  }else{
	  
	     final String htmlContent =
      "<html>" + LS +
      "  <body>" + LS +
      "    <h1>Comuniquese con soporte</h1>" + LS +
    //  "    <p>Pagina web <a href=\"http://www.sistemcobro.com\">link</a>.</p>" + LS +
       "    <p>a través de un ticket con su cordinador.</p>" + LS +
       "    <p>error Usuario windows: "+ usuario + " identificacion: "+identificacion+"   </p>" + LS +
       "    <p>ticket tableau: "+ ticket + "   </p>" + LS +
      
      	" <div> " + LS +
     	" <object type='text/html' data='"+url+"' >" + LS +
			" </object></div> " + LS +
      "  </body>" + LS +
      
      "</html>";
    webBrowser.setHTMLContent(htmlContent);
     
  }
  
   
	//String parametros="numeroidentificacionP="+empleado.getEmpleadoidentificacion().getNumeroidentificacion();
 
   
    

    webBrowser.setBarsVisible(true);
    webBrowser.setStatusBarVisible(true);
    
 
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    add(webBrowserPanel, BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
    //JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
   // menuBarCheckBox.addItemListener(new ItemListener() {
    //  public void itemStateChanged(ItemEvent e) {
    //    webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
    //  }
   // });
   // buttonPanel.add(menuBarCheckBox);
   // add(buttonPanel, BorderLayout.SOUTH);
  }

  
/*  public void loadProperties(String h, String p)  {
      try {
          Properties props = new Properties();

          System.out.println("h: " + h + " p: " + p);

          props.setProperty("java.naming.factory.initial",
                  "com.sun.enterprise.naming.SerialInitContextFactory");
          props.setProperty("java.naming.factory.url.pkgs",
                  "com.sun.enterprise.naming");
          props.setProperty("java.naming.factory.state",
                  "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
          props.setProperty("org.omg.CORBA.ORBInitialHost", h);
          props.setProperty("org.omg.CORBA.ORBInitialPort", p);

          ic = new InitialContext(props);
      } catch (NamingException ex) {
    	  throw new LogicaException(ex.toString(), ex.getCause());
      }
  }
  */
  
  
}
