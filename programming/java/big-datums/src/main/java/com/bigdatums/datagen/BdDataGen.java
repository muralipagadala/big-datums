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

    public List<String> genPeopleData(int numLines, boolean addHeader, boolean encloseWithQuotes, String delimiter){

        String out;
        int numFields = 12;
        int lineStart = 0;
        List<String> lines = new ArrayList<String>(numLines);
        if(encloseWithQuotes) delimiter = "\"" + delimiter + "\"";

        if(addHeader) {
            String[] header = new String[numFields];
            header[0] = "id";
            header[1] = "username";
            header[2] = "email_address";
            header[3] = "phone_number";
            header[4] = "first_name";
            header[5] = "last_name";
            header[6] = "middle_name";
            header[7] = "sex";
            header[8] = "birthdate";
            header[9] = "join_date";
            header[10] = "previous_logins";
            header[11] ="last_ip";

            out = String.join(delimiter, header);
            if(encloseWithQuotes) out = addOuterQuotes(out);
            lines.add(out);
            lineStart = 1;
        }

        for(int i=lineStart; i<numLines; i++){

            String[] line = new String[numFields];
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
            lines.add(out);
        }

        return lines;
    }

    public static void main(String[] args){
        BdDataGen bd = new BdDataGen();
        List<String> output = bd.genPeopleData(10000, false, false, "\t");

        for(String line : output){
            System.out.println(line);
        }
    }

}
