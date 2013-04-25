package jp.uozias.ecoappserver.controllers;

import javax.servlet.http.HttpServlet;

import jp.uozias.ecoappserver.utils.Constants;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class PushTest extends HttpServlet {

	Sender sender = new Sender(Constants.GCMAPIKEY);
	Message message = new Message.Builder().build();
	MulticastResult result = sender.send(message, devices, 5);

}
