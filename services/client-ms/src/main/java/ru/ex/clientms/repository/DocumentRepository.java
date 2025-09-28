package ru.ex.clientms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.clientms.models.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> { }
