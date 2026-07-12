package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for Skill operations.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 6 (Section 2): Used in ManyToMany with Employee
 */
@Service
public class SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    private SkillRepository skillRepository;

    /**
     * Get a skill by its ID.
     * Uses JpaRepository.findById().
     *
     * @param id - Skill primary key
     * @return Skill object
     */
    @Transactional
    public Skill get(int id) {
        LOGGER.info("Start SkillService.get, id={}", id);
        Skill skill = skillRepository.findById(id).get();
        LOGGER.info("End SkillService.get, skill={}", skill);
        return skill;
    }

    /**
     * Save (insert or update) a skill.
     * Uses JpaRepository.save().
     *
     * @param skill - Skill object to persist
     */
    @Transactional
    public void save(Skill skill) {
        LOGGER.info("Start SkillService.save");
        skillRepository.save(skill);
        LOGGER.info("End SkillService.save");
    }
}
