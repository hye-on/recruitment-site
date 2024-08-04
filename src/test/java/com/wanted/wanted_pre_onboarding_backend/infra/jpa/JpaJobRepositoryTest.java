package com.wanted.wanted_pre_onboarding_backend.infra.jpa;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.wanted.wanted_pre_onboarding_backend.domain.entity.Company;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Job;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.JobSkill;
import com.wanted.wanted_pre_onboarding_backend.domain.entity.Skill;
import com.wanted.wanted_pre_onboarding_backend.infra.jpa.specification.JpaJobSpecification;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaJobRepositoryTest {

	@Autowired
	private JpaJobRepository jobRepository;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() {
		Company company1 = Company.builder()
			.name("원티드")
			.countryName("한국")
			.locationName("서울")
			.build();
		entityManager.persist(company1);

		Company company2 = Company.builder()
			.name("구글")
			.countryName("미국")
			.locationName("실리콘벨리")
			.build();
		entityManager.persist(company2);

		Skill javaSkill = Skill.builder().name("Java").build();
		Skill pythonSkill = Skill.builder().name("Python").build();
		Skill reactSkill = Skill.builder().name("React").build();
		entityManager.persist(javaSkill);
		entityManager.persist(pythonSkill);
		entityManager.persist(reactSkill);

		Job job1 = Job.builder()
			.company(company1)
			.position("주니어 자바 개발자")
			.description("주니어 자바 개발자 채용합니다.")
			.recruitmentBonus(10000)
			.build();
		entityManager.persist(job1);

		Job job2 = Job.builder()
			.company(company2)
			.position("주니어 풀스택 개발자")
			.description("주니어 풀스택 개발자 채용합니다.")
			.recruitmentBonus(8000)
			.build();
		entityManager.persist(job2);

		JobSkill jobSkill1 = JobSkill.builder().job(job1).skill(javaSkill).build();
		JobSkill jobSkill2 = JobSkill.builder().job(job2).skill(pythonSkill).build();
		JobSkill jobSkill3 = JobSkill.builder().job(job2).skill(reactSkill).build();
		entityManager.persist(jobSkill1);
		entityManager.persist(jobSkill2);
		entityManager.persist(jobSkill3);

		entityManager.flush();
		entityManager.clear();
	}

	@Test
	void testSearchByCompanyName() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("원티드"));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getCompany().getName()).isEqualTo("원티드");
	}

	@Test
	void testSearchByCountryName() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("한국"));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getCompany().getCountryName()).isEqualTo("한국");
	}

	@Test
	void testSearchByLocationName() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("실리콘벨리"));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getCompany().getLocationName()).isEqualTo("실리콘벨리");
	}

	@Test
	void testSearchByPosition() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("자바 개발자"));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getPosition()).isEqualTo("주니어 자바 개발자");
	}

	@Test
	void testSearchByDescription() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("풀스택 개발자 채용합니다."));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getDescription()).contains("주니어 풀스택 개발자 채용합니다.");
	}

	@Test
	void testSearchBySkill() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("Python"));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getJobSkills()).anyMatch(js -> js.getSkill().getName().equals("Python"));
	}

	@Test
	void testSearchByRecruitmentBonus() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("8000"));
		assertThat(jobs).hasSize(1);
		assertThat(jobs.get(0).getRecruitmentBonus()).isEqualTo(8000);
	}

	@Test
	void testSearchMultipleResults() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("주니어"));
		assertThat(jobs).hasSize(2);
	}

	@Test
	void testSearchNoResults() {
		List<Job> jobs = jobRepository.findAll(JpaJobSpecification.searchInAllFields("네이버"));
		assertThat(jobs).isEmpty();
	}
}
