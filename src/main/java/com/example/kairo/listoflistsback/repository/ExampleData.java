//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.QueryResults;
//import com.querydsl.core.types.dsl.PathBuilder;
//import com.querydsl.jpa.JPAQueryBase;
//import com.querydsl.jpa.impl.JPAQuery;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@Repository
//public class ExampleData extends JpaCrudRepositoryImpl<Example, FilterExample> {
//
//    public ExampleData(EntityManager entityManager) {
//        super(entityManager);
//    }
//
//    @Override
//    public Page<Example> pageByFilter(Pageable pageable, FilterExample filter) {
//        JPAQuery jpaQuery = new JPAQuery(entityManager);
//
//        QExample entidade = QExample.example;
//        JPAQueryBase jpaQueryBase = jpaQuery.from(entidade);
//        jpaQueryBase.limit(pageable.getPageSize()).offset(pageable.getOffset());
//        PathBuilder<Example> pathBuilder = new PathBuilder<>(Example.class, "account");
//        BooleanBuilder where = new BooleanBuilder();
//
//        where.and(entidade.active.isTrue());
//
//        if (filter.getId() != null) {
//            where.and(entidade.id.like("%" + filter.getId() + "%"));
//        }
//        if (filter.getId() != null && filter.getId() > 0) {
//            where.and(entidade.id.eq(filter.getId()));
//        }
//        if (filter.getCnpj() != null && !filter.getCnpj().isBlank()) {
//            where.and(entidade.cnpj.like("%" + filter.getCnpj() + "%"));
//        }
//
//        if (filter.getActive() != null) {
//            where.and(entidade.active.eq(filter.getActive()));
//        }
//
//        jpaQueryBase.where(where);
//        for (Sort.Order order : pageable.getSort()) {
//            if (order.getDirection().equals(Sort.Direction.ASC)) {
//                jpaQueryBase.orderBy(pathBuilder.getString(order.getProperty()).asc());
//            } else {
//                jpaQueryBase.orderBy(pathBuilder.getString(order.getProperty()).desc());
//            }
//        }
//
//        QueryResults jpaQueryResults = jpaQueryBase.fetchResults();
//        return new PageImpl<Example>(jpaQueryResults.getResults(), pageable, jpaQueryResults.getTotal());
//        //return null;
//    }
//
//    @Override
//    public List<Example> findAllByFilter(FilterExample filter) {
//        JPAQuery jpaQuery = new JPAQuery(entityManager);
//
//        QExample entidade = QExample.example;
//        JPAQueryBase jpaQueryBase = jpaQuery.from(entidade);
//
//        PathBuilder<Example> pathBuilder = new PathBuilder<>(Example.class, "account");
//        BooleanBuilder where = new BooleanBuilder();
//
//        where.and(entidade.active.isTrue());
//
//        if (filter.getId() != null) {
//            where.and(entidade.id.like("%" + filter.getId() + "%"));
//        }
//        if (filter.getId() != null && filter.getId() > 0) {
//            where.and(entidade.id.eq(filter.getId()));
//        }
//        if (filter.getCnpj() != null && !filter.getCnpj().isBlank()) {
//            where.and(entidade.cnpj.like("%" + filter.getCnpj() + "%"));
//        }
//
//        if (filter.getActive() != null) {
//            where.and(entidade.active.eq(filter.getActive()));
//        }
//
//        jpaQueryBase.where(where);
//
//        QueryResults jpaQueryResults = jpaQueryBase.fetchResults();
//        return jpaQueryResults.getResults();
//        //return null;
//    }
//}
