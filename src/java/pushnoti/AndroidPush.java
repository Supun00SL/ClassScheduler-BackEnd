/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushnoti;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AndroidPush {

    /**
     * Replace SERVER_KEY with your SERVER_KEY generated from FCM Replace
     * DEVICE_TOKEN with your DEVICE_TOKEN
     */
    private static String SERVER_KEY = "AAAAmT8Myo4:APA91bGZy4_JcknZlUBjBNr_SFXmMJ-yyEQl-Fbz6nMAEo8fenMmZZ_uiajdS3pg6zLvi_4Xyad9zKU-8lMGH9yb_-CegQDR5WjIx2TUP5-WxJHBZEuvzcUJ1hB93KnRw7skpfgCc1KG";
    private static String DEVICE_TOKEN = "fERH9bNwt-0:APA91bGh8tanplVpHdxhDxu7l-DTrppm2qKiFrkO2j0cY6Mt-dUertEf0P4hICyzpIIKC09zVGC8hSLeVcEmVIl6vRsnUVH_Sn3XPN2AwD8IGbFW3VBv5gY0DdKEDSzWv_ZYfcSATVqg";
    //static int id=1;
    /**
     * USE THIS METHOD to send push notification
     */
    public static void main(String[] args) throws Exception {
        String title = "My First Notification";
        String message = "Hello, I'm push notification";
        //sendPushNotification(title, message, DEVICE_TOKEN);

    }

    /**
     * Sends notification to mobile, YOU DON'T NEED TO UNDERSTAND THIS METHOD
     */
    public static void sendPushNotification(String title, String message,String token,int count) throws Exception {
        
        String pushMessage = "{\"data\":{\"title\":\""
                + title
                + "\",\"message\":\""
                + message
                + "\",\"count\":\""
                + count
                + "\"},\"to\":\""
                + token
                + "\"}";
        // Create connection to send FCM Message request.
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
      

        // Send FCM message content.
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(pushMessage.getBytes());

        System.out.println(conn.getResponseCode());
        System.out.println(conn.getResponseMessage());
    }
}
