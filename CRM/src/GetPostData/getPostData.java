package GetPostData;


	import java.io.BufferedReader;

	import javax.servlet.http.HttpServletRequest;

	import net.sf.json.JSONObject;


	public class getPostData {
		private	StringBuffer jb = new StringBuffer();
		private	String line = null;
		private JSONObject jsonObject = new JSONObject();
		
		public JSONObject getPost(HttpServletRequest request)
		{
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		    jb.append(line);
			} catch (Exception e) { System.out.println("wrong input"); }
			
		 jsonObject = JSONObject.fromObject(jb.toString());
		 return jsonObject;
		}
	}


