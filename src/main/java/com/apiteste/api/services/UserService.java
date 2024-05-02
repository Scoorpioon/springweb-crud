package com.apiteste.api.services;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiteste.api.models.User;
import com.apiteste.api.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> usuarioEncontrado = this.userRepository.findById(id);

        return usuarioEncontrado.orElseThrow(() -> new RuntimeException("Não encontrado."));
    }

    @Transactional // Utilizamos essa notação sempre que formos realizar alguma alteração no banco
    public User create(User usuario) {
        usuario.setId(null);
        usuario = this.userRepository.save(usuario);
        return usuario;
    }

    @Transactional
    public User update(User usuario) {
        User usuarioAtualizado = findById(usuario.getId());
        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setSenha(usuario.getSenha());

        return this.userRepository.save(usuarioAtualizado);
    }

    @Transactional
    public void delete(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception error) {
            throw new RuntimeException("Não foi possível excluir esse usuário.");
        }
    }
}