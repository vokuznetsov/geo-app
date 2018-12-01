package ru.one.factor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.one.factor.ddl.HibernateExporter;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        if (args.length == 2 && "-ddl".equals(args[0])) {
            String outputPath = args[1];
            HibernateExporter exporter = new HibernateExporter(outputPath);
            exporter.export();
        } else {
            SpringApplication.run(Application.class, args);
        }
    }
}
