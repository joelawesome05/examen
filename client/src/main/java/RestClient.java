import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Client.Client;
import Client.Step;
import Client.Location;
import Client.HeartRate;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class RestClient {

    public static void main(String[] args) throws InterruptedException {

        List<String> listFechas = new ArrayList<String>();

        listFechas.add("jun 07,2017 1:35");
        listFechas.add("jun 07,2017 2:35");
        listFechas.add("jun 07,2017 3:35");
        listFechas.add("jun 07,2017 4:35");
        listFechas.add("jun 07,2017 5:35");
        listFechas.add("jun 07,2017 6:35");
        listFechas.add("jun 07,2017 15:35");
        listFechas.add("jun 07,2017 16:35");
        listFechas.add("jun 07,2017 17:35");
        listFechas.add("jun 07,2017 18:35");


        listFechas.add("jun 08,2017 1:35");
        listFechas.add("jun 08,2017 12:35");
        listFechas.add("jun 08,2017 13:35");
        listFechas.add("jun 08,2017 14:35");
        listFechas.add("jun 08,2017 15:35");
        listFechas.add("jun 08,2017 16:35");
        listFechas.add("jun 08,2017 18:35");
        listFechas.add("jun 08,2017 19:35");
        listFechas.add("jun 08,2017 20:35");
        listFechas.add("jun 08,2017 21:35");



        double[] latitudes = new double[20];
        latitudes[0] = 1;
        latitudes[1] = 1;
        latitudes[2] = 1;
        latitudes[3] = 1;
        latitudes[4] = 1;
        latitudes[5] = 1;
        latitudes[6] = 1;
        latitudes[7] = 1;
        latitudes[8] = 1;
        latitudes[9] = 1;

        latitudes[10] = 1;
        latitudes[11] = 1;
        latitudes[12] = 1;
        latitudes[13] = 1;
        latitudes[14] = 1;
        latitudes[15] = 1;
        latitudes[16] = 1;
        latitudes[17] = 1;
        latitudes[18] = 1;
        latitudes[19] = 1;






        /*

        // PRUEBA DE STRING TO MILISECUNDS
        String string = "jun 08,2017 15:35";
        DateFormat format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
        System.out.print(date.getTime());

        */

        /*
            while(true) {
                int sleep;
                int id = 1;//id de la banda
                //crear objeto step
                long registration = System.currentTimeMillis();//tiempo de registro
                int numberSteps = (int) (Math.random() * 9) + 1;//numero de pasos aleatorio
                Step step=new Step(numberSteps,registration);
                //crear objeto location
                double lat=(Math.random() * 180)-90;
                double lng=(Math.random() * 360)-180;
                registration = System.currentTimeMillis();//tiempo de registro
                Location location=new Location(lat,lng,registration);
                //crear objeto heartrate
                int bpm=(int) (Math.random() * 80) + 40;
                registration = System.currentTimeMillis();//tiempo de registro
                HeartRate hr=new HeartRate(bpm,registration);
                //duerme el sistema de 1.5 seg hasta 5 seg
                try {
                    sleep=(int) (Math.random() * 5000) + 1500;// de 1.5seg a 5 seg
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //crear cliente con id definido
                Client client=new Client(id);
                //setear
                client.setStep(step);
                client.setLocation(location);
                client.setHeartRate(hr);
                //seetear parametros step
                ArrayList<NameValuePair> parameters=client.parametersStep();
                //ejecutar steps
                client.execute("http://localhost:8080/API/stepLog/" + step.getRegistrationDate(),parameters);
                //setear parametros location
                parameters=client.parametersLocation();
                //ejecutar location
                client.execute("http://localhost:8080/API/locationLog/" + location.getRegistrationDate(),parameters);
                //parametors HeartRate
                parameters=client.parametersHeartRate();
                //ejecutar HeartRate
                client.execute("http://localhost:8080/API/heartrateLog/" + location.getRegistrationDate(),parameters);
                //apagar cliente
                client.shutDown();
            }*/

    }
}