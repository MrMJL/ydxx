package org.ydxx.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;
import org.ydxx.entity.ResultMessage;
import org.ydxx.entity.User;
import org.ydxx.entity.Xsdy;

public class Business {
	//private Client client = new Client();
	//private String HOST = "http://192.168.2.245:8080/ydxx/json/";
    //
	//public ResultMessage regedit(User user) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
	//	parameterMap.put("user.username", user.getUsername());
	//	parameterMap.put("user.sex", user.getSex());
	//	parameterMap.put("user.age", user.getAge() + "");
	//	parameterMap.put("user.password", user.getPassword());
	//	parameterMap.put("user.email", user.getEmail());
	//	parameterMap.put("user.type", "3");
	//	parameterMap.put("user.isenable", "1");
    //
	//	HttpPost httpPost = new HttpPost(HOST + "login-regedit.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
    //
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage login(User user) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
	//	parameterMap.put("username", user.getUsername());
	//	parameterMap.put("password", user.getPassword());
	//	HttpPost httpPost = new HttpPost(HOST + "login-jsonCheck.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject jsonObject = new JSONObject(message);
	//			resultMessage.setMessage(jsonObject.getString("message"));
	//			resultMessage.setStatus(jsonObject.getBoolean("status"));
	//			if (jsonObject.getBoolean("status")) {
	//				User localUser = new User();
	//				JSONObject jsonUserObject = jsonObject.getJSONObject("user");
	//				localUser.setId(jsonUserObject.getString("id"));
	//				localUser.setUsername(jsonUserObject.getString("username"));
	//				localUser.setPassword(jsonUserObject.getString("password"));
	//				localUser.setSex(jsonUserObject.getString("sex"));
	//				localUser.setAge(jsonUserObject.getString("age"));
	//				localUser.setEmail(jsonUserObject.getString("email"));
	//				localUser.setType(jsonUserObject.getString("type"));
	//				resultMessage.setUser(localUser);
	//			}
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage updateUser(User user) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
	//	parameterMap.put("entity.id", user.getId());
	//	parameterMap.put("entity.username", user.getUsername());
	//	parameterMap.put("entity.sex", user.getSex());
	//	parameterMap.put("entity.age", user.getAge() + "");
	//	parameterMap.put("entity.password", user.getPassword());
	//	parameterMap.put("entity.email", user.getEmail());
	//	parameterMap.put("entity.isenable", "3");
	//	parameterMap.put("entity.type", user.getType());
    //
	//	HttpPost httpPost = new HttpPost(HOST + "user-saveUpdate.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
    //
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
    //
	//public ResultMessage deleteBingliList(String id) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
	//	parameterMap.put("entity.id", id);
	//	HttpPost httpPost = new HttpPost(HOST + "bingli-delete.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage findMess() {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
    //
	//	HttpPost httpPost = new HttpPost(HOST + "mess-list.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject jsonObject = new JSONObject(message);
	//			resultMessage.setMessage(jsonObject.getString("message"));
	//			resultMessage.setStatus(jsonObject.getBoolean("status"));
	//			List<Mess> entityList = new ArrayList<Mess>();
	//			if (resultMessage.isStatus() && !jsonObject.isNull("list")) {
	//				JSONArray entityJsonarray = jsonObject.getJSONArray("list");
	//				for (int i = 0; i < entityJsonarray.length(); i++) {
	//					JSONObject entityJson = entityJsonarray.getJSONObject(i);
	//					Mess entity = new Mess();
	//					entity.setId(entityJson.getString("id"));
	//					entity.setExt1(entityJson.getString("ext1"));
	//					entity.setExt2(entityJson.getString("ext2"));
	//					entity.setExt3(entityJson.getString("ext3"));
	//					entity.setFmessage(entityJson.getString("fmessage"));
	//					entity.setFuserid(entityJson.getString("fuserid"));
	//					entity.setFusername(entityJson.getString("fusername"));
	//					entity.setTmessage(entityJson.getString("tmessage"));
	//					entity.setTuserid(entityJson.getString("tuserid"));
	//					entity.setTusername(entityJson.getString("tusername"));
    //
	//					entityList.add(entity);
	//				}
	//			}
	//			resultMessage.setItems(entityList);
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage addFMess(Mess mess) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
    //
	//	// parameterMap.put("entity.id", mess.getFuserid());
	//	parameterMap.put("entity.fmessage", mess.getFmessage());
	//	parameterMap.put("entity.fuserid", mess.getFuserid());
    //
	//	HttpPost httpPost = new HttpPost(HOST + "mess-saveUpdate.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
    //
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage addTMess(Mess mess) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
    //
	//	parameterMap.put("entity.id", mess.getId());
	//	parameterMap.put("entity.tmessage", mess.getTmessage());
	//	parameterMap.put("entity.tuserid", mess.getTuserid());
    //
	//	HttpPost httpPost = new HttpPost(HOST + "mess-saveUpdate1.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
    //
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
    //
    //
    //
	//public ResultMessage deleteXsdy(Xsdy xsdy) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
    //
	//	parameterMap.put("entity.id", xsdy.getId());
    //
	//	HttpPost httpPost = new HttpPost(HOST + "xsdy-delete.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
    //
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage findJxzy() {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
    //
	//	HttpPost httpPost = new HttpPost(HOST + "jxzy-list.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject jsonObject = new JSONObject(message);
	//			resultMessage.setMessage(jsonObject.getString("message"));
	//			resultMessage.setStatus(jsonObject.getBoolean("status"));
	//			List<Jxzy> entityList = new ArrayList<Jxzy>();
	//			if (resultMessage.isStatus() && !jsonObject.isNull("list")) {
	//				JSONArray entityJsonarray = jsonObject.getJSONArray("list");
	//				for (int i = 0; i < entityJsonarray.length(); i++) {
	//					JSONObject entityJson = entityJsonarray.getJSONObject(i);
	//					Jxzy entity = new Jxzy();
	//					entity.setId(entityJson.getString("id"));
	//					entity.setExt1(entityJson.getString("ext1"));
	//					entity.setExt2(entityJson.getString("ext2"));
	//					entity.setExt3(entityJson.getString("ext3"));
	//					entity.setKcmc(entityJson.getString("kcmc"));
	//					entity.setLsid(entityJson.getString("lsid"));
	//					entity.setLsxm(entityJson.getString("lsxm"));
	//					entityList.add(entity);
	//				}
	//			}
	//			resultMessage.setItems(entityList);
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage findXsdy(Xsdy xsdy) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
	//	parameterMap.put("entity.xsid", xsdy.getXsid());
	//	HttpPost httpPost = new HttpPost(HOST + "xsdy-list.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject jsonObject = new JSONObject(message);
	//			resultMessage.setMessage(jsonObject.getString("message"));
	//			resultMessage.setStatus(jsonObject.getBoolean("status"));
	//			List<XsdyDao> entityList = new ArrayList<XsdyDao>();
	//			if (resultMessage.isStatus() && !jsonObject.isNull("list")) {
	//				JSONArray entityJsonarray = jsonObject.getJSONArray("list");
	//				for (int i = 0; i < entityJsonarray.length(); i++) {
	//					JSONObject entityJson = entityJsonarray.getJSONObject(i);
	//					XsdyDao entity = new XsdyDao();
	//					entity.setId(entityJson.getString("id"));
	//					entity.setExt1(entityJson.getString("ext1"));
	//					entity.setExt2(entityJson.getString("ext2"));
	//					entity.setExt3(entityJson.getString("ext3"));
	//					entity.setJxzyid(entityJson.getString("jxzyid"));
	//					entity.setJxzymc(entityJson.getString("jxzymc"));
	//					entity.setXsid(entityJson.getString("xsid"));
	//					entity.setXsmc(entityJson.getString("xsmc"));
	//					entityList.add(entity);
	//				}
	//			}
	//			resultMessage.setItems(entityList);
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}
    //
	//public ResultMessage addXsdy(Xsdy xsdy) {
	//	ResultMessage resultMessage = new ResultMessage();
	//	Map<String, String> parameterMap = new HashMap<String, String>();
    //
	//	parameterMap.put("entity.ext1", xsdy.getExt1());
	//	parameterMap.put("entity.ext2", xsdy.getExt2());
	//	parameterMap.put("entity.ext3", xsdy.getExt3());
	//	parameterMap.put("entity.jxzyid", xsdy.getJxzyid());
	//	parameterMap.put("entity.jxzymc", xsdy.getJxzymc());
	//	parameterMap.put("entity.xsid", xsdy.getXsid());
	//	parameterMap.put("entity.xsmc", xsdy.getXsmc());
    //
	//	HttpPost httpPost = new HttpPost(HOST + "xsdy-saveUpdate.action");
	//	try {
	//		String message = client.doPostAsString(httpPost, parameterMap);
	//		if (message != null && !message.equals("")) {
	//			JSONObject demoJson = new JSONObject(message);
	//			resultMessage.setMessage(demoJson.getString("message"));
	//			resultMessage.setStatus(demoJson.getBoolean("status"));
    //
	//		}
	//	} catch (RuntimeException e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		resultMessage.setMessage("系统发生异常！");
	//		resultMessage.setStatus(false);
	//	}
	//	return resultMessage;
	//}

}
