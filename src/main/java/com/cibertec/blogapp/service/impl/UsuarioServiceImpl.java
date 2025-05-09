package com.cibertec.blogapp.service.impl;

import com.cibertec.blogapp.model.Usuario;
import com.cibertec.blogapp.repository.UsuarioRepository;
import com.cibertec.blogapp.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	// AUTENTICACIÓN PARA SPRING SECURITY
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usuarioRepo.findByUsername(email).map(user -> new User(user.getEmail(), user.getPassword(),
				Collections.singleton(() -> "ROLE_" + user.getRol()) // prefijo obligatorio
		)).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
	}

	// REGISTRAR USUARIO
	public ResponseEntity<?> registrar(Usuario usuario) {
		usuario.setFechaRegistro(LocalDateTime.now());
		Usuario guardado = usuarioRepo.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
	}

	// LISTAR TODOS LOS USUARIOS
	public ResponseEntity<?> listar() {
		List<Usuario> usuarios = usuarioRepo.findAll();
		return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
	}

	// OBTENER USUARIO POR ID
	public ResponseEntity<?> obtenerPorId(Long id) {
		// Se busca el usuario en la base de datos usando el ID proporcionado
		Optional<Usuario> usuario = usuarioRepo.findById(id);

		// Si el usuario se encuentra, se devuelve con un código de respuesta 200 OK
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}

		// Si el usuario no se encuentra, se devuelve un error 404 NOT FOUND con un
		// mensaje
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
	}

	// ACTUALIZAR USUARIO
	public ResponseEntity<?> actualizar(Long id, Usuario nuevo) {
		Optional<Usuario> existenteOpt = usuarioRepo.findById(id);
		if (existenteOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
		}

		Usuario existente = existenteOpt.get();
		existente.setNombre(nuevo.getNombre());
		existente.setEmail(nuevo.getEmail());
		existente.setPassword(nuevo.getPassword()); // Aquí puedes encriptar si usas BCrypt
		existente.setRol(nuevo.getRol());

		Usuario actualizado = usuarioRepo.save(existente);
		return ResponseEntity.ok(actualizado);
	}

	// ELIMINAR USUARIO
	public ResponseEntity<?> eliminar(Long id) {
		if (!usuarioRepo.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
		}
		usuarioRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}