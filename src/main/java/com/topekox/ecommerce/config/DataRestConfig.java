package com.topekox.ecommerce.config;

/*
 * This Class for Disabling method REST / Read Only
 * for method REST GET read only and disable other method
 */

import com.topekox.ecommerce.entity.Country;
import com.topekox.ecommerce.entity.Product;
import com.topekox.ecommerce.entity.ProductCategory;
import com.topekox.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Value("${allowed.origin}")
    private String[] allowedOrigin;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // disable action REST
        HttpMethod[] unsupportedAction = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        disableHttpMethod(config.getExposureConfiguration()
                .forDomainType(Product.class), unsupportedAction);

        disableHttpMethod(config.getExposureConfiguration()
                .forDomainType(ProductCategory.class), unsupportedAction);

        disableHttpMethod(config.getExposureConfiguration()
                .forDomainType(Country.class), unsupportedAction);

        disableHttpMethod(config.getExposureConfiguration()
                .forDomainType(State.class), unsupportedAction);

        // call an internal helper method
        exposeId(config);

        // configure CORS mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigin);
    }

    private void disableHttpMethod(ExposureConfigurer config, HttpMethod[] unsupportedAction) {
        config
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedAction))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedAction));
    }

    private void exposeId(RepositoryRestConfiguration config) {

        // expose entity id

        // get list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClass = new ArrayList<>();

        // get the entity types for entities
        for (EntityType tempEntity : entities) {
            entityClass.add(tempEntity.getJavaType());
        }

        // expose the entity id for the array of entity/domain types
        Class[] domainTypes = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
