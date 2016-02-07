package com.bigdatums.datagen;

import org.jfairy.Fairy;
import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.net.NetworkProducer;
import org.jfairy.producer.person.Person;
import java.util.Random;

public class BdPerson {
    public String[] header = {"id","username","email_address","phone_number","first_name","last_name","middle_name","sex","birthdate","join_date","previous_logins","last_ip"};

    public int id;
    public String username;
    public String email_address;
    public String phone_number;
    public String first_name;
    public String last_name;
    public String middle_name;
    public String sex;
    public String birthdate;
    public String join_date;
    public int previous_logins;
    public String last_ip;

    private int currId = 0;
    private Random r = new Random();

    public void populateRandom(Fairy fairy, boolean randId) {
        Person person = fairy.person();
        DateProducer dp = fairy.dateProducer();
        BaseProducer bp = fairy.baseProducer();
        NetworkProducer np = fairy.networkProducer();

        if(randId) currId = r.nextInt(20000000);
        else currId++;

        id = currId;
        username = person.username();
        email_address = person.email();
        phone_number = person.telephoneNumber();
        first_name = person.firstName();
        last_name = person.lastName();
        middle_name = person.middleName();
        sex = person.sex().name();
        birthdate = person.dateOfBirth().toLocalDate().toString();
        join_date = dp.randomDateBetweenYearAndNow(2005).toString();
        previous_logins = bp.randomBetween(1,10000);
        last_ip = np.ipAddress();
    }

    public String[] toArray(){
        String[] personArr = {Integer.toString(id),username,email_address,phone_number,first_name,last_name,middle_name,sex,birthdate,join_date,Integer.toString(previous_logins),last_ip};
        return personArr;
    }

    public String headerToString() {
        return headerToString("\t");
    }

    public String headerToString(String sep) {
        return String.join(sep, header);
    }

    public String toString() {
        return toString("\t");
    }

    public String toString(String sep) {
        return String.join(sep, toArray());
    }

}
