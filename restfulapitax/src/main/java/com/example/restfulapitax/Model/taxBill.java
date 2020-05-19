package com.example.restfulapitax.Model;

import java.sql.Date;
import java.util.ArrayList;

public class taxBill {
    String id;
    ArrayList<declareTax> declareTaxes;
    Date paymentDate;
}
