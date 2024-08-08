package com.example.livro.apilivro.repositories;

import com.example.livro.apilivro.models.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, UUID> {
    List<LivroModel> findByGenero(String genero);
}
