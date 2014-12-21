package com.edu.thss.smartdental.RemoteDB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentDBUtil {
	private ArrayList<String> parametername = new ArrayList<String>();
	private ArrayList<String> parametervalue = new ArrayList<String>();
	private ArrayList<String> resultinfo = new ArrayList<String>();
	private HttpConnSoap Soap = new HttpConnSoap();
	/**
	 * 鑾峰彇甯栧瓙鍐呭叏閮ㄨ瘎璁�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllComments(int PostId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		parametername.add("PostId");
		parametervalue.add(Integer.toString(PostId));
			
		try{
			resultinfo = Soap.GetWebService("selectAllCommentsByPostId", parametername, parametervalue);
		}
		catch(Exception e) {
		}
			

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("commentusername", "commentusername");
		tempHash.put("commenttype", "commenttype");
		tempHash.put("commenttitle", "commenttitle");
		tempHash.put("commentcontent", "commentcontent");
		tempHash.put("replytouser", "replytouser");
		tempHash.put("time","time");
		list.add(tempHash);
		
		for (int j = 0; j < resultinfo.size(); j += 6) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("commentusername", resultinfo.get(j));
			hashMap.put("commenttype", resultinfo.get(j + 1));
			hashMap.put("commenttitle", resultinfo.get(j + 2));
			hashMap.put("commentcontent", resultinfo.get(j + 3));
			hashMap.put("replytouser", resultinfo.get(j + 4));
			hashMap.put("time", resultinfo.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 鏂板璇勮
	 * 
	 * @return
	 */
	public String insertComment(String postid, String commentContent, String username, String CommentType, String ReplyUserName) {

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		
		parametername.add("postId");
		parametername.add("commentContent");
		parametername.add("username");
		parametername.add("CommentType");
		parametername.add("ReplyUserName");
		parametervalue.add(postid);
		parametervalue.add(commentContent);
		parametervalue.add(username);
		parametervalue.add(CommentType);	
		parametervalue.add(ReplyUserName);
		try{
			resultinfo = Soap.GetWebService("insertComment", parametername, parametervalue);
		}
		catch(Exception e) {
		}	
		if(resultinfo.size() == 0){
			return "fail to connect to Database";
		}
		return resultinfo.get(0);
	}
	/**
	 * 鍒犻櫎璇勮
	 * 
	 * @return
	 */
	public String deleteComment(String commentId) {

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		
		parametername.add("id");
		parametervalue.add(commentId);
		try{
			resultinfo = Soap.GetWebService("deleteComment", parametername, parametervalue);
		}
		catch(Exception e) {
		}
		if(resultinfo.size() == 0){
			return "fail to connect to Database";
		}
		return resultinfo.get(0);
	}
}
