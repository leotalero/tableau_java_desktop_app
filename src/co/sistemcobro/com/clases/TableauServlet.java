// -----------------------------------------------------------------------------
// 
// This file is the copyrighted property of Tableau Software and is protected 
// by registered patents and other applicable U.S. and international laws and 
// regulations.
// 
// Unlicensed use of the contents of this file is prohibited. Please refer to 
// the NOTICES.txt file for further details.
// 
// -----------------------------------------------------------------------------
package co.sistemcobro.com.clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Servlet implementation class for Servlet: TableauServlet
 *
 */
 public class TableauServlet  {
    public TableauServlet() {
		super();
	}   	
	
	
	
    // the client_ip parameter isn't necessary to send in the POST unless you have
    // wgserver.extended_trusted_ip_checking enabled (it's disabled by default)
	public String getTrustedTicket(String wgserver, String user, String remoteAddr) 
       
    {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String resp="";
		try {
            // Encode the parameters
            StringBuffer data = new StringBuffer();
            data.append(URLEncoder.encode("username", "UTF-8"));
            data.append("=");
            data.append(URLEncoder.encode(user, "UTF-8"));
            data.append("&");
            data.append(URLEncoder.encode("client_ip", "UTF-8"));
            data.append("=");
            data.append(URLEncoder.encode(remoteAddr, "UTF-8"));
            
            // Send the request
            URL url = new URL("http://" + wgserver + "/trusted");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(data.toString());
            out.flush();
            
            // Read the response
            StringBuffer rsp = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ( (line = in.readLine()) != null) {
                rsp.append(line);
            }
            
            resp= rsp.toString();
            
        } catch (Exception e) {
            
        }
        finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            }
            catch (IOException e) {}
        }
		return resp;
    }
}
