package sample.userGoogle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import sample.user.UserDAO;
import sample.user.UserDTO;
/**
 * @author heaty566
 */
@WebServlet(urlPatterns = { "/LoginGoogleHandler" })
public class LoginGoogleHandler extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
        private static final String DEFAULT_PASSWORD = "1234";
        private static final String ERROR = "login.html";
        private static final String SUCCESS = "LoginController";
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String url = ERROR;
            try{
		String code = request.getParameter("code");
		String accessToken = getToken(code);
		UserGoogleDTO user = getUserInfo(accessToken);
		UserDTO userDto = new UserDTO(user.getId(),user.getName(),"US",DEFAULT_PASSWORD);
                UserDAO userDao = new UserDAO();
                if(userDao.checkDupicate(userDto.getUserID())){
                    url = SUCCESS;
                     url = url + "?userID=" + URLEncoder.encode(userDto.getUserID(), "UTF-8") + 
                                    "&password=" + URLEncoder.encode(userDto.getPassword(), "UTF-8");
                }else{
                    boolean check = userDao.insert(userDto);
                    if(check){
                        url = SUCCESS;
                        url = url + "?userID=" + URLEncoder.encode(userDto.getUserID(), "UTF-8") + 
                                    "&password=" + URLEncoder.encode(userDto.getPassword(), "UTF-8");
                    }
                }
            }catch(Exception e){
                log("Error at LoginGoogleHandler: "+e.toString());
            }finally{
                request.getRequestDispatcher(url).forward(request, response);
            }
	}
	 public static String getToken(String code) throws ClientProtocolException, IOException {
        // In các tham số ra để kiểm tra
        System.out.println("Client ID: " + Constants.GOOGLE_CLIENT_ID);
        System.out.println("Client Secret: " + Constants.GOOGLE_CLIENT_SECRET);
        System.out.println("Redirect URI: " + Constants.GOOGLE_REDIRECT_URI);
        System.out.println("Authorization Code: " + code);
        System.out.println("Grant Type: " + Constants.GOOGLE_GRANT_TYPE);
        
        // Gửi yêu cầu API để lấy token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        
        System.out.println("Response: " + response);
        
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

	public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();

		UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

		return googlePojo;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the +
	// sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}