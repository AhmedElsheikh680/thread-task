package com.task.task;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@SpringBootApplication
public class TaskApplication {


    static Set<Integer> id = new HashSet<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new ParallelThread());
        Thread t2 = new Thread(new ParallelThread());
        Thread t3 = new Thread(new ParallelThread());
        Thread t4 = new Thread(new ParallelThread());
        Thread t5 = new Thread(new ParallelThread());
        Thread t6 = new Thread(new ParallelThread());
        Thread t7 = new Thread(new ParallelThread());
        Thread t8 = new Thread(new ParallelThread());
        Thread t9 = new Thread(new ParallelThread());
        Thread t10 = new Thread(new ParallelThread());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }

    private static class ParallelThread implements Runnable {
        @Override
        public void run() {
            HttpResponse response;
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getConnection = new HttpGet("https://gist.githubusercontent.com/evanr76/3365397/raw/7015f47f96a254ba71e1deb1d420a87bade42767/shipwire-rate-response-example.xml");
            String xmlString;
            int randomNum = createRandomNumber(id);
            File myObj = new File("D:\\bettleware\\1\\task\\files\\task" + randomNum + ".json");
            try {
                response = httpClient.execute(getConnection);
                xmlString = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONObject json = XML.toJSONObject(xmlString);
                String jsonString = json.toString(4);
                if (myObj.createNewFile()) {
                    FileWriter myWriter = new FileWriter(myObj);
                    myWriter.write(jsonString);
                    myWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int createRandomNumber(Set<Integer> id) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
        if (id.add(randomNum))
            return randomNum;
        return createRandomNumber(id);
    }
}