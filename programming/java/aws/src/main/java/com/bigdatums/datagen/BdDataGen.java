package com.bigdatums.datagen;

import org.jfairy.Fairy;
import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.net.NetworkProducer;
import org.jfairy.producer.person.Person;

import java.util.ArrayList;
import java.util.List;

public class BdDataGen {

    private Fairy fairy = Fairy.create();

    private String addOuterQuotes(String line){
        return "\"" + line + "\"";
    }

    public String[] genPeopleData(int numLines, boolean addHeader, boolean encloseWithQuotes, String delimiter){

        List<String> header;
        String out;
        int fields = 12;
        int lineStart = 0;
        String[] lines = new String[numLines];
        if(encloseWithQuotes) delimiter = "\"" + delimiter + "\"";

        if(addHeader) {
            header = new ArrayList<String>();
            header.add("id");
            header.add("username");
            header.add("email_address");
            header.add("phone_number");
            header.add("first_name");
            header.add("last_name");
            header.add("middle_name");
            header.add("sex");
            header.add("birthdate");
            header.add("join_date");
            header.add("previous_logins");
            header.add("last_ip");

            out = String.join(delimiter, header);
            if(encloseWithQuotes) out = addOuterQuotes(out);
            lines[0] = out;
            lineStart = 1;
        }

        for(int i=lineStart; i<numLines; i++){

            String[] line = new String[fields];

            Person person = fairy.person();
            DateProducer dp = fairy.dateProducer();
            BaseProducer bp = fairy.baseProducer();
            NetworkProducer np = fairy.networkProducer();

            line[0] = Integer.toString(i);
            line[1] = person.username();
            line[2] = person.email();
            line[3] = person.telephoneNumber();
            line[4] = person.firstName();
            line[5] = person.lastName();
            line[6] = person.middleName();
            line[7] = person.sex().name();
            line[8] = person.dateOfBirth().toLocalDate().toString();
            line[9] = dp.randomDateBetweenYearAndNow(2005).toString();
            line[10] = Integer.toString(bp.randomBetween(1,10000));
            line[11] = np.ipAddress();

            out = String.join(delimiter, line);
            if(encloseWithQuotes) out = addOuterQuotes(out);
            lines[i] = out;
        }

        return lines;

    }

    public static void main(String[] args){
        BdDataGen bd = new BdDataGen();
        String[] output = bd.genPeopleData(10000, false, false, "\t");

        for(int i=0; i<output.length; i++){
            System.out.println(output[i]);
        }
    }

}
