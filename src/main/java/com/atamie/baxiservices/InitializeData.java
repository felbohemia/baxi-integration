package com.atamie.baxiservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class InitializeData {


    @Autowired
    private DataSource dataSource;
    InitializeData(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {

        ResourceDatabasePopulator resourceDatabasePopulator1 = new ResourceDatabasePopulator(false,
                false, "UTF-8", new ClassPathResource("account.sql"));
        ResourceDatabasePopulator resourceDatabasePopulator2 = new ResourceDatabasePopulator(false,
                false, "UTF-8", new ClassPathResource("transaction.sql"));
        ResourceDatabasePopulator resourceDatabasePopulator3 = new ResourceDatabasePopulator(false,
                false, "UTF-8", new ClassPathResource("addbalancecolumn.sql"));

        resourceDatabasePopulator1.execute(dataSource);
       resourceDatabasePopulator2.execute(dataSource);
       //resourceDatabasePopulator3.execute(dataSource);

    }
}


