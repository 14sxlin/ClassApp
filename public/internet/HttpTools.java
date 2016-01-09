package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpTools {
	
	/**
	 * 以POST的方式访问网页,注意不是线程安全的
	 * 因为其中使用的是StringBuilder
	 * @param website 要发送数据的网址
	 * @param param  要发送的数据
	 * @return 包含网页数据的StringBuilder
	 */
	public static StringBuilder post(String website,String param,String encode) {
		
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(website);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setAllowUserInteraction(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setConnectTimeout(1000);
			
			con.connect();
			
			PrintWriter pw = new PrintWriter(con.getOutputStream());
			pw.println(param);
			pw.flush();
			
			if (con.getResponseCode()==200) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), encode));
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				} 
			}
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb;
	}

	/**
	 * 以GET的方式访问网页,注意不是线程安全的
	 * 因为其中使用的是StringBuilder
	 * @param website 访问的网址,要包含查询串
	* @return 包含网页数据的StringBuilder
	 */
	public static StringBuilder get(String website,String encode)
	{
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(website);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(true);
			con.setConnectTimeout(1000);
			
			con.connect();
			
			if (con.getResponseCode()==200) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), encode));
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				} 
			}
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb;
		
	}
}
