package at.schrer.cookbook.search;

import at.schrer.cookbook.data.entity.RecipeEntity;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class HibernateSearchService {

    private final EntityManager entityManager;

    @Autowired
    public HibernateSearchService(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @PostConstruct
    public void initialize() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }

    public List<RecipeEntity> searchRecipesByFuzzyTitle(String searchTerm){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(RecipeEntity.class).get();
        Query query = queryBuilder.keyword().fuzzy().onField("title").matching(searchTerm).createQuery();

        return (List<RecipeEntity>) fullTextEntityManager.createFullTextQuery(query, RecipeEntity.class).getResultList();
    }

}
