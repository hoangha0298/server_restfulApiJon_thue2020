package com.example.restfulapitax;

import com.example.restfulapitax.Model.DAO.InfomationDatabase;
import com.example.restfulapitax.Model.DAO.StartConnect;
import com.example.restfulapitax.Model.DAO.DAOCreateDataBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class RestfulapitaxApplication {

    public static void main(String[] args) {

        Connection conn = StartConnect.getConnection(
                InfomationDatabase.getHostName(),
                InfomationDatabase.getUserName(),
                InfomationDatabase.getPassword()
        );
        // nếu chưa có database thì tạo database
        if (!StartConnect.selectedDatabase(InfomationDatabase.getDbName())) {
            DAOCreateDataBase.run(conn, InfomationDatabase.getDbName());
        }

        // chạy project spring boot
        SpringApplication.run(RestfulapitaxApplication.class, args);
    }

}
