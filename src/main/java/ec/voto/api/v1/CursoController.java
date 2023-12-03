package ec.voto.api.v1;

import java.util.List;

import ec.voto.api.dto.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.voto.api.dto.ApiResponseDTO;

import ec.voto.api.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/api/v1.0/curso" })
public class CursoController {
    @Autowired
    CursoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listar() {
        List<CursoDTO> list = service.findAll(new CursoDTO());
        ApiResponseDTO<List<CursoDTO>> response = new ApiResponseDTO<>(true, list);
        return (new ResponseEntity<Object>(response, HttpStatus.OK));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardar(@RequestBody CursoDTO CursoDTO) {
        CursoDTO CursoDTOResult = service.save(CursoDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, CursoDTOResult), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@RequestBody CursoDTO CursoDTO) {
        CursoDTO resultDTO = service.update(CursoDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
        CursoDTO dto = new CursoDTO();
        dto.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
    }
}