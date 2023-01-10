package study.querydsl3;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl3.entity.Hello;
import study.querydsl3.entity.QHello;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class Querydsl3ApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		final Hello hello = new Hello();
		em.persist(hello);

		final JPAQueryFactory query = new JPAQueryFactory(em);
		final QHello qHello = QHello.hello;

		final Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
