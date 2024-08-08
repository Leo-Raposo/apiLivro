package com.example.livro.apilivro.controllers;


import com.example.livro.apilivro.dtos.LivroRecordDto;
import com.example.livro.apilivro.models.LivroModel;
import com.example.livro.apilivro.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livro")
public class LivroController {
    
    @Autowired
    LivroRepository livroRepository;
    
    @GetMapping
    public ResponseEntity<List<LivroModel>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLivroById(@PathVariable(value="id") UUID id ) {
        Optional<LivroModel> livroO = livroRepository.findById(id);
        if (livroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro nao encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(livroO.get());
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<LivroModel>> getLivrosByGenero(@PathVariable(value = "genero") String genero) {
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findByGenero(genero));
    }

    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody @Valid LivroRecordDto livroDto) {
        var livroModel = new LivroModel();
        BeanUtils.copyProperties(livroDto, livroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteLivro(@PathVariable(value="id") UUID id){
        Optional<LivroModel> livroO = livroRepository.findById(id);
        if(livroO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro nao encontrado");
        }else {
            livroRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro excluido com sucesso!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLivro(@PathVariable(value = "id") UUID id, @RequestBody @Valid LivroRecordDto LivroRecordDto) {
        Optional<LivroModel> livroO = livroRepository.findById(id);
        if (livroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro nao encontrado");
        } else {
            var livroModel = livroO.get();
            BeanUtils.copyProperties(LivroRecordDto, livroModel);
            return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livroModel));
        }
    }
}
