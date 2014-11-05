package com.crm.servlets.agent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crm.servlets.interlevel.CRecord;
import com.crm.servlets.methods.GetPostData;

import net.sf.json.JSONObject;


/**
 * Servlet implementation class CreateRecord
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Agent/CreateRecord" })
public class CreateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GetPostData postData=new GetPostData();
		JSONObject newrecord=postData.getPost(request);
		newrecord=CRecord.createRecord(newrecord);
		response.getWriter().print(newrecord);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
