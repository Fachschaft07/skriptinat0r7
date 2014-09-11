package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectures;

    public List<Lecture> findAll() {
        return lectures.findAll(sortByLectureAndProfessorName());
    }

    private Sort sortByLectureAndProfessorName() {
        return new Sort(new Order(Sort.Direction.ASC, "name"), new Order(Sort.Direction.ASC, "readingProfessor.lastName"));
    }

    public Lecture save(Lecture lecture) {
        return lectures.save(lecture);
    }

    public void delete(Lecture lecture) {
        lectures.delete(lecture);
    }
}
