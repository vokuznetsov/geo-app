package ru.one.factor.ddl;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class will create an hibernate {@link MetadataSources} with the given dialect and will scan provided
 * package for {@link MappedSuperclass} and {@link Entity}.
 * You can then use the export methods to generate your schema DDL.
 */
public final class HibernateExporter {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateExporter.class);
    private static final String DIALECT = "org.hibernate.dialect.H2Dialect";
    private static final List<String> ENTITY_PACKAGES = Collections.singletonList("ru.one.factor.domain");
    private String outputFile;

    public HibernateExporter(String outputFile) {
        this.outputFile = outputFile;
    }

    public void export() {
        SchemaExport export = new SchemaExport();
        export.setOutputFile(outputFile);
        export.setFormat(true);
        export.setDelimiter(";");
        EnumSet<TargetType> types = EnumSet.of(TargetType.SCRIPT);
        Metadata metadata = createMetadataSources().buildMetadata();
        export.execute(types, Action.CREATE, metadata);
    }

    private MetadataSources createMetadataSources() {
        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", DIALECT)
                        .build());

        for (String entityPackage : ENTITY_PACKAGES) {
            final Reflections reflections = new Reflections(entityPackage);
            for (Class<?> cl : reflections.getTypesAnnotatedWith(MappedSuperclass.class)) {
                metadata.addAnnotatedClass(cl);
                LOG.info(String.format("Mapped = %s", cl.getName()));
            }
            for (Class<?> cl : reflections.getTypesAnnotatedWith(Entity.class)) {
                metadata.addAnnotatedClass(cl);
                LOG.info(String.format("Mapped = %s", cl.getName()));
            }
        }
        return metadata;
    }
}
